package com.voyager.nearbystores_v2.controllers.Filter;

import com.voyager.nearbystores_v2.classes.Filter;
import com.voyager.nearbystores_v2.classes.FilterDeepChild;
import com.voyager.nearbystores_v2.classes.FilterDeepInnerChild;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by User on 03-Apr-18.
 */

public class FilterDeepInnerChildOfferController {


    public static List<FilterDeepInnerChild> getArrayList() {

    List <FilterDeepInnerChild> results = new ArrayList<>();
    RealmList<FilterDeepInnerChild> listCats = FilterDeepInnerChildOfferController.list();

    results.addAll(listCats.subList(0, listCats.size()));
    return results;
}

        public static RealmList<FilterDeepInnerChild> list(){

            Realm realm = Realm.getDefaultInstance();
            RealmResults result = realm.where(Filter.class).findAll();

            RealmList <FilterDeepInnerChild> results = new RealmList<FilterDeepInnerChild>();
            results.addAll(result.subList(0, result.size()));

            return  results;
        }

        public static boolean insertFilter(final FilterDeepInnerChild filt){

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

        public static boolean insertFilters(final RealmList<FilterDeepInnerChild> list){

            Realm realm = Realm.getDefaultInstance();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    RealmList<FilterDeepInnerChild> oldList= FilterDeepInnerChildOfferController.list();
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


        public static FilterDeepInnerChild findId(int id){

            Realm realm = Realm.getDefaultInstance();
            FilterDeepInnerChild obj = realm.where(FilterDeepInnerChild.class).equalTo("numCat",id).findFirst();

            return obj;
        }

}