package com.voyager.nearbystores_v2.parser.api_parser;

import android.util.Log;

import com.google.gson.Gson;
import com.voyager.nearbystores_v2.appconfig.AppConfig;
import com.voyager.nearbystores_v2.classes.Filter;
import com.voyager.nearbystores_v2.parser.Parser;
import com.voyager.nearbystores_v2.parser.tags.Tags;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;

/**
 * Created by User on 03-Apr-18.
 */

public class FilterOffersParser extends Parser {
    public FilterOffersParser(JSONObject json) {
        super(json);
        Gson gson = new Gson();
        String jsonString = gson.toJson(json);
        System.out.println("-----------ListOffersFragment FilterOffersParser list: "+jsonString);
    }


    public RealmList<Filter> getFilters(){

        RealmList<Filter> list = new RealmList<Filter>();

        try{

            JSONObject json_array = json.getJSONObject(Tags.RESULT);
            Gson gson = new Gson();
            String jsonString = gson.toJson(json_array);
            System.out.println("-----------ListOffersFragment FilterOffersParser getFilters list json_array: "+jsonString);
            for (int i=0;i<json_array.length();i++){


                try {
                    JSONObject json_user = json_array.getJSONObject(i + "");
                    gson = new Gson();
                    String jsonString2 = gson.toJson(json_user);
                    System.out.println("-----------ListOffersFragment FilterOffersParser getFilters list json_array inner: "+jsonString2);
                    Filter filt = new Filter();
                    filt.setNumFilt(json_user.getInt("id_offertype"));
                    filt.setNameFilt(json_user.getString("name"));
                    filt.setParentCategory(json_user.getInt("parent_id"));
                    filt.setStatus(json_user.getString("status"));
                    System.out.println("FilterOffersParser getFilters num: "+filt.getNumFilt());
                    System.out.println("FilterOffersParser getFilters name: "+filt.getNameFilt());
                    System.out.println("FilterOffersParser getFilters status: "+filt.getStatus());
                    System.out.println("FilterOffersParser getFilters num: "+filt.getParentCategory());

                    try {
                        /*FiltersChildOffersParser mChildParser =  new FiltersChildOffersParser(json_user);
                        filt.setFilterChild(mChildParser.getFilters());*/
                    }catch (Exception e){
                        if(AppConfig.APP_DEBUG)
                            e.printStackTrace();
                    }

                    if(AppConfig.APP_DEBUG)
                        Log.e("filt",json_user.getString("image"));


                    list.add(filt);
                }catch (JSONException e){
                    System.out.println("-----------ListOffersFragment FilterOffersParser getFilters list json_array error: "+e.getMessage());
                    e.printStackTrace();
                }

            }

        }catch (JSONException e){
            e.printStackTrace();
            System.out.println("-----------ListOffersFragment FilterOffersParser getFilters list json_array error outer: "+e.getMessage());

        }


        return list;
    }



}