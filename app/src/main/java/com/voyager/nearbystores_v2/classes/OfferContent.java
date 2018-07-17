package com.voyager.nearbystores_v2.classes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Amine on 11/8/2017.
 */

public class OfferContent extends RealmObject {

    @PrimaryKey
    private int id;
    private String description;
    private float price;
    private float percent;
    private float offerPercent;
    private float actualPrice;
    private String currency;

    public float getOfferPercent() {
        return offerPercent;
    }

    public void setOfferPercent(float offerPercent) {
        this.offerPercent = offerPercent;
    }

    public float getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(float actualPrice) {
        this.actualPrice = actualPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
