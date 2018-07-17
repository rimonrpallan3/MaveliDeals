package com.voyager.nearbystores_v2.classes;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import java.io.Serializable;

/**
 * Created by User on 03-Apr-18.
 */

public class FilterChild extends RealmObject implements Serializable {

    @PrimaryKey
    private  Integer numFilt;
    private int type;
    private String nameFilt;
    private int  parentCategory;
    private int  status;
    private boolean menu =true;

    private RealmList<FilterInnerChild> filterInnerChild;


    public FilterChild(Integer numFilt, String nameFilt, int parentCategory) {
        this.numFilt = numFilt;
        this.nameFilt = nameFilt;
        this.parentCategory = parentCategory;
        this.menu = true;
    }

    public FilterChild() {

    }

    public RealmList<FilterInnerChild> getFilterInnerChild() {
        return filterInnerChild;
    }

    public void setFilterInnerChild(RealmList<FilterInnerChild> filterInnerChild) {
        this.filterInnerChild = filterInnerChild;
    }

    public int getStatus() {
        return status;
    }

    public boolean isMenu() {
        return menu;
    }

    public void setMenu(boolean menu) {
        this.menu = menu;
    }

    public void setStatus(int status) {
        this.status = status;
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