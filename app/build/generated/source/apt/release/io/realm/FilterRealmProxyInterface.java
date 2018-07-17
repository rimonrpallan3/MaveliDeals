package io.realm;


public interface FilterRealmProxyInterface {
    public Integer realmGet$numFilt();
    public void realmSet$numFilt(Integer value);
    public int realmGet$type();
    public void realmSet$type(int value);
    public String realmGet$nameFilt();
    public void realmSet$nameFilt(String value);
    public int realmGet$parentCategory();
    public void realmSet$parentCategory(int value);
    public String realmGet$status();
    public void realmSet$status(String value);
    public boolean realmGet$menu();
    public void realmSet$menu(boolean value);
    public RealmList<com.voyager.nearbystores_v2.classes.FilterChild> realmGet$filterChild();
    public void realmSet$filterChild(RealmList<com.voyager.nearbystores_v2.classes.FilterChild> value);
}
