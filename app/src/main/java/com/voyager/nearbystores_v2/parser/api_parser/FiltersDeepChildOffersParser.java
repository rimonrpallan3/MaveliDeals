package com.voyager.nearbystores_v2.parser.api_parser;

import android.util.Log;

import com.voyager.nearbystores_v2.appconfig.AppConfig;
import com.voyager.nearbystores_v2.classes.FilterDeepChild;
import com.voyager.nearbystores_v2.classes.FilterInnerChild;
import com.voyager.nearbystores_v2.parser.Parser;
import com.voyager.nearbystores_v2.parser.tags.Tags;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;

/**
 * Created by User on 03-Apr-18.
 */

public class FiltersDeepChildOffersParser extends Parser {
    public FiltersDeepChildOffersParser(JSONObject json) {
        super(json);
    }


    public RealmList<FilterDeepChild> getFilters(){

        RealmList<FilterDeepChild> list = new RealmList<FilterDeepChild>();

        try{

            JSONObject json_array = json.getJSONObject(Tags.CHILD);

            for (int i=0;i<json_array.length();i++){


                try {
                    JSONObject json_user = json_array.getJSONObject(i + "");
                    FilterDeepChild filterDeepChild = new FilterDeepChild();
                    filterDeepChild.setNumFilt(json_user.getInt("id_offertype"));
                    filterDeepChild.setNameFilt(json_user.getString("name"));
                    filterDeepChild.setParentCategory(json_user.getInt("parent_id"));
                    filterDeepChild.setStatus(json_user.getInt("status"));
                    System.out.println("FiltersDeepChildOffersParser getFilters num: "+filterDeepChild.getNumFilt());
                    System.out.println("FiltersDeepChildOffersParser getFilters name: "+filterDeepChild.getNameFilt());
                    System.out.println("FiltersDeepChildOffersParser getFilters status: "+filterDeepChild.getStatus());
                    System.out.println("FiltersDeepChildOffersParser getFilters num: "+filterDeepChild.getParentCategory());
                    try {
                        /*FiltersDeepInnerChildOffersParser mDeepInnerChildOffersParser =  new FiltersDeepInnerChildOffersParser(json_user);
                        filterDeepChild.setFilterDeepInnerChildren(mDeepInnerChildOffersParser.getFilters());*/

                        /*filterChild.setImages(mImagesParser.getImage());*/
                    }catch (Exception e){
                        if(AppConfig.APP_DEBUG)
                            e.printStackTrace();
                    }

                    if(AppConfig.APP_DEBUG)
                        Log.e("filterDeepChild",json_user.getString("child"));


                    list.add(filterDeepChild);
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }

        }catch (JSONException e){
            e.printStackTrace();
        }


        return list;
    }



}