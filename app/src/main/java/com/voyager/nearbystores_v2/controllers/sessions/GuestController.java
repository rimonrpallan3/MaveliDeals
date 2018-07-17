package com.voyager.nearbystores_v2.controllers.sessions;

import com.voyager.nearbystores_v2.classes.Guest;

import io.realm.Realm;

/**
 * Created by Amine on 11/20/2017.
 */

public class GuestController {

    public static void saveGuest(final Guest guest){

        Realm  mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(guest);
            }
        });

    }

    public static Guest getGuest(){

        Realm  mRealm = Realm.getDefaultInstance();
        return mRealm.where(Guest.class).findFirst();

    }

    public static void clear(){

        Realm  mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Guest g = realm.where(Guest.class).findFirst();
                try {
                    g.deleteFromRealm();
                }catch (Exception e){

                }
            }
        });

    }



    public static boolean isStored(){

       Realm  mRealm = Realm.getDefaultInstance();
        Guest guest = mRealm.where(Guest.class).findFirst();

        if(guest!=null && guest.isValid() && guest.isManaged()){
            return true;
        }

        return false;
    }


}
