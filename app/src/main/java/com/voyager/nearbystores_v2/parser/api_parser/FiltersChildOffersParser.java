package com.voyager.nearbystores_v2.parser.api_parser;

import android.util.Log;

import com.google.gson.Gson;
import com.voyager.nearbystores_v2.appconfig.AppConfig;
import com.voyager.nearbystores_v2.classes.Filter;
import com.voyager.nearbystores_v2.classes.FilterChild;
import com.voyager.nearbystores_v2.parser.Parser;
import com.voyager.nearbystores_v2.parser.tags.Tags;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;

/**
 * Created by User on 03-Apr-18.
 */

public class FiltersChildOffersParser extends Parser {
    public FiltersChildOffersParser(JSONObject json) {
        super(json);
        Gson gson = new Gson();
        String jsonString = gson.toJson(json);
        System.out.println("----------- ListOffersFragment FilterOffersParser FiltersChildOffersParser list: "+jsonString);
    }


    public RealmList<FilterChild> getFilters(){

        RealmList<FilterChild> list = new RealmList<FilterChild>();

        try{

            JSONObject json_array = json.getJSONObject(Tags.CHILD);
            Gson gson = new Gson();
            String jsonString = gson.toJson(json_array);
            System.out.println("----------- ListOffersFragment FiltersChildOffersParser getFilters list json_array: "+jsonString);

            for (int i=0;i<json_array.length();i++){


                try {
                    JSONObject json_user = json_array.getJSONObject(i + "");
                    gson = new Gson();
                    String jsonString2 = gson.toJson(json_user);
                    System.out.println("----------- ListOffersFragment FiltersChildOffersParser getFilters list json_array inner: "+jsonString2);
                    FilterChild filterChild = new FilterChild();
                    filterChild.setNumFilt(json_user.getInt("id_offertype"));
                    filterChild.setNameFilt(json_user.getString("name"));
                    filterChild.setParentCategory(json_user.getInt("parent_id"));
                    filterChild.setStatus(json_user.getInt("status"));

                    System.out.println("FiltersChildOffersParser getFilters num: "+filterChild.getNumFilt());
                    System.out.println("FiltersChildOffersParser getFilters name: "+filterChild.getNameFilt());
                    System.out.println("FiltersChildOffersParser getFilters status: "+filterChild.getStatus());
                    System.out.println("FiltersChildOffersParser getFilters num: "+filterChild.getParentCategory());

                    try {
                        /*FiltersInnerChildOffersParser mChildInnerParser =  new FiltersInnerChildOffersParser(json_user);
                        filterChild.setFilterInnerChild(mChildInnerParser.getFilters());*/
                        /*filterChild.setImages(mImagesParser.getImage());*/
                    }catch (Exception e){
                        if(AppConfig.APP_DEBUG)
                            e.printStackTrace();
                    }

                    if(AppConfig.APP_DEBUG)
                        Log.e("filterChild",json_user.getString("image"));


                    list.add(filterChild);
                }catch (JSONException e){
                    e.printStackTrace();
                    System.out.println("-----------ListOffersFragment FilterOffersParser FiltersChildOffersParser getFilters list json_array error: "+e.getMessage());

                }

            }

        }catch (JSONException e){
            e.printStackTrace();
            System.out.println("-----------ListOffersFragment FilterOffersParser FiltersChildOffersParser getFilters list json_array error outer : "+e.getMessage());

        }


        return list;
    }



}