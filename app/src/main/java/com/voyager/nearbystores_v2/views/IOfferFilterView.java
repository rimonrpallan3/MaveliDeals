package com.voyager.nearbystores_v2.views;

import android.view.View;

import com.voyager.nearbystores_v2.RetorHelper.MainOffers;
import com.voyager.nearbystores_v2.RetorHelper.OfferList;

import java.util.List;

/**
 * Created by User on 06-Apr-18.
 */

public interface IOfferFilterView {
    void setFilterList(List<MainOffers> mainOffersList);
    void setFilterListRecycle(View view, int position, int num);
    void setChildList(List<MainOffers> childMainOffersList);

    /*************************************************************

    /*Used in FilterAdapter ItemList*/
    void onItemCheck(String offerTypeCheckList);
    void onItemUncheck(String offerTypeCheckedLists);


    /****************************************************************/
    /*Used in FilterInnerAdapter*/
    void onItemInnerCheck(String offerTypeCheckList);
    void onItemInnerUncheck(String offerTypeCheckedLists);

}
