package io.realm;


import android.util.JsonReader;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmModel>> modelClasses = new HashSet<Class<? extends RealmModel>>(20);
        modelClasses.add(com.voyager.nearbystores_v2.classes.FilterDeepChild.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.Session.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.Discussion.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.Event.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.Inbox.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.FilterInnerChild.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.FilterChild.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.EventNotification.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.Store.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.CountriesModel.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.Offer.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.Review.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.Filter.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.Images.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.Message.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.Category.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.OfferContent.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.Guest.class);
        modelClasses.add(com.voyager.nearbystores_v2.classes.User.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public Map<Class<? extends RealmModel>, OsObjectSchemaInfo> getExpectedObjectSchemaInfoMap() {
        Map<Class<? extends RealmModel>, OsObjectSchemaInfo> infoMap = new HashMap<Class<? extends RealmModel>, OsObjectSchemaInfo>(20);
        infoMap.put(com.voyager.nearbystores_v2.classes.FilterDeepChild.class, io.realm.FilterDeepChildRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.Session.class, io.realm.SessionRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.Discussion.class, io.realm.DiscussionRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.Event.class, io.realm.EventRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.Inbox.class, io.realm.InboxRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.FilterInnerChild.class, io.realm.FilterInnerChildRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.FilterChild.class, io.realm.FilterChildRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.EventNotification.class, io.realm.EventNotificationRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.Store.class, io.realm.StoreRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.CountriesModel.class, io.realm.CountriesModelRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.Offer.class, io.realm.OfferRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.Review.class, io.realm.ReviewRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.Filter.class, io.realm.FilterRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.Images.class, io.realm.ImagesRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.Message.class, io.realm.MessageRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.Category.class, io.realm.CategoryRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class, io.realm.FilterDeepInnerChildRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.OfferContent.class, io.realm.OfferContentRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.Guest.class, io.realm.GuestRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.voyager.nearbystores_v2.classes.User.class, io.realm.UserRealmProxy.getExpectedObjectSchemaInfo());
        return infoMap;
    }

    @Override
    public ColumnInfo createColumnInfo(Class<? extends RealmModel> clazz, OsSchemaInfo schemaInfo) {
        checkClass(clazz);

        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepChild.class)) {
            return io.realm.FilterDeepChildRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Session.class)) {
            return io.realm.SessionRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Discussion.class)) {
            return io.realm.DiscussionRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Event.class)) {
            return io.realm.EventRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Inbox.class)) {
            return io.realm.InboxRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterInnerChild.class)) {
            return io.realm.FilterInnerChildRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterChild.class)) {
            return io.realm.FilterChildRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.EventNotification.class)) {
            return io.realm.EventNotificationRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Store.class)) {
            return io.realm.StoreRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.CountriesModel.class)) {
            return io.realm.CountriesModelRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Offer.class)) {
            return io.realm.OfferRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Review.class)) {
            return io.realm.ReviewRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Filter.class)) {
            return io.realm.FilterRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Images.class)) {
            return io.realm.ImagesRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Message.class)) {
            return io.realm.MessageRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Category.class)) {
            return io.realm.CategoryRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class)) {
            return io.realm.FilterDeepInnerChildRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.OfferContent.class)) {
            return io.realm.OfferContentRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Guest.class)) {
            return io.realm.GuestRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.User.class)) {
            return io.realm.UserRealmProxy.createColumnInfo(schemaInfo);
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public List<String> getFieldNames(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepChild.class)) {
            return io.realm.FilterDeepChildRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Session.class)) {
            return io.realm.SessionRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Discussion.class)) {
            return io.realm.DiscussionRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Event.class)) {
            return io.realm.EventRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Inbox.class)) {
            return io.realm.InboxRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterInnerChild.class)) {
            return io.realm.FilterInnerChildRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterChild.class)) {
            return io.realm.FilterChildRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.EventNotification.class)) {
            return io.realm.EventNotificationRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Store.class)) {
            return io.realm.StoreRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.CountriesModel.class)) {
            return io.realm.CountriesModelRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Offer.class)) {
            return io.realm.OfferRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Review.class)) {
            return io.realm.ReviewRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Filter.class)) {
            return io.realm.FilterRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Images.class)) {
            return io.realm.ImagesRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Message.class)) {
            return io.realm.MessageRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Category.class)) {
            return io.realm.CategoryRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class)) {
            return io.realm.FilterDeepInnerChildRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.OfferContent.class)) {
            return io.realm.OfferContentRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Guest.class)) {
            return io.realm.GuestRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.User.class)) {
            return io.realm.UserRealmProxy.getFieldNames();
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public String getSimpleClassNameImpl(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepChild.class)) {
            return io.realm.FilterDeepChildRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Session.class)) {
            return io.realm.SessionRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Discussion.class)) {
            return io.realm.DiscussionRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Event.class)) {
            return io.realm.EventRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Inbox.class)) {
            return io.realm.InboxRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterInnerChild.class)) {
            return io.realm.FilterInnerChildRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterChild.class)) {
            return io.realm.FilterChildRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.EventNotification.class)) {
            return io.realm.EventNotificationRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Store.class)) {
            return io.realm.StoreRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.CountriesModel.class)) {
            return io.realm.CountriesModelRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Offer.class)) {
            return io.realm.OfferRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Review.class)) {
            return io.realm.ReviewRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Filter.class)) {
            return io.realm.FilterRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Images.class)) {
            return io.realm.ImagesRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Message.class)) {
            return io.realm.MessageRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Category.class)) {
            return io.realm.CategoryRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class)) {
            return io.realm.FilterDeepInnerChildRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.OfferContent.class)) {
            return io.realm.OfferContentRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Guest.class)) {
            return io.realm.GuestRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.User.class)) {
            return io.realm.UserRealmProxy.getSimpleClassName();
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E newInstance(Class<E> clazz, Object baseRealm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        try {
            objectContext.set((BaseRealm) baseRealm, row, columnInfo, acceptDefaultValue, excludeFields);
            checkClass(clazz);

            if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepChild.class)) {
                return clazz.cast(new io.realm.FilterDeepChildRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.Session.class)) {
                return clazz.cast(new io.realm.SessionRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.Discussion.class)) {
                return clazz.cast(new io.realm.DiscussionRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.Event.class)) {
                return clazz.cast(new io.realm.EventRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.Inbox.class)) {
                return clazz.cast(new io.realm.InboxRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterInnerChild.class)) {
                return clazz.cast(new io.realm.FilterInnerChildRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterChild.class)) {
                return clazz.cast(new io.realm.FilterChildRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.EventNotification.class)) {
                return clazz.cast(new io.realm.EventNotificationRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.Store.class)) {
                return clazz.cast(new io.realm.StoreRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.CountriesModel.class)) {
                return clazz.cast(new io.realm.CountriesModelRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.Offer.class)) {
                return clazz.cast(new io.realm.OfferRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.Review.class)) {
                return clazz.cast(new io.realm.ReviewRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.Filter.class)) {
                return clazz.cast(new io.realm.FilterRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.Images.class)) {
                return clazz.cast(new io.realm.ImagesRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.Message.class)) {
                return clazz.cast(new io.realm.MessageRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.Category.class)) {
                return clazz.cast(new io.realm.CategoryRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class)) {
                return clazz.cast(new io.realm.FilterDeepInnerChildRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.OfferContent.class)) {
                return clazz.cast(new io.realm.OfferContentRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.Guest.class)) {
                return clazz.cast(new io.realm.GuestRealmProxy());
            }
            if (clazz.equals(com.voyager.nearbystores_v2.classes.User.class)) {
                return clazz.cast(new io.realm.UserRealmProxy());
            }
            throw getMissingProxyClassException(clazz);
        } finally {
            objectContext.clear();
        }
    }

    @Override
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmModel> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepChild.class)) {
            return clazz.cast(io.realm.FilterDeepChildRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.FilterDeepChild) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Session.class)) {
            return clazz.cast(io.realm.SessionRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Session) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Discussion.class)) {
            return clazz.cast(io.realm.DiscussionRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Discussion) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Event.class)) {
            return clazz.cast(io.realm.EventRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Event) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Inbox.class)) {
            return clazz.cast(io.realm.InboxRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Inbox) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterInnerChild.class)) {
            return clazz.cast(io.realm.FilterInnerChildRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.FilterInnerChild) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterChild.class)) {
            return clazz.cast(io.realm.FilterChildRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.FilterChild) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.EventNotification.class)) {
            return clazz.cast(io.realm.EventNotificationRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.EventNotification) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Store.class)) {
            return clazz.cast(io.realm.StoreRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Store) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.CountriesModel.class)) {
            return clazz.cast(io.realm.CountriesModelRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.CountriesModel) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Offer.class)) {
            return clazz.cast(io.realm.OfferRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Offer) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Review.class)) {
            return clazz.cast(io.realm.ReviewRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Review) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Filter.class)) {
            return clazz.cast(io.realm.FilterRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Filter) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Images.class)) {
            return clazz.cast(io.realm.ImagesRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Images) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Message.class)) {
            return clazz.cast(io.realm.MessageRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Message) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Category.class)) {
            return clazz.cast(io.realm.CategoryRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Category) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class)) {
            return clazz.cast(io.realm.FilterDeepInnerChildRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.OfferContent.class)) {
            return clazz.cast(io.realm.OfferContentRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.OfferContent) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Guest.class)) {
            return clazz.cast(io.realm.GuestRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Guest) obj, update, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.User.class)) {
            return clazz.cast(io.realm.UserRealmProxy.copyOrUpdate(realm, (com.voyager.nearbystores_v2.classes.User) obj, update, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public void insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepChild.class)) {
            io.realm.FilterDeepChildRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.FilterDeepChild) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Session.class)) {
            io.realm.SessionRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Session) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Discussion.class)) {
            io.realm.DiscussionRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Discussion) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Event.class)) {
            io.realm.EventRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Event) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Inbox.class)) {
            io.realm.InboxRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Inbox) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterInnerChild.class)) {
            io.realm.FilterInnerChildRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.FilterInnerChild) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterChild.class)) {
            io.realm.FilterChildRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.FilterChild) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.EventNotification.class)) {
            io.realm.EventNotificationRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.EventNotification) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Store.class)) {
            io.realm.StoreRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Store) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.CountriesModel.class)) {
            io.realm.CountriesModelRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.CountriesModel) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Offer.class)) {
            io.realm.OfferRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Offer) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Review.class)) {
            io.realm.ReviewRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Review) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Filter.class)) {
            io.realm.FilterRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Filter) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Images.class)) {
            io.realm.ImagesRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Images) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Message.class)) {
            io.realm.MessageRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Message) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Category.class)) {
            io.realm.CategoryRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Category) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class)) {
            io.realm.FilterDeepInnerChildRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.OfferContent.class)) {
            io.realm.OfferContentRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.OfferContent) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Guest.class)) {
            io.realm.GuestRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Guest) object, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.User.class)) {
            io.realm.UserRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.User) object, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepChild.class)) {
                io.realm.FilterDeepChildRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.FilterDeepChild) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Session.class)) {
                io.realm.SessionRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Session) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Discussion.class)) {
                io.realm.DiscussionRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Discussion) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Event.class)) {
                io.realm.EventRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Event) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Inbox.class)) {
                io.realm.InboxRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Inbox) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterInnerChild.class)) {
                io.realm.FilterInnerChildRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.FilterInnerChild) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterChild.class)) {
                io.realm.FilterChildRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.FilterChild) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.EventNotification.class)) {
                io.realm.EventNotificationRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.EventNotification) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Store.class)) {
                io.realm.StoreRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Store) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.CountriesModel.class)) {
                io.realm.CountriesModelRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.CountriesModel) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Offer.class)) {
                io.realm.OfferRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Offer) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Review.class)) {
                io.realm.ReviewRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Review) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Filter.class)) {
                io.realm.FilterRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Filter) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Images.class)) {
                io.realm.ImagesRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Images) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Message.class)) {
                io.realm.MessageRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Message) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Category.class)) {
                io.realm.CategoryRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Category) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class)) {
                io.realm.FilterDeepInnerChildRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.OfferContent.class)) {
                io.realm.OfferContentRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.OfferContent) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Guest.class)) {
                io.realm.GuestRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.Guest) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.User.class)) {
                io.realm.UserRealmProxy.insert(realm, (com.voyager.nearbystores_v2.classes.User) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepChild.class)) {
                    io.realm.FilterDeepChildRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Session.class)) {
                    io.realm.SessionRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Discussion.class)) {
                    io.realm.DiscussionRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Event.class)) {
                    io.realm.EventRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Inbox.class)) {
                    io.realm.InboxRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterInnerChild.class)) {
                    io.realm.FilterInnerChildRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterChild.class)) {
                    io.realm.FilterChildRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.EventNotification.class)) {
                    io.realm.EventNotificationRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Store.class)) {
                    io.realm.StoreRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.CountriesModel.class)) {
                    io.realm.CountriesModelRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Offer.class)) {
                    io.realm.OfferRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Review.class)) {
                    io.realm.ReviewRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Filter.class)) {
                    io.realm.FilterRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Images.class)) {
                    io.realm.ImagesRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Message.class)) {
                    io.realm.MessageRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Category.class)) {
                    io.realm.CategoryRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class)) {
                    io.realm.FilterDeepInnerChildRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.OfferContent.class)) {
                    io.realm.OfferContentRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Guest.class)) {
                    io.realm.GuestRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.User.class)) {
                    io.realm.UserRealmProxy.insert(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, RealmModel obj, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepChild.class)) {
            io.realm.FilterDeepChildRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.FilterDeepChild) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Session.class)) {
            io.realm.SessionRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Session) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Discussion.class)) {
            io.realm.DiscussionRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Discussion) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Event.class)) {
            io.realm.EventRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Event) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Inbox.class)) {
            io.realm.InboxRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Inbox) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterInnerChild.class)) {
            io.realm.FilterInnerChildRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.FilterInnerChild) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterChild.class)) {
            io.realm.FilterChildRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.FilterChild) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.EventNotification.class)) {
            io.realm.EventNotificationRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.EventNotification) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Store.class)) {
            io.realm.StoreRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Store) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.CountriesModel.class)) {
            io.realm.CountriesModelRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.CountriesModel) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Offer.class)) {
            io.realm.OfferRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Offer) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Review.class)) {
            io.realm.ReviewRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Review) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Filter.class)) {
            io.realm.FilterRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Filter) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Images.class)) {
            io.realm.ImagesRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Images) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Message.class)) {
            io.realm.MessageRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Message) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Category.class)) {
            io.realm.CategoryRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Category) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class)) {
            io.realm.FilterDeepInnerChildRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.OfferContent.class)) {
            io.realm.OfferContentRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.OfferContent) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Guest.class)) {
            io.realm.GuestRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Guest) obj, cache);
        } else if (clazz.equals(com.voyager.nearbystores_v2.classes.User.class)) {
            io.realm.UserRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.User) obj, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepChild.class)) {
                io.realm.FilterDeepChildRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.FilterDeepChild) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Session.class)) {
                io.realm.SessionRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Session) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Discussion.class)) {
                io.realm.DiscussionRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Discussion) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Event.class)) {
                io.realm.EventRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Event) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Inbox.class)) {
                io.realm.InboxRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Inbox) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterInnerChild.class)) {
                io.realm.FilterInnerChildRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.FilterInnerChild) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterChild.class)) {
                io.realm.FilterChildRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.FilterChild) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.EventNotification.class)) {
                io.realm.EventNotificationRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.EventNotification) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Store.class)) {
                io.realm.StoreRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Store) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.CountriesModel.class)) {
                io.realm.CountriesModelRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.CountriesModel) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Offer.class)) {
                io.realm.OfferRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Offer) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Review.class)) {
                io.realm.ReviewRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Review) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Filter.class)) {
                io.realm.FilterRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Filter) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Images.class)) {
                io.realm.ImagesRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Images) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Message.class)) {
                io.realm.MessageRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Message) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Category.class)) {
                io.realm.CategoryRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Category) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class)) {
                io.realm.FilterDeepInnerChildRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.OfferContent.class)) {
                io.realm.OfferContentRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.OfferContent) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Guest.class)) {
                io.realm.GuestRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.Guest) object, cache);
            } else if (clazz.equals(com.voyager.nearbystores_v2.classes.User.class)) {
                io.realm.UserRealmProxy.insertOrUpdate(realm, (com.voyager.nearbystores_v2.classes.User) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepChild.class)) {
                    io.realm.FilterDeepChildRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Session.class)) {
                    io.realm.SessionRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Discussion.class)) {
                    io.realm.DiscussionRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Event.class)) {
                    io.realm.EventRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Inbox.class)) {
                    io.realm.InboxRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterInnerChild.class)) {
                    io.realm.FilterInnerChildRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterChild.class)) {
                    io.realm.FilterChildRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.EventNotification.class)) {
                    io.realm.EventNotificationRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Store.class)) {
                    io.realm.StoreRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.CountriesModel.class)) {
                    io.realm.CountriesModelRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Offer.class)) {
                    io.realm.OfferRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Review.class)) {
                    io.realm.ReviewRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Filter.class)) {
                    io.realm.FilterRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Images.class)) {
                    io.realm.ImagesRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Message.class)) {
                    io.realm.MessageRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Category.class)) {
                    io.realm.CategoryRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class)) {
                    io.realm.FilterDeepInnerChildRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.OfferContent.class)) {
                    io.realm.OfferContentRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.Guest.class)) {
                    io.realm.GuestRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.voyager.nearbystores_v2.classes.User.class)) {
                    io.realm.UserRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepChild.class)) {
            return clazz.cast(io.realm.FilterDeepChildRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Session.class)) {
            return clazz.cast(io.realm.SessionRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Discussion.class)) {
            return clazz.cast(io.realm.DiscussionRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Event.class)) {
            return clazz.cast(io.realm.EventRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Inbox.class)) {
            return clazz.cast(io.realm.InboxRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterInnerChild.class)) {
            return clazz.cast(io.realm.FilterInnerChildRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterChild.class)) {
            return clazz.cast(io.realm.FilterChildRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.EventNotification.class)) {
            return clazz.cast(io.realm.EventNotificationRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Store.class)) {
            return clazz.cast(io.realm.StoreRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.CountriesModel.class)) {
            return clazz.cast(io.realm.CountriesModelRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Offer.class)) {
            return clazz.cast(io.realm.OfferRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Review.class)) {
            return clazz.cast(io.realm.ReviewRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Filter.class)) {
            return clazz.cast(io.realm.FilterRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Images.class)) {
            return clazz.cast(io.realm.ImagesRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Message.class)) {
            return clazz.cast(io.realm.MessageRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Category.class)) {
            return clazz.cast(io.realm.CategoryRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class)) {
            return clazz.cast(io.realm.FilterDeepInnerChildRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.OfferContent.class)) {
            return clazz.cast(io.realm.OfferContentRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Guest.class)) {
            return clazz.cast(io.realm.GuestRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.User.class)) {
            return clazz.cast(io.realm.UserRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepChild.class)) {
            return clazz.cast(io.realm.FilterDeepChildRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Session.class)) {
            return clazz.cast(io.realm.SessionRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Discussion.class)) {
            return clazz.cast(io.realm.DiscussionRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Event.class)) {
            return clazz.cast(io.realm.EventRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Inbox.class)) {
            return clazz.cast(io.realm.InboxRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterInnerChild.class)) {
            return clazz.cast(io.realm.FilterInnerChildRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterChild.class)) {
            return clazz.cast(io.realm.FilterChildRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.EventNotification.class)) {
            return clazz.cast(io.realm.EventNotificationRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Store.class)) {
            return clazz.cast(io.realm.StoreRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.CountriesModel.class)) {
            return clazz.cast(io.realm.CountriesModelRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Offer.class)) {
            return clazz.cast(io.realm.OfferRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Review.class)) {
            return clazz.cast(io.realm.ReviewRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Filter.class)) {
            return clazz.cast(io.realm.FilterRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Images.class)) {
            return clazz.cast(io.realm.ImagesRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Message.class)) {
            return clazz.cast(io.realm.MessageRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Category.class)) {
            return clazz.cast(io.realm.CategoryRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class)) {
            return clazz.cast(io.realm.FilterDeepInnerChildRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.OfferContent.class)) {
            return clazz.cast(io.realm.OfferContentRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Guest.class)) {
            return clazz.cast(io.realm.GuestRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.User.class)) {
            return clazz.cast(io.realm.UserRealmProxy.createUsingJsonStream(realm, reader));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepChild.class)) {
            return clazz.cast(io.realm.FilterDeepChildRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.FilterDeepChild) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Session.class)) {
            return clazz.cast(io.realm.SessionRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.Session) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Discussion.class)) {
            return clazz.cast(io.realm.DiscussionRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.Discussion) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Event.class)) {
            return clazz.cast(io.realm.EventRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.Event) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Inbox.class)) {
            return clazz.cast(io.realm.InboxRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.Inbox) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterInnerChild.class)) {
            return clazz.cast(io.realm.FilterInnerChildRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.FilterInnerChild) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterChild.class)) {
            return clazz.cast(io.realm.FilterChildRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.FilterChild) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.EventNotification.class)) {
            return clazz.cast(io.realm.EventNotificationRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.EventNotification) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Store.class)) {
            return clazz.cast(io.realm.StoreRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.Store) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.CountriesModel.class)) {
            return clazz.cast(io.realm.CountriesModelRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.CountriesModel) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Offer.class)) {
            return clazz.cast(io.realm.OfferRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.Offer) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Review.class)) {
            return clazz.cast(io.realm.ReviewRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.Review) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Filter.class)) {
            return clazz.cast(io.realm.FilterRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.Filter) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Images.class)) {
            return clazz.cast(io.realm.ImagesRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.Images) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Message.class)) {
            return clazz.cast(io.realm.MessageRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.Message) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Category.class)) {
            return clazz.cast(io.realm.CategoryRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.Category) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class)) {
            return clazz.cast(io.realm.FilterDeepInnerChildRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.FilterDeepInnerChild) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.OfferContent.class)) {
            return clazz.cast(io.realm.OfferContentRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.OfferContent) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.Guest.class)) {
            return clazz.cast(io.realm.GuestRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.Guest) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.voyager.nearbystores_v2.classes.User.class)) {
            return clazz.cast(io.realm.UserRealmProxy.createDetachedCopy((com.voyager.nearbystores_v2.classes.User) realmObject, 0, maxDepth, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

}
