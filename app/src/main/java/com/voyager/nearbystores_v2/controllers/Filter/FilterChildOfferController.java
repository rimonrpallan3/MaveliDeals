package com.voyager.nearbystores_v2.controllers.Filter;

import com.voyager.nearbystores_v2.classes.Filter;
import com.voyager.nearbystores_v2.classes.FilterChild;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by User on 03-Apr-18.
 */

public class FilterChildOfferController {
    
    
    public static List<FilterChild> getArrayList() {

    List <FilterChild> results = new ArrayList<>();
    RealmList<FilterChild> listCats = FilterChildOfferController.list();

    results.addAll(listCats.subList(0, listCats.size()));
    return results;
}

        public static RealmList<FilterChild> list(){

            Realm realm = Realm.getDefaultInstance();
            RealmResults result = realm.where(Filter.class).findAll();

            RealmList <FilterChild> results = new RealmList<FilterChild>();
            results.addAll(result.subList(0, result.size()));

            return  results;
        }

        public static boolean insertFilter(final FilterChild filt){

            Realm realm = Realm.getDefaultInstance();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

//                RealmResults<Filter> r = realm.where(Filter.class).findAll();
//                r.deleteAllFromRealm();

                    realm.copyToRealmOrUpdate(filt);
                }
            });

            return  true;
        }

        public static boolean insertFilters(final RealmList<FilterChild> list){

            Realm realm = Realm.getDefaultInstance();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    RealmList<FilterChild> oldList= FilterChildOfferController.list();
                    if(oldList.size()>0)
                        for (int i=0;i<oldList.size();i++){
                            if(!oldList.get(i).isMenu())
                                oldList.get(i).deleteFromRealm();
                        }

                    realm.copyToRealmOrUpdate(list);
                }
            });

            return  true;
        }


        public static FilterChild findId(int id){

            Realm realm = Realm.getDefaultInstance();
            FilterChild obj = realm.where(FilterChild.class).equalTo("numCat",id).findFirst();

            return obj;
        }

}