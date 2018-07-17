package com.voyager.nearbystores_v2.presenter;

import com.voyager.nearbystores_v2.RetorHelper.MainOffers;
import com.voyager.nearbystores_v2.RetorHelper.OfferList;

import java.util.List;

/**
 * Created by User on 06-Apr-18.
 */

public interface IOfferFilterPresenter {
    void setAdapter(List<MainOffers> mainOffersList);
}
