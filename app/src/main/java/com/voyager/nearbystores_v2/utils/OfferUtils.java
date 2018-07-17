package com.voyager.nearbystores_v2.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Amine on 1/26/2018.
 */

public class OfferUtils {

    public static class Currency{
        public String code="";
        public String symbol="";
        public String name="";
        public int format=1;
    }

    public static String parseCurrencyFormat(float price,String cData){
        //$formats = array("X0,000.00","0,000.00X","X 0,000.00","0,000.00 X","0,000.00","X0,000.00 XX","XX0,000.00","0,000.00XX");

        //emigrate to 1.1.6
        String defCurrency = "";
        Currency mCurrency=null;
        try {
            JSONObject currencyJson  = new JSONObject(cData);

            mCurrency = new Currency();
            mCurrency.code = currencyJson.getString("code");
            mCurrency.symbol = currencyJson.getString("symbol");
            mCurrency.name = currencyJson.getString("name");
            mCurrency.format = currencyJson.getInt("format");

        }catch (JSONException e) {
            defCurrency = cData;
            e.printStackTrace();
        }

        if(!defCurrency.equals(""))
            return cData+" "+String.format("%.2f", price);



        if(mCurrency!=null)
        switch (mCurrency.format){
            case 1:
                return mCurrency.symbol+String.format("%.2f", price);
            case 2:
                return price+mCurrency.symbol;
            case 3:
                return mCurrency.symbol+" "+String.format("%.2f", price);
            case 4:
                return price+" "+mCurrency.symbol;
            case 5:
                return String.valueOf(String.format("%.2f", price));
            case 6:
                return mCurrency.symbol+String.format("%.2f", price)+" "+mCurrency.code;
            case 7:
                return mCurrency.code+String.format("%.2f", price);
            case 8:
                return String.format("%.2f", price)+mCurrency.code;
        }



        return String.valueOf(String.format("%.2f", price));
    }


}
