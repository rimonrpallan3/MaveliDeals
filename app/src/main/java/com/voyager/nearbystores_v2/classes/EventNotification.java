package com.voyager.nearbystores_v2.classes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Amine on 11/21/2017.
 */

public class EventNotification extends RealmObject {

    @PrimaryKey
    private int id;
    private Event event;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
