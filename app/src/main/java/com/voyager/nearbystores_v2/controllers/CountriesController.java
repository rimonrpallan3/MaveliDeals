package com.voyager.nearbystores_v2.controllers;

import com.voyager.nearbystores_v2.classes.CountriesModel;

import io.realm.Realm;

/**
 * Created by Amine on 7/12/2017.
 */

public class CountriesController {

    public static CountriesModel findByDialCode(String code){
        Realm realm = Realm.getDefaultInstance();
        return realm.where(CountriesModel.class).equalTo("dial_code",code).findFirst();
    }

    public static CountriesModel findByCode(String code){
        Realm realm = Realm.getDefaultInstance();
        return realm.where(CountriesModel.class).equalTo("code",code).findFirst();
    }
}
