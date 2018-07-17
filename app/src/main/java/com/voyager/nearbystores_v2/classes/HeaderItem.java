package com.voyager.nearbystores_v2.classes;

/**
 * Created by Amine on 1/9/2016.
 */
public class HeaderItem extends Item {

    public final static String TAG_NAME = "HeaderItem";

    public HeaderItem(){
        super();
        this.type = TAG_NAME;
    }

    private String email;

    public String getEmail() {
        return email;
    }




    public void setEmail(String email) {
        this.email = email;
    }
}
