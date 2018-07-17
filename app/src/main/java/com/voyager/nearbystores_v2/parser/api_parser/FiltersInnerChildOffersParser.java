package com.voyager.nearbystores_v2.parser.api_parser;

import android.util.Log;

import com.voyager.nearbystores_v2.appconfig.AppConfig;
import com.voyager.nearbystores_v2.classes.FilterChild;
import com.voyager.nearbystores_v2.classes.FilterInnerChild;
import com.voyager.nearbystores_v2.controllers.Filter.FilterDeepChildOfferController;
import com.voyager.nearbystores_v2.parser.Parser;
import com.voyager.nearbystores_v2.parser.tags.Tags;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;

/**
 * Created by User on 03-Apr-18.
 */

public class FiltersInnerChildOffersParser extends Parser {
    public FiltersInnerChildOffersParser(JSONObject json) {
        super(json);
    }


    public RealmList<FilterInnerChild> getFilters(){

        RealmList<FilterInnerChild> list = new RealmList<FilterInnerChild>();

        try{

            JSONObject json_array = json.getJSONObject(Tags.CHILD);

            for (int i=0;i<json_array.length();i++){


                try {
                    JSONObject json_user = json_array.getJSONObject(i + "");
                    FilterInnerChild filterInnerChild = new FilterInnerChild();
                    filterInnerChild.setNumFilt(json_user.getInt("id_offertype"));
                    filterInnerChild.setNameFilt(json_user.getString("name"));
                    filterInnerChild.setParentCategory(json_user.getInt("parent_id"));
                    filterInnerChild.setStatus(json_user.getInt("status"));
                    System.out.println("FiltersInnerChildOffersParser getFilters num: "+filterInnerChild.getNumFilt());
                    System.out.println("FiltersInnerChildOffersParser getFilters name: "+filterInnerChild.getNameFilt());
                    System.out.println("FiltersInnerChildOffersParser getFilters status: "+filterInnerChild.getStatus());
                    System.out.println("FiltersInnerChildOffersParser getFilters num: "+filterInnerChild.getParentCategory());
                    try {
                        /*FiltersDeepChildOffersParser mDeepChildOffersParser =  new FiltersDeepChildOffersParser(json_user);
                        filterInnerChild.setFilterDeepChild(mDeepChildOffersParser.getFilters());*/

                        /*filterChild.setImages(mImagesParser.getImage());*/
                    }catch (Exception e){
                        if(AppConfig.APP_DEBUG)
                            e.printStackTrace();
                    }

                    if(AppConfig.APP_DEBUG)
                        Log.e("filterInnerChild",json_user.getString("status"));


                    list.add(filterInnerChild);
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