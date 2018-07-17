package com.voyager.nearbystores_v2.utils;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;

import com.voyager.nearbystores_v2.AppController;
import com.voyager.nearbystores_v2.appconfig.AppConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Amine on 8/17/2016.
 */

public class DateUtils {

    public static String getDateByTimeZone(String dateStr, String schema){
        String inputPattern = "yyyy-MM-dd";

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Locale current = AppController.getInstance().getResources().getConfiguration().locale;
        try {

            Date inputDate = inputFormat.parse(dateStr);
            SimpleDateFormat formatter = null;

            if (schema != null) {
                if(AppConfig.APP_DEBUG)
                    Log.e("dateUtilsSchema",schema+" - "+current);
                formatter = new SimpleDateFormat(schema,current);
            }else {
                formatter = new SimpleDateFormat("dd MMMM yyyy hh:mm",current);
            }

            formatter.setTimeZone(TimeZone.getDefault());
            return formatter.format(inputDate);

        }catch (Exception e){
            e.printStackTrace();
            return dateStr;
        }
    }


    public static boolean checkDatesExpired(String endDate)    {
        boolean b = false;
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat dfDate  = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dfDate.format(c);
        try {
            if(dfDate.parse(currentDate).before(dfDate.parse(endDate)))
            {
                b = true;//If start date is before end date
            }
            else if(dfDate.parse(currentDate).equals(dfDate.parse(endDate)))
            {
                b = true;//If two dates are equal
            }
            else
            {
                b = false; //If start date is after the end date
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("MainActivity CheckDates ParseException => " + e.getMessage());
        }
        return b;
    }

    public static String prepareOutputDate(String dateStr, String schema,Context context) {

        String inputPattern = "yyyy-MM-dd hh:mm";

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);

        try {

            inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date inputDate = inputFormat.parse(dateStr);

            String hourFormat = "hh:mm";

            if(context!=null){
                if(hourFormat12(context)){
                    hourFormat = "hh:mm";
                }else {
                    hourFormat = "kk:mm";
                }
            }

            SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy "+hourFormat);
            SimpleDateFormat formatterHour = new SimpleDateFormat(hourFormat);

            if (schema != null) {
                formatter = new SimpleDateFormat(schema);
                formatterHour = new SimpleDateFormat(schema);
            }


            formatter.setTimeZone(TimeZone.getDefault());
            formatterHour.setTimeZone(TimeZone.getDefault());

            int diffrence = minutesDifference(dateStr);

            if(diffrence<1440){
                return formatterHour.format(inputDate);
            }else{
                return formatter.format(inputDate);
            }



        } catch (ParseException e) {


            return dateStr;
        }

    }


    public static long getDateToMillSeconds(String dateStr) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {

            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = formatter.parse(dateStr);

            return date.getTime();
        } catch (ParseException e) {

            if(AppConfig.APP_DEBUG)
                    e.printStackTrace();
        }

        return 0;
    }

    public static int minutesDifference(String dateStr) {

        int MILLI_TO_MINUTE = 1000 * 60;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyy-MM-dd H:m:s");

        Date currentDate = new Date();

        try {

            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = formatter.parse(dateStr);

            formatter.setTimeZone(TimeZone.getDefault());
            String newDate = formatter.format(date);
            date = formatter.parse(newDate);


            return (int) (currentDate.getTime() - date.getTime()) / MILLI_TO_MINUTE;

        } catch (ParseException e) {
            e.printStackTrace();
        }


        return 0;
    }


    private static boolean hourFormat12(Context context){

        Calendar mCalendar = null;
        if (!DateFormat.is24HourFormat(context)) {

            return true;
        } else {
            return false;
        }
    }
}
