package io.realm;


public interface DiscussionRealmProxyInterface {
    public int realmGet$discussionId();
    public void realmSet$discussionId(int value);
    public com.voyager.nearbystores_v2.classes.User realmGet$senderUser();
    public void realmSet$senderUser(com.voyager.nearbystores_v2.classes.User value);
    public int realmGet$receiverId();
    public void realmSet$receiverId(int value);
    public RealmList<com.voyager.nearbystores_v2.classes.Message> realmGet$messages();
    public void realmSet$messages(RealmList<com.voyager.nearbystores_v2.classes.Message> value);
    public String realmGet$createdAt();
    public void realmSet$createdAt(String value);
    public int realmGet$status();
    public void realmSet$status(int value);
    public boolean realmGet$isSystem();
    public void realmSet$isSystem(boolean value);
}
