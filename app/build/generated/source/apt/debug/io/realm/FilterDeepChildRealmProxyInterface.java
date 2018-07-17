package io.realm;


public interface FilterDeepChildRealmProxyInterface {
    public Integer realmGet$numFilt();
    public void realmSet$numFilt(Integer value);
    public int realmGet$type();
    public void realmSet$type(int value);
    public String realmGet$nameFilt();
    public void realmSet$nameFilt(String value);
    public int realmGet$parentCategory();
    public void realmSet$parentCategory(int value);
    public int realmGet$status();
    public void realmSet$status(int value);
    public boolean realmGet$menu();
    public void realmSet$menu(boolean value);
    public RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild> realmGet$filterDeepInnerChildren();
    public void realmSet$filterDeepInnerChildren(RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild> value);
}
