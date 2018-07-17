package com.voyager.nearbystores_v2.utils;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Amine on 8/5/2016.
 */

public class Translator {


    private static Map<String,JSONObject> jsonLanguages = null;
    private static boolean jsonLoaded = false;
    public static String DefaultLang = Locale.getDefault().getLanguage().toLowerCase();
    private static Map<String,String> translate=new HashMap<>();
    private static Map<String,String> values=new HashMap<>();
    private static Map<String,String> commentValues=new HashMap<>();




    public static String print(String str){
        String value = print(str,null);

        //%s to str value
        return value;
    }

    public static String print(String str, String comment, Object... values){
        String value = print(str,comment);

        //%s to str value
        return String.format(value,values);
    }

    public static String print(String str, String comment, Double... values){
        String value = print(str,comment);

        //%s to str value
        return String.format(value,values);
    }

    public static String print(String str, String comment, String... values){
        String value = print(str,comment);

        //%s to str value
        return String.format(value,values);
    }

    public static String print(String str, String comment){
        return str;
    }


    public static String getString(String str){

        String value = str;

        try{

            String lang = DefaultLang.trim().toLowerCase();

            JSONObject js = new JSONObject(str);

            if(js.has(lang)){
                value = js.getString(lang);
            }else if(js.has("en")){
                value = js.getString("en");
            }else{
                value = str;
            }

        }catch (JSONException jex){

            value=str;
        }catch (Exception e){
            value ="";
        }


        return value;
    }

    public static String getString(String str, String lng){

        String value = str;

        try{

            String lang = lng.trim();

            JSONObject js = new JSONObject(str);

            if(js.has(lang)){
                value = js.getString(lang);
            }else if(js.has("en")){
                value = js.getString("en");
            }else{
                value = str;
            }



        }catch (JSONException jex){
            //value = str;
          //  jex.printStackTrace();
        }

        return value;
    }





}
