package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Property;
import io.realm.internal.ProxyUtils;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class OfferRealmProxy extends com.voyager.nearbystores_v2.classes.Offer
    implements RealmObjectProxy, OfferRealmProxyInterface {

    static final class OfferColumnInfo extends ColumnInfo {
        long idIndex;
        long contentIndex;
        long store_idIndex;
        long user_idIndex;
        long statusIndex;
        long date_startIndex;
        long date_endIndex;
        long nameIndex;
        long store_nameIndex;
        long imagesIndex;
        long distanceIndex;

        OfferColumnInfo(OsSchemaInfo schemaInfo) {
            super(11);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Offer");
            this.idIndex = addColumnDetails("id", objectSchemaInfo);
            this.contentIndex = addColumnDetails("content", objectSchemaInfo);
            this.store_idIndex = addColumnDetails("store_id", objectSchemaInfo);
            this.user_idIndex = addColumnDetails("user_id", objectSchemaInfo);
            this.statusIndex = addColumnDetails("status", objectSchemaInfo);
            this.date_startIndex = addColumnDetails("date_start", objectSchemaInfo);
            this.date_endIndex = addColumnDetails("date_end", objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.store_nameIndex = addColumnDetails("store_name", objectSchemaInfo);
            this.imagesIndex = addColumnDetails("images", objectSchemaInfo);
            this.distanceIndex = addColumnDetails("distance", objectSchemaInfo);
        }

        OfferColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new OfferColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final OfferColumnInfo src = (OfferColumnInfo) rawSrc;
            final OfferColumnInfo dst = (OfferColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.contentIndex = src.contentIndex;
            dst.store_idIndex = src.store_idIndex;
            dst.user_idIndex = src.user_idIndex;
            dst.statusIndex = src.statusIndex;
            dst.date_startIndex = src.date_startIndex;
            dst.date_endIndex = src.date_endIndex;
            dst.nameIndex = src.nameIndex;
            dst.store_nameIndex = src.store_nameIndex;
            dst.imagesIndex = src.imagesIndex;
            dst.distanceIndex = src.distanceIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(11);
        fieldNames.add("id");
        fieldNames.add("content");
        fieldNames.add("store_id");
        fieldNames.add("user_id");
        fieldNames.add("status");
        fieldNames.add("date_start");
        fieldNames.add("date_end");
        fieldNames.add("name");
        fieldNames.add("store_name");
        fieldNames.add("images");
        fieldNames.add("distance");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private OfferColumnInfo columnInfo;
    private ProxyState<com.voyager.nearbystores_v2.classes.Offer> proxyState;

    OfferRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (OfferColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.voyager.nearbystores_v2.classes.Offer>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idIndex);
    }

    @Override
    public void realmSet$id(int value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'id' cannot be changed after object was created.");
    }

    @Override
    public com.voyager.nearbystores_v2.classes.OfferContent realmGet$content() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNullLink(columnInfo.contentIndex)) {
            return null;
        }
        return proxyState.getRealm$realm().get(com.voyager.nearbystores_v2.classes.OfferContent.class, proxyState.getRow$realm().getLink(columnInfo.contentIndex), false, Collections.<String>emptyList());
    }

    @Override
    public void realmSet$content(com.voyager.nearbystores_v2.classes.OfferContent value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("content")) {
                return;
            }
            if (value != null && !RealmObject.isManaged(value)) {
                value = ((Realm) proxyState.getRealm$realm()).copyToRealm(value);
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                // Table#nullifyLink() does not support default value. Just using Row.
                row.nullifyLink(columnInfo.contentIndex);
                return;
            }
            proxyState.checkValidObject(value);
            row.getTable().setLink(columnInfo.contentIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().nullifyLink(columnInfo.contentIndex);
            return;
        }
        proxyState.checkValidObject(value);
        proxyState.getRow$realm().setLink(columnInfo.contentIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$store_id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.store_idIndex);
    }

    @Override
    public void realmSet$store_id(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.store_idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.store_idIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$user_id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.user_idIndex);
    }

    @Override
    public void realmSet$user_id(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.user_idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.user_idIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$status() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.statusIndex);
    }

    @Override
    public void realmSet$status(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.statusIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.statusIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$date_start() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.date_startIndex);
    }

    @Override
    public void realmSet$date_start(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.date_startIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.date_startIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.date_startIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.date_startIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$date_end() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.date_endIndex);
    }

    @Override
    public void realmSet$date_end(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.date_endIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.date_endIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.date_endIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.date_endIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameIndex);
    }

    @Override
    public void realmSet$name(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$store_name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.store_nameIndex);
    }

    @Override
    public void realmSet$store_name(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.store_nameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.store_nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.store_nameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.store_nameIndex, value);
    }

    @Override
    public com.voyager.nearbystores_v2.classes.Images realmGet$images() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNullLink(columnInfo.imagesIndex)) {
            return null;
        }
        return proxyState.getRealm$realm().get(com.voyager.nearbystores_v2.classes.Images.class, proxyState.getRow$realm().getLink(columnInfo.imagesIndex), false, Collections.<String>emptyList());
    }

    @Override
    public void realmSet$images(com.voyager.nearbystores_v2.classes.Images value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("images")) {
                return;
            }
            if (value != null && !RealmObject.isManaged(value)) {
                value = ((Realm) proxyState.getRealm$realm()).copyToRealm(value);
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                // Table#nullifyLink() does not support default value. Just using Row.
                row.nullifyLink(columnInfo.imagesIndex);
                return;
            }
            proxyState.checkValidObject(value);
            row.getTable().setLink(columnInfo.imagesIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().nullifyLink(columnInfo.imagesIndex);
            return;
        }
        proxyState.checkValidObject(value);
        proxyState.getRow$realm().setLink(columnInfo.imagesIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
    }

    @Override
    @SuppressWarnings("cast")
    public Double realmGet$distance() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.distanceIndex)) {
            return null;
        }
        return (double) proxyState.getRow$realm().getDouble(columnInfo.distanceIndex);
    }

    @Override
    public void realmSet$distance(Double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.distanceIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setDouble(columnInfo.distanceIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.distanceIndex);
            return;
        }
        proxyState.getRow$realm().setDouble(columnInfo.distanceIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Offer", 11, 0);
        builder.addPersistedProperty("id", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addPersistedLinkProperty("content", RealmFieldType.OBJECT, "OfferContent");
        builder.addPersistedProperty("store_id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("user_id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("status", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("date_start", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("date_end", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("store_name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedLinkProperty("images", RealmFieldType.OBJECT, "Images");
        builder.addPersistedProperty("distance", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static OfferColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new OfferColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Offer";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.voyager.nearbystores_v2.classes.Offer createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(2);
        com.voyager.nearbystores_v2.classes.Offer obj = null;
        if (update) {
            Table table = realm.getTable(com.voyager.nearbystores_v2.classes.Offer.class);
            OfferColumnInfo columnInfo = (OfferColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.Offer.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.Offer.class), false, Collections.<String> emptyList());
                    obj = new io.realm.OfferRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("content")) {
                excludeFields.add("content");
            }
            if (json.has("images")) {
                excludeFields.add("images");
            }
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.OfferRealmProxy) realm.createObjectInternal(com.voyager.nearbystores_v2.classes.Offer.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.OfferRealmProxy) realm.createObjectInternal(com.voyager.nearbystores_v2.classes.Offer.class, json.getInt("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final OfferRealmProxyInterface objProxy = (OfferRealmProxyInterface) obj;
        if (json.has("content")) {
            if (json.isNull("content")) {
                objProxy.realmSet$content(null);
            } else {
                com.voyager.nearbystores_v2.classes.OfferContent contentObj = OfferContentRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("content"), update);
                objProxy.realmSet$content(contentObj);
            }
        }
        if (json.has("store_id")) {
            if (json.isNull("store_id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'store_id' to null.");
            } else {
                objProxy.realmSet$store_id((int) json.getInt("store_id"));
            }
        }
        if (json.has("user_id")) {
            if (json.isNull("user_id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'user_id' to null.");
            } else {
                objProxy.realmSet$user_id((int) json.getInt("user_id"));
            }
        }
        if (json.has("status")) {
            if (json.isNull("status")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'status' to null.");
            } else {
                objProxy.realmSet$status((int) json.getInt("status"));
            }
        }
        if (json.has("date_start")) {
            if (json.isNull("date_start")) {
                objProxy.realmSet$date_start(null);
            } else {
                objProxy.realmSet$date_start((String) json.getString("date_start"));
            }
        }
        if (json.has("date_end")) {
            if (json.isNull("date_end")) {
                objProxy.realmSet$date_end(null);
            } else {
                objProxy.realmSet$date_end((String) json.getString("date_end"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("store_name")) {
            if (json.isNull("store_name")) {
                objProxy.realmSet$store_name(null);
            } else {
                objProxy.realmSet$store_name((String) json.getString("store_name"));
            }
        }
        if (json.has("images")) {
            if (json.isNull("images")) {
                objProxy.realmSet$images(null);
            } else {
                com.voyager.nearbystores_v2.classes.Images imagesObj = ImagesRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("images"), update);
                objProxy.realmSet$images(imagesObj);
            }
        }
        if (json.has("distance")) {
            if (json.isNull("distance")) {
                objProxy.realmSet$distance(null);
            } else {
                objProxy.realmSet$distance((double) json.getDouble("distance"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.voyager.nearbystores_v2.classes.Offer createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.voyager.nearbystores_v2.classes.Offer obj = new com.voyager.nearbystores_v2.classes.Offer();
        final OfferRealmProxyInterface objProxy = (OfferRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("content")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$content(null);
                } else {
                    com.voyager.nearbystores_v2.classes.OfferContent contentObj = OfferContentRealmProxy.createUsingJsonStream(realm, reader);
                    objProxy.realmSet$content(contentObj);
                }
            } else if (name.equals("store_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$store_id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'store_id' to null.");
                }
            } else if (name.equals("user_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$user_id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'user_id' to null.");
                }
            } else if (name.equals("status")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$status((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'status' to null.");
                }
            } else if (name.equals("date_start")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$date_start((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$date_start(null);
                }
            } else if (name.equals("date_end")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$date_end((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$date_end(null);
                }
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("store_name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$store_name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$store_name(null);
                }
            } else if (name.equals("images")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$images(null);
                } else {
                    com.voyager.nearbystores_v2.classes.Images imagesObj = ImagesRealmProxy.createUsingJsonStream(realm, reader);
                    objProxy.realmSet$images(imagesObj);
                }
            } else if (name.equals("distance")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$distance((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$distance(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
        }
        return realm.copyToRealm(obj);
    }

    public static com.voyager.nearbystores_v2.classes.Offer copyOrUpdate(Realm realm, com.voyager.nearbystores_v2.classes.Offer object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.voyager.nearbystores_v2.classes.Offer) cachedRealmObject;
        }

        com.voyager.nearbystores_v2.classes.Offer realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.voyager.nearbystores_v2.classes.Offer.class);
            OfferColumnInfo columnInfo = (OfferColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.Offer.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = table.findFirstLong(pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.Offer.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.OfferRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, realmObject, object, cache) : copy(realm, object, update, cache);
    }

    public static com.voyager.nearbystores_v2.classes.Offer copy(Realm realm, com.voyager.nearbystores_v2.classes.Offer newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.voyager.nearbystores_v2.classes.Offer) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.voyager.nearbystores_v2.classes.Offer realmObject = realm.createObjectInternal(com.voyager.nearbystores_v2.classes.Offer.class, ((OfferRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        OfferRealmProxyInterface realmObjectSource = (OfferRealmProxyInterface) newObject;
        OfferRealmProxyInterface realmObjectCopy = (OfferRealmProxyInterface) realmObject;


        com.voyager.nearbystores_v2.classes.OfferContent contentObj = realmObjectSource.realmGet$content();
        if (contentObj == null) {
            realmObjectCopy.realmSet$content(null);
        } else {
            com.voyager.nearbystores_v2.classes.OfferContent cachecontent = (com.voyager.nearbystores_v2.classes.OfferContent) cache.get(contentObj);
            if (cachecontent != null) {
                realmObjectCopy.realmSet$content(cachecontent);
            } else {
                realmObjectCopy.realmSet$content(OfferContentRealmProxy.copyOrUpdate(realm, contentObj, update, cache));
            }
        }
        realmObjectCopy.realmSet$store_id(realmObjectSource.realmGet$store_id());
        realmObjectCopy.realmSet$user_id(realmObjectSource.realmGet$user_id());
        realmObjectCopy.realmSet$status(realmObjectSource.realmGet$status());
        realmObjectCopy.realmSet$date_start(realmObjectSource.realmGet$date_start());
        realmObjectCopy.realmSet$date_end(realmObjectSource.realmGet$date_end());
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$store_name(realmObjectSource.realmGet$store_name());

        com.voyager.nearbystores_v2.classes.Images imagesObj = realmObjectSource.realmGet$images();
        if (imagesObj == null) {
            realmObjectCopy.realmSet$images(null);
        } else {
            com.voyager.nearbystores_v2.classes.Images cacheimages = (com.voyager.nearbystores_v2.classes.Images) cache.get(imagesObj);
            if (cacheimages != null) {
                realmObjectCopy.realmSet$images(cacheimages);
            } else {
                realmObjectCopy.realmSet$images(ImagesRealmProxy.copyOrUpdate(realm, imagesObj, update, cache));
            }
        }
        realmObjectCopy.realmSet$distance(realmObjectSource.realmGet$distance());
        return realmObject;
    }

    public static long insert(Realm realm, com.voyager.nearbystores_v2.classes.Offer object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.voyager.nearbystores_v2.classes.Offer.class);
        long tableNativePtr = table.getNativePtr();
        OfferColumnInfo columnInfo = (OfferColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.Offer.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((OfferRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);

        com.voyager.nearbystores_v2.classes.OfferContent contentObj = ((OfferRealmProxyInterface) object).realmGet$content();
        if (contentObj != null) {
            Long cachecontent = cache.get(contentObj);
            if (cachecontent == null) {
                cachecontent = OfferContentRealmProxy.insert(realm, contentObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.contentIndex, rowIndex, cachecontent, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.store_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$store_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$user_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$status(), false);
        String realmGet$date_start = ((OfferRealmProxyInterface) object).realmGet$date_start();
        if (realmGet$date_start != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.date_startIndex, rowIndex, realmGet$date_start, false);
        }
        String realmGet$date_end = ((OfferRealmProxyInterface) object).realmGet$date_end();
        if (realmGet$date_end != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.date_endIndex, rowIndex, realmGet$date_end, false);
        }
        String realmGet$name = ((OfferRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$store_name = ((OfferRealmProxyInterface) object).realmGet$store_name();
        if (realmGet$store_name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.store_nameIndex, rowIndex, realmGet$store_name, false);
        }

        com.voyager.nearbystores_v2.classes.Images imagesObj = ((OfferRealmProxyInterface) object).realmGet$images();
        if (imagesObj != null) {
            Long cacheimages = cache.get(imagesObj);
            if (cacheimages == null) {
                cacheimages = ImagesRealmProxy.insert(realm, imagesObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.imagesIndex, rowIndex, cacheimages, false);
        }
        Double realmGet$distance = ((OfferRealmProxyInterface) object).realmGet$distance();
        if (realmGet$distance != null) {
            Table.nativeSetDouble(tableNativePtr, columnInfo.distanceIndex, rowIndex, realmGet$distance, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.voyager.nearbystores_v2.classes.Offer.class);
        long tableNativePtr = table.getNativePtr();
        OfferColumnInfo columnInfo = (OfferColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.Offer.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.voyager.nearbystores_v2.classes.Offer object = null;
        while (objects.hasNext()) {
            object = (com.voyager.nearbystores_v2.classes.Offer) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((OfferRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);

            com.voyager.nearbystores_v2.classes.OfferContent contentObj = ((OfferRealmProxyInterface) object).realmGet$content();
            if (contentObj != null) {
                Long cachecontent = cache.get(contentObj);
                if (cachecontent == null) {
                    cachecontent = OfferContentRealmProxy.insert(realm, contentObj, cache);
                }
                table.setLink(columnInfo.contentIndex, rowIndex, cachecontent, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.store_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$store_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$user_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$status(), false);
            String realmGet$date_start = ((OfferRealmProxyInterface) object).realmGet$date_start();
            if (realmGet$date_start != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.date_startIndex, rowIndex, realmGet$date_start, false);
            }
            String realmGet$date_end = ((OfferRealmProxyInterface) object).realmGet$date_end();
            if (realmGet$date_end != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.date_endIndex, rowIndex, realmGet$date_end, false);
            }
            String realmGet$name = ((OfferRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            }
            String realmGet$store_name = ((OfferRealmProxyInterface) object).realmGet$store_name();
            if (realmGet$store_name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.store_nameIndex, rowIndex, realmGet$store_name, false);
            }

            com.voyager.nearbystores_v2.classes.Images imagesObj = ((OfferRealmProxyInterface) object).realmGet$images();
            if (imagesObj != null) {
                Long cacheimages = cache.get(imagesObj);
                if (cacheimages == null) {
                    cacheimages = ImagesRealmProxy.insert(realm, imagesObj, cache);
                }
                table.setLink(columnInfo.imagesIndex, rowIndex, cacheimages, false);
            }
            Double realmGet$distance = ((OfferRealmProxyInterface) object).realmGet$distance();
            if (realmGet$distance != null) {
                Table.nativeSetDouble(tableNativePtr, columnInfo.distanceIndex, rowIndex, realmGet$distance, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.voyager.nearbystores_v2.classes.Offer object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.voyager.nearbystores_v2.classes.Offer.class);
        long tableNativePtr = table.getNativePtr();
        OfferColumnInfo columnInfo = (OfferColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.Offer.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((OfferRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
        }
        cache.put(object, rowIndex);

        com.voyager.nearbystores_v2.classes.OfferContent contentObj = ((OfferRealmProxyInterface) object).realmGet$content();
        if (contentObj != null) {
            Long cachecontent = cache.get(contentObj);
            if (cachecontent == null) {
                cachecontent = OfferContentRealmProxy.insertOrUpdate(realm, contentObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.contentIndex, rowIndex, cachecontent, false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.contentIndex, rowIndex);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.store_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$store_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$user_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$status(), false);
        String realmGet$date_start = ((OfferRealmProxyInterface) object).realmGet$date_start();
        if (realmGet$date_start != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.date_startIndex, rowIndex, realmGet$date_start, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.date_startIndex, rowIndex, false);
        }
        String realmGet$date_end = ((OfferRealmProxyInterface) object).realmGet$date_end();
        if (realmGet$date_end != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.date_endIndex, rowIndex, realmGet$date_end, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.date_endIndex, rowIndex, false);
        }
        String realmGet$name = ((OfferRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$store_name = ((OfferRealmProxyInterface) object).realmGet$store_name();
        if (realmGet$store_name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.store_nameIndex, rowIndex, realmGet$store_name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.store_nameIndex, rowIndex, false);
        }

        com.voyager.nearbystores_v2.classes.Images imagesObj = ((OfferRealmProxyInterface) object).realmGet$images();
        if (imagesObj != null) {
            Long cacheimages = cache.get(imagesObj);
            if (cacheimages == null) {
                cacheimages = ImagesRealmProxy.insertOrUpdate(realm, imagesObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.imagesIndex, rowIndex, cacheimages, false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.imagesIndex, rowIndex);
        }
        Double realmGet$distance = ((OfferRealmProxyInterface) object).realmGet$distance();
        if (realmGet$distance != null) {
            Table.nativeSetDouble(tableNativePtr, columnInfo.distanceIndex, rowIndex, realmGet$distance, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.distanceIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.voyager.nearbystores_v2.classes.Offer.class);
        long tableNativePtr = table.getNativePtr();
        OfferColumnInfo columnInfo = (OfferColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.Offer.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.voyager.nearbystores_v2.classes.Offer object = null;
        while (objects.hasNext()) {
            object = (com.voyager.nearbystores_v2.classes.Offer) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((OfferRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
            }
            cache.put(object, rowIndex);

            com.voyager.nearbystores_v2.classes.OfferContent contentObj = ((OfferRealmProxyInterface) object).realmGet$content();
            if (contentObj != null) {
                Long cachecontent = cache.get(contentObj);
                if (cachecontent == null) {
                    cachecontent = OfferContentRealmProxy.insertOrUpdate(realm, contentObj, cache);
                }
                Table.nativeSetLink(tableNativePtr, columnInfo.contentIndex, rowIndex, cachecontent, false);
            } else {
                Table.nativeNullifyLink(tableNativePtr, columnInfo.contentIndex, rowIndex);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.store_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$store_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$user_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$status(), false);
            String realmGet$date_start = ((OfferRealmProxyInterface) object).realmGet$date_start();
            if (realmGet$date_start != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.date_startIndex, rowIndex, realmGet$date_start, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.date_startIndex, rowIndex, false);
            }
            String realmGet$date_end = ((OfferRealmProxyInterface) object).realmGet$date_end();
            if (realmGet$date_end != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.date_endIndex, rowIndex, realmGet$date_end, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.date_endIndex, rowIndex, false);
            }
            String realmGet$name = ((OfferRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
            }
            String realmGet$store_name = ((OfferRealmProxyInterface) object).realmGet$store_name();
            if (realmGet$store_name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.store_nameIndex, rowIndex, realmGet$store_name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.store_nameIndex, rowIndex, false);
            }

            com.voyager.nearbystores_v2.classes.Images imagesObj = ((OfferRealmProxyInterface) object).realmGet$images();
            if (imagesObj != null) {
                Long cacheimages = cache.get(imagesObj);
                if (cacheimages == null) {
                    cacheimages = ImagesRealmProxy.insertOrUpdate(realm, imagesObj, cache);
                }
                Table.nativeSetLink(tableNativePtr, columnInfo.imagesIndex, rowIndex, cacheimages, false);
            } else {
                Table.nativeNullifyLink(tableNativePtr, columnInfo.imagesIndex, rowIndex);
            }
            Double realmGet$distance = ((OfferRealmProxyInterface) object).realmGet$distance();
            if (realmGet$distance != null) {
                Table.nativeSetDouble(tableNativePtr, columnInfo.distanceIndex, rowIndex, realmGet$distance, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.distanceIndex, rowIndex, false);
            }
        }
    }

    public static com.voyager.nearbystores_v2.classes.Offer createDetachedCopy(com.voyager.nearbystores_v2.classes.Offer realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.voyager.nearbystores_v2.classes.Offer unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.voyager.nearbystores_v2.classes.Offer();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.voyager.nearbystores_v2.classes.Offer) cachedObject.object;
            }
            unmanagedObject = (com.voyager.nearbystores_v2.classes.Offer) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        OfferRealmProxyInterface unmanagedCopy = (OfferRealmProxyInterface) unmanagedObject;
        OfferRealmProxyInterface realmSource = (OfferRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());

        // Deep copy of content
        unmanagedCopy.realmSet$content(OfferContentRealmProxy.createDetachedCopy(realmSource.realmGet$content(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$store_id(realmSource.realmGet$store_id());
        unmanagedCopy.realmSet$user_id(realmSource.realmGet$user_id());
        unmanagedCopy.realmSet$status(realmSource.realmGet$status());
        unmanagedCopy.realmSet$date_start(realmSource.realmGet$date_start());
        unmanagedCopy.realmSet$date_end(realmSource.realmGet$date_end());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$store_name(realmSource.realmGet$store_name());

        // Deep copy of images
        unmanagedCopy.realmSet$images(ImagesRealmProxy.createDetachedCopy(realmSource.realmGet$images(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$distance(realmSource.realmGet$distance());

        return unmanagedObject;
    }

    static com.voyager.nearbystores_v2.classes.Offer update(Realm realm, com.voyager.nearbystores_v2.classes.Offer realmObject, com.voyager.nearbystores_v2.classes.Offer newObject, Map<RealmModel, RealmObjectProxy> cache) {
        OfferRealmProxyInterface realmObjectTarget = (OfferRealmProxyInterface) realmObject;
        OfferRealmProxyInterface realmObjectSource = (OfferRealmProxyInterface) newObject;
        com.voyager.nearbystores_v2.classes.OfferContent contentObj = realmObjectSource.realmGet$content();
        if (contentObj == null) {
            realmObjectTarget.realmSet$content(null);
        } else {
            com.voyager.nearbystores_v2.classes.OfferContent cachecontent = (com.voyager.nearbystores_v2.classes.OfferContent) cache.get(contentObj);
            if (cachecontent != null) {
                realmObjectTarget.realmSet$content(cachecontent);
            } else {
                realmObjectTarget.realmSet$content(OfferContentRealmProxy.copyOrUpdate(realm, contentObj, true, cache));
            }
        }
        realmObjectTarget.realmSet$store_id(realmObjectSource.realmGet$store_id());
        realmObjectTarget.realmSet$user_id(realmObjectSource.realmGet$user_id());
        realmObjectTarget.realmSet$status(realmObjectSource.realmGet$status());
        realmObjectTarget.realmSet$date_start(realmObjectSource.realmGet$date_start());
        realmObjectTarget.realmSet$date_end(realmObjectSource.realmGet$date_end());
        realmObjectTarget.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectTarget.realmSet$store_name(realmObjectSource.realmGet$store_name());
        com.voyager.nearbystores_v2.classes.Images imagesObj = realmObjectSource.realmGet$images();
        if (imagesObj == null) {
            realmObjectTarget.realmSet$images(null);
        } else {
            com.voyager.nearbystores_v2.classes.Images cacheimages = (com.voyager.nearbystores_v2.classes.Images) cache.get(imagesObj);
            if (cacheimages != null) {
                realmObjectTarget.realmSet$images(cacheimages);
            } else {
                realmObjectTarget.realmSet$images(ImagesRealmProxy.copyOrUpdate(realm, imagesObj, true, cache));
            }
        }
        realmObjectTarget.realmSet$distance(realmObjectSource.realmGet$distance());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Offer = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{content:");
        stringBuilder.append(realmGet$content() != null ? "OfferContent" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{store_id:");
        stringBuilder.append(realmGet$store_id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{user_id:");
        stringBuilder.append(realmGet$user_id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{status:");
        stringBuilder.append(realmGet$status());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{date_start:");
        stringBuilder.append(realmGet$date_start() != null ? realmGet$date_start() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{date_end:");
        stringBuilder.append(realmGet$date_end() != null ? realmGet$date_end() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{store_name:");
        stringBuilder.append(realmGet$store_name() != null ? realmGet$store_name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{images:");
        stringBuilder.append(realmGet$images() != null ? "Images" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{distance:");
        stringBuilder.append(realmGet$distance() != null ? realmGet$distance() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferRealmProxy aOffer = (OfferRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aOffer.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aOffer.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aOffer.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
