package io.realm;


public interface EventRealmProxyInterface {
    public int realmGet$id();
    public void realmSet$id(int value);
    public String realmGet$name();
    public void realmSet$name(String value);
    public String realmGet$address();
    public void realmSet$address(String value);
    public com.voyager.nearbystores_v2.classes.Images realmGet$Images();
    public void realmSet$Images(com.voyager.nearbystores_v2.classes.Images value);
    public String realmGet$imageJson();
    public void realmSet$imageJson(String value);
    public String realmGet$dateB();
    public void realmSet$dateB(String value);
    public String realmGet$dateE();
    public void realmSet$dateE(String value);
    public String realmGet$description();
    public void realmSet$description(String value);
    public Double realmGet$distance();
    public void realmSet$distance(Double value);
    public Double realmGet$lat();
    public void realmSet$lat(Double value);
    public Double realmGet$lng();
    public void realmSet$lng(Double value);
    public String realmGet$tel();
    public void realmSet$tel(String value);
    public String realmGet$webSite();
    public void realmSet$webSite(String value);
    public int realmGet$type();
    public void realmSet$type(int value);
    public int realmGet$status();
    public void realmSet$status(int value);
    public boolean realmGet$liked();
    public void realmSet$liked(boolean value);
    public int realmGet$store_id();
    public void realmSet$store_id(int value);
    public String realmGet$store_name();
    public void realmSet$store_name(String value);
    public RealmList<com.voyager.nearbystores_v2.classes.Images> realmGet$listImages();
    public void realmSet$listImages(RealmList<com.voyager.nearbystores_v2.classes.Images> value);
}
