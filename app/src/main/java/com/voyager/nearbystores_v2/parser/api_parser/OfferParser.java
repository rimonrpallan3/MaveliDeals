package com.voyager.nearbystores_v2.parser.api_parser;


import com.voyager.nearbystores_v2.classes.Offer;
import com.voyager.nearbystores_v2.parser.Parser;
import com.voyager.nearbystores_v2.parser.tags.Tags;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;


public class OfferParser extends Parser {

    public OfferParser(JSONObject json) {
        super(json);
    }

    public RealmList<Offer> getOffers(){

        RealmList<Offer> list = new RealmList<Offer>();

        try{

            JSONObject json_array = json.getJSONObject(Tags.RESULT);

            for (int i=0;i<json_array.length();i++){

                try {
                    JSONObject json_user = json_array.getJSONObject(i + "");
                    Offer offer = new Offer();

                    offer.setId(json_user.getInt("id_offer"));
                    offer.setName(json_user.getString("name"));
                    offer.setDate_end(json_user.getString("date_end"));
                    offer.setDate_start(json_user.getString("date_start"));
                    offer.setStatus(json_user.getInt("status"));
                    offer.setStore_id(json_user.getInt("store_id"));
                    offer.setStore_name(json_user.getString("store_name"));
                    offer.setDistance(json_user.getDouble("distance"));

                    OfferContentParser mOfferContentParser = new OfferContentParser(new JSONObject(
                            json_user.getString("content")
                    ));

                    offer.setContent(mOfferContentParser.getContent(offer.getId()));

                    ImagesParser mImagesParser = new ImagesParser(
                            new JSONObject(json_user.getString("image"))
                    );

                    offer.setImages(mImagesParser.getImage());

                    list.add(offer);
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
