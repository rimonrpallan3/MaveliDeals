package com.voyager.nearbystores_v2.controllers;

import com.voyager.nearbystores_v2.classes.Guest;
import com.voyager.nearbystores_v2.classes.Review;
import com.voyager.nearbystores_v2.controllers.sessions.GuestController;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Amine on 12/25/2017.
 */

public class ReviewController {


    public static boolean isRated(int store_id){


        Guest guest = GuestController.getGuest();
        Realm mRealm = Realm.getDefaultInstance();

        RealmResults<Review> result = mRealm.where(Review.class)
                .equalTo("store_id",store_id)
                .equalTo("guest_id",guest.getId()).findAll();

        if(result.isLoaded() && result.isValid() && result.size()>0)
            return  true;

        return  false;
    }

}
