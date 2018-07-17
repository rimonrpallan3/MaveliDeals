package com.voyager.nearbystores_v2.parser.api_parser;

import android.util.Log;

import com.voyager.nearbystores_v2.appconfig.AppConfig;
import com.voyager.nearbystores_v2.classes.FilterDeepChild;
import com.voyager.nearbystores_v2.classes.FilterDeepInnerChild;
import com.voyager.nearbystores_v2.parser.Parser;
import com.voyager.nearbystores_v2.parser.tags.Tags;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;

/**
 * Created by User on 03-Apr-18.
 */

public class FiltersDeepInnerChildOffersParser extends Parser {
    public FiltersDeepInnerChildOffersParser(JSONObject json) {
        super(json);
    }


    public RealmList<FilterDeepInnerChild> getFilters(){

        RealmList<FilterDeepInnerChild> list = new RealmList<FilterDeepInnerChild>();

        try{

            JSONObject json_array = json.getJSONObject(Tags.CHILD);

            for (int i=0;i<json_array.length();i++){


                try {
                    JSONObject json_user = json_array.getJSONObject(i + "");
                    FilterDeepInnerChild filterDeepInnerChild = new FilterDeepInnerChild();
                    filterDeepInnerChild.setNumFilt(json_user.getInt("id_offertype"));
                    filterDeepInnerChild.setNameFilt(json_user.getString("name"));
                    filterDeepInnerChild.setParentCategory(json_user.getInt("parent_id"));
                    filterDeepInnerChild.setStatus(json_user.getInt("status"));
                    System.out.println("FiltersDeepInnerChildOffersParser getFilters num: "+filterDeepInnerChild.getNumFilt());
                    System.out.println("FiltersDeepInnerChildOffersParser getFilters name: "+filterDeepInnerChild.getNameFilt());
                    System.out.println("FiltersDeepInnerChildOffersParser getFilters status: "+filterDeepInnerChild.getStatus());
                    System.out.println("FiltersDeepInnerChildOffersParser getFilters num: "+filterDeepInnerChild.getParentCategory());


                    if(AppConfig.APP_DEBUG)
                        Log.e("filterDeepInnerChild",json_user.getString("status"));


                    list.add(filterDeepInnerChild);
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