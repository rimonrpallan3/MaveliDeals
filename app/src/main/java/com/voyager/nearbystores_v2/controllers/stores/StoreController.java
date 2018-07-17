package com.voyager.nearbystores_v2.controllers.stores;

import com.voyager.nearbystores_v2.classes.Store;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Amine on 7/12/2017.
 */

public class StoreController {

    public static boolean insertStores(final RealmList<Store> list){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (Store str : list){
                    Store hasStore = realm.where(Store.class).equalTo("id",str.getId()).findFirst();

                    if(hasStore!=null && hasStore.isLoaded()){
                        str.setSaved(hasStore.isSaved());
                        realm.copyToRealmOrUpdate(hasStore);
                    }else {
                        realm.copyToRealmOrUpdate(str);
                    }
                }

            }
        });
        return  true;
    }

    public static Store findStoreById(int id){

        Realm realm = Realm.getDefaultInstance();
        Store obj = realm.where(Store.class).equalTo("id",id).findFirst();

        return obj;
    }

    public static Store findStoreByUserId(int id){

        Realm realm = Realm.getDefaultInstance();
        Store obj = realm.where(Store.class).equalTo("id",id).findFirst();

        return obj;
    }

    public static Store getStore(int id){

        Realm realm = Realm.getDefaultInstance();
        Store obj = realm.where(Store.class).equalTo("id",id).findFirst();

        return obj;
    }



    public static boolean doSave(int id){

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Store obj = realm.where(Store.class).equalTo("id",id).findFirst();
        obj.setSaved(true);
        realm.copyToRealmOrUpdate(obj);
        realm.commitTransaction();

        return false;
    }


    public static boolean isSaved(int id){

        Realm realm = Realm.getDefaultInstance();
        Store store = realm.where(Store.class).equalTo("id",id).equalTo("saved",true).findFirst();

        if(store!=null)
            return true;

        return false;
    }


    public static String getSavedStoresAsString(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Store> results = realm.where(Store.class).equalTo("saved",true).findAll();

        String ids = "";
        for (Store st : results){
            ids = ids+","+st.getId();
        }

        return ids;
    }

    public static List<Store> getSavedStoresAsArrayList(){

        Realm realm = Realm.getDefaultInstance();
        RealmResults result = realm.where(Store.class).equalTo("saved",true).findAll();

        List <Store> array = new ArrayList<>();

        array.addAll(result.subList(0, result.size()));
        return  array;
    }

    public static boolean doDelete(int id){

        Realm realm = Realm.getDefaultInstance();
        final Store obj = realm.where(Store.class).equalTo("id",id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                obj.setSaved(false);
                realm.copyToRealm(obj);
            }
        });
        return false;
    }

    public static void removeAll(){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults result = realm.where(Store.class).findAll();
                result.deleteAllFromRealm();
            }
        });

    }

}
