package com.voyager.nearbystores_v2.Services;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Amine on 8/19/2016.
 */

public class BusStation {

   // private static Bus bus=new Bus(ThreadEnforcer.ANY);


    public static EventBus getBus() {

        return EventBus.getDefault();
    }

//    public static void setBus(Bus bus) {
//        BusStation.bus = bus;
//    }
}
