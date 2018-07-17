package com.voyager.nearbystores_v2.classes;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by User on 03-Apr-18.
 */

public class Filter extends RealmObject implements Serializable {

    @PrimaryKey
    private  Integer numFilt;
    private int type;
    private String nameFilt;
    private int  parentCategory;
    private String status;
    private boolean menu =true;
    private RealmList<FilterChild> filterChild;


    public Filter(Integer numFilt, String nameFilt, int parentCategory) {
        this.numFilt = numFilt;
        this.nameFilt = nameFilt;
        this.parentCategory = parentCategory;
        this.menu = true;
    }

    public Filter() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isMenu() {
        return menu;
    }

    public void setMenu(boolean menu) {
        this.menu = menu;
    }

    public RealmList<FilterChild> getFilterChild() {
        return filterChild;
    }

    public void setFilterChild(RealmList<FilterChild> filterChild) {
        this.filterChild = filterChild;
    }

    public int getNumFilt() {
        return numFilt;
    }

    public void setNumFilt(Integer numFilt) {
        this.numFilt = numFilt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNameFilt() {
        return nameFilt;
    }

    public void setNameFilt(String nameFilt) {
        this.nameFilt = nameFilt;
    }

    public int getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(int parentCategory) {
        this.parentCategory = parentCategory;
    }
}