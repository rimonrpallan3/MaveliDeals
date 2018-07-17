package com.voyager.nearbystores_v2.presenter;

import com.voyager.nearbystores_v2.RetorHelper.MainOffers;
import com.voyager.nearbystores_v2.RetorHelper.OfferList;
import com.voyager.nearbystores_v2.views.IOfferFilterView;

import java.util.List;

/**
 * Created by User on 06-Apr-18.
 */

public class OfferFilterPresenter implements IOfferFilterPresenter{

    IOfferFilterView iOfferFilterView;

    public OfferFilterPresenter(IOfferFilterView iOfferFilterView) {
        this.iOfferFilterView = iOfferFilterView;
    }

    @Override
    public void setAdapter(List<MainOffers> mainOffersList) {
        iOfferFilterView.setFilterList(mainOffersList);
    }
}
