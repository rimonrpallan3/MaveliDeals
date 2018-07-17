package com.voyager.nearbystores_v2.parser.api_parser;


import com.voyager.nearbystores_v2.classes.OfferContent;
import com.voyager.nearbystores_v2.parser.Parser;

import org.json.JSONException;
import org.json.JSONObject;


public class OfferContentParser extends Parser {

    public OfferContentParser(JSONObject json) {
        super(json);
    }

    public OfferContent getContent(int offerId){

        OfferContent mOfferContent = new OfferContent();

        try{


            mOfferContent.setId(offerId);
            mOfferContent.setCurrency(json.getString("currency"));
            mOfferContent.setDescription(json.getString("description"));
            mOfferContent.setPercent((float) json.getDouble("percent"));
            mOfferContent.setOfferPercent((float) json.getDouble("offerpercent"));
            mOfferContent.setPrice((float) json.getDouble("price"));
            mOfferContent.setActualPrice((float) json.getDouble("actualPrice"));

            return mOfferContent;

        }catch (JSONException e){
            e.printStackTrace();
        }


        return null;
    }



}
