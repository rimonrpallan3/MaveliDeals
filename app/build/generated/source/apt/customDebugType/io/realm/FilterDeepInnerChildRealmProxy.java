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
public class FilterDeepInnerChildRealmProxy extends com.voyager.nearbystores_v2.classes.FilterDeepInnerChild
    implements RealmObjectProxy, FilterDeepInnerChildRealmProxyInterface {

    static final class FilterDeepInnerChildColumnInfo extends ColumnInfo {
        long numFiltIndex;
        long typeIndex;
        long nameFiltIndex;
        long parentCategoryIndex;
        long statusIndex;
        long menuIndex;

        FilterDeepInnerChildColumnInfo(OsSchemaInfo schemaInfo) {
            super(6);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("FilterDeepInnerChild");
            this.numFiltIndex = addColumnDetails("numFilt", objectSchemaInfo);
            this.typeIndex = addColumnDetails("type", objectSchemaInfo);
            this.nameFiltIndex = addColumnDetails("nameFilt", objectSchemaInfo);
            this.parentCategoryIndex = addColumnDetails("parentCategory", objectSchemaInfo);
            this.statusIndex = addColumnDetails("status", objectSchemaInfo);
            this.menuIndex = addColumnDetails("menu", objectSchemaInfo);
        }

        FilterDeepInnerChildColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new FilterDeepInnerChildColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final FilterDeepInnerChildColumnInfo src = (FilterDeepInnerChildColumnInfo) rawSrc;
            final FilterDeepInnerChildColumnInfo dst = (FilterDeepInnerChildColumnInfo) rawDst;
            dst.numFiltIndex = src.numFiltIndex;
            dst.typeIndex = src.typeIndex;
            dst.nameFiltIndex = src.nameFiltIndex;
            dst.parentCategoryIndex = src.parentCategoryIndex;
            dst.statusIndex = src.statusIndex;
            dst.menuIndex = src.menuIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(6);
        fieldNames.add("numFilt");
        fieldNames.add("type");
        fieldNames.add("nameFilt");
        fieldNames.add("parentCategory");
        fieldNames.add("status");
        fieldNames.add("menu");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private FilterDeepInnerChildColumnInfo columnInfo;
    private ProxyState<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild> proxyState;

    FilterDeepInnerChildRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (FilterDeepInnerChildColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public Integer realmGet$numFilt() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.numFiltIndex)) {
            return null;
        }
        return (int) proxyState.getRow$realm().getLong(columnInfo.numFiltIndex);
    }

    @Override
    public void realmSet$numFilt(Integer value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'numFilt' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$type() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.typeIndex);
    }

    @Override
    public void realmSet$type(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.typeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.typeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$nameFilt() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameFiltIndex);
    }

    @Override
    public void realmSet$nameFilt(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nameFiltIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameFiltIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameFiltIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameFiltIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$parentCategory() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.parentCategoryIndex);
    }

    @Override
    public void realmSet$parentCategory(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.parentCategoryIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.parentCategoryIndex, value);
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
    public boolean realmGet$menu() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.menuIndex);
    }

    @Override
    public void realmSet$menu(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.menuIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.menuIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("FilterDeepInnerChild", 6, 0);
        builder.addPersistedProperty("numFilt", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("type", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("nameFilt", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("parentCategory", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("status", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("menu", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static FilterDeepInnerChildColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new FilterDeepInnerChildColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "FilterDeepInnerChild";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.voyager.nearbystores_v2.classes.FilterDeepInnerChild createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.voyager.nearbystores_v2.classes.FilterDeepInnerChild obj = null;
        if (update) {
            Table table = realm.getTable(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class);
            FilterDeepInnerChildColumnInfo columnInfo = (FilterDeepInnerChildColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class);
            long pkColumnIndex = columnInfo.numFiltIndex;
            long rowIndex = Table.NO_MATCH;
            if (json.isNull("numFilt")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("numFilt"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class), false, Collections.<String> emptyList());
                    obj = new io.realm.FilterDeepInnerChildRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("numFilt")) {
                if (json.isNull("numFilt")) {
                    obj = (io.realm.FilterDeepInnerChildRealmProxy) realm.createObjectInternal(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.FilterDeepInnerChildRealmProxy) realm.createObjectInternal(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class, json.getInt("numFilt"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'numFilt'.");
            }
        }

        final FilterDeepInnerChildRealmProxyInterface objProxy = (FilterDeepInnerChildRealmProxyInterface) obj;
        if (json.has("type")) {
            if (json.isNull("type")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'type' to null.");
            } else {
                objProxy.realmSet$type((int) json.getInt("type"));
            }
        }
        if (json.has("nameFilt")) {
            if (json.isNull("nameFilt")) {
                objProxy.realmSet$nameFilt(null);
            } else {
                objProxy.realmSet$nameFilt((String) json.getString("nameFilt"));
            }
        }
        if (json.has("parentCategory")) {
            if (json.isNull("parentCategory")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'parentCategory' to null.");
            } else {
                objProxy.realmSet$parentCategory((int) json.getInt("parentCategory"));
            }
        }
        if (json.has("status")) {
            if (json.isNull("status")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'status' to null.");
            } else {
                objProxy.realmSet$status((int) json.getInt("status"));
            }
        }
        if (json.has("menu")) {
            if (json.isNull("menu")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'menu' to null.");
            } else {
                objProxy.realmSet$menu((boolean) json.getBoolean("menu"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.voyager.nearbystores_v2.classes.FilterDeepInnerChild createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.voyager.nearbystores_v2.classes.FilterDeepInnerChild obj = new com.voyager.nearbystores_v2.classes.FilterDeepInnerChild();
        final FilterDeepInnerChildRealmProxyInterface objProxy = (FilterDeepInnerChildRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("numFilt")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$numFilt((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$numFilt(null);
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$type((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'type' to null.");
                }
            } else if (name.equals("nameFilt")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$nameFilt((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$nameFilt(null);
                }
            } else if (name.equals("parentCategory")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$parentCategory((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'parentCategory' to null.");
                }
            } else if (name.equals("status")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$status((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'status' to null.");
                }
            } else if (name.equals("menu")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$menu((boolean) reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'menu' to null.");
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'numFilt'.");
        }
        return realm.copyToRealm(obj);
    }

    public static com.voyager.nearbystores_v2.classes.FilterDeepInnerChild copyOrUpdate(Realm realm, com.voyager.nearbystores_v2.classes.FilterDeepInnerChild object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
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
            return (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild) cachedRealmObject;
        }

        com.voyager.nearbystores_v2.classes.FilterDeepInnerChild realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class);
            FilterDeepInnerChildColumnInfo columnInfo = (FilterDeepInnerChildColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class);
            long pkColumnIndex = columnInfo.numFiltIndex;
            Number value = ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$numFilt();
            long rowIndex = Table.NO_MATCH;
            if (value == null) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstLong(pkColumnIndex, value.longValue());
            }
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.FilterDeepInnerChildRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, realmObject, object, cache) : copy(realm, object, update, cache);
    }

    public static com.voyager.nearbystores_v2.classes.FilterDeepInnerChild copy(Realm realm, com.voyager.nearbystores_v2.classes.FilterDeepInnerChild newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.voyager.nearbystores_v2.classes.FilterDeepInnerChild realmObject = realm.createObjectInternal(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class, ((FilterDeepInnerChildRealmProxyInterface) newObject).realmGet$numFilt(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        FilterDeepInnerChildRealmProxyInterface realmObjectSource = (FilterDeepInnerChildRealmProxyInterface) newObject;
        FilterDeepInnerChildRealmProxyInterface realmObjectCopy = (FilterDeepInnerChildRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectCopy.realmSet$nameFilt(realmObjectSource.realmGet$nameFilt());
        realmObjectCopy.realmSet$parentCategory(realmObjectSource.realmGet$parentCategory());
        realmObjectCopy.realmSet$status(realmObjectSource.realmGet$status());
        realmObjectCopy.realmSet$menu(realmObjectSource.realmGet$menu());
        return realmObject;
    }

    public static long insert(Realm realm, com.voyager.nearbystores_v2.classes.FilterDeepInnerChild object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class);
        long tableNativePtr = table.getNativePtr();
        FilterDeepInnerChildColumnInfo columnInfo = (FilterDeepInnerChildColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class);
        long pkColumnIndex = columnInfo.numFiltIndex;
        Object primaryKeyValue = ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$numFilt();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$numFilt());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$numFilt());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.typeIndex, rowIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$type(), false);
        String realmGet$nameFilt = ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$nameFilt();
        if (realmGet$nameFilt != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameFiltIndex, rowIndex, realmGet$nameFilt, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.parentCategoryIndex, rowIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$parentCategory(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$status(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.menuIndex, rowIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$menu(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class);
        long tableNativePtr = table.getNativePtr();
        FilterDeepInnerChildColumnInfo columnInfo = (FilterDeepInnerChildColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class);
        long pkColumnIndex = columnInfo.numFiltIndex;
        com.voyager.nearbystores_v2.classes.FilterDeepInnerChild object = null;
        while (objects.hasNext()) {
            object = (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            Object primaryKeyValue = ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$numFilt();
            long rowIndex = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
            } else {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$numFilt());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$numFilt());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.typeIndex, rowIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$type(), false);
            String realmGet$nameFilt = ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$nameFilt();
            if (realmGet$nameFilt != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameFiltIndex, rowIndex, realmGet$nameFilt, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.parentCategoryIndex, rowIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$parentCategory(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$status(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.menuIndex, rowIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$menu(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.voyager.nearbystores_v2.classes.FilterDeepInnerChild object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class);
        long tableNativePtr = table.getNativePtr();
        FilterDeepInnerChildColumnInfo columnInfo = (FilterDeepInnerChildColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class);
        long pkColumnIndex = columnInfo.numFiltIndex;
        Object primaryKeyValue = ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$numFilt();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$numFilt());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$numFilt());
        }
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.typeIndex, rowIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$type(), false);
        String realmGet$nameFilt = ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$nameFilt();
        if (realmGet$nameFilt != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameFiltIndex, rowIndex, realmGet$nameFilt, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameFiltIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.parentCategoryIndex, rowIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$parentCategory(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$status(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.menuIndex, rowIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$menu(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class);
        long tableNativePtr = table.getNativePtr();
        FilterDeepInnerChildColumnInfo columnInfo = (FilterDeepInnerChildColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class);
        long pkColumnIndex = columnInfo.numFiltIndex;
        com.voyager.nearbystores_v2.classes.FilterDeepInnerChild object = null;
        while (objects.hasNext()) {
            object = (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            Object primaryKeyValue = ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$numFilt();
            long rowIndex = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
            } else {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$numFilt());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$numFilt());
            }
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.typeIndex, rowIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$type(), false);
            String realmGet$nameFilt = ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$nameFilt();
            if (realmGet$nameFilt != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameFiltIndex, rowIndex, realmGet$nameFilt, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameFiltIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.parentCategoryIndex, rowIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$parentCategory(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$status(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.menuIndex, rowIndex, ((FilterDeepInnerChildRealmProxyInterface) object).realmGet$menu(), false);
        }
    }

    public static com.voyager.nearbystores_v2.classes.FilterDeepInnerChild createDetachedCopy(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.voyager.nearbystores_v2.classes.FilterDeepInnerChild unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.voyager.nearbystores_v2.classes.FilterDeepInnerChild();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild) cachedObject.object;
            }
            unmanagedObject = (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        FilterDeepInnerChildRealmProxyInterface unmanagedCopy = (FilterDeepInnerChildRealmProxyInterface) unmanagedObject;
        FilterDeepInnerChildRealmProxyInterface realmSource = (FilterDeepInnerChildRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$numFilt(realmSource.realmGet$numFilt());
        unmanagedCopy.realmSet$type(realmSource.realmGet$type());
        unmanagedCopy.realmSet$nameFilt(realmSource.realmGet$nameFilt());
        unmanagedCopy.realmSet$parentCategory(realmSource.realmGet$parentCategory());
        unmanagedCopy.realmSet$status(realmSource.realmGet$status());
        unmanagedCopy.realmSet$menu(realmSource.realmGet$menu());

        return unmanagedObject;
    }

    static com.voyager.nearbystores_v2.classes.FilterDeepInnerChild update(Realm realm, com.voyager.nearbystores_v2.classes.FilterDeepInnerChild realmObject, com.voyager.nearbystores_v2.classes.FilterDeepInnerChild newObject, Map<RealmModel, RealmObjectProxy> cache) {
        FilterDeepInnerChildRealmProxyInterface realmObjectTarget = (FilterDeepInnerChildRealmProxyInterface) realmObject;
        FilterDeepInnerChildRealmProxyInterface realmObjectSource = (FilterDeepInnerChildRealmProxyInterface) newObject;
        realmObjectTarget.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectTarget.realmSet$nameFilt(realmObjectSource.realmGet$nameFilt());
        realmObjectTarget.realmSet$parentCategory(realmObjectSource.realmGet$parentCategory());
        realmObjectTarget.realmSet$status(realmObjectSource.realmGet$status());
        realmObjectTarget.realmSet$menu(realmObjectSource.realmGet$menu());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("FilterDeepInnerChild = proxy[");
        stringBuilder.append("{numFilt:");
        stringBuilder.append(realmGet$numFilt() != null ? realmGet$numFilt() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{type:");
        stringBuilder.append(realmGet$type());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{nameFilt:");
        stringBuilder.append(realmGet$nameFilt() != null ? realmGet$nameFilt() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{parentCategory:");
        stringBuilder.append(realmGet$parentCategory());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{status:");
        stringBuilder.append(realmGet$status());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{menu:");
        stringBuilder.append(realmGet$menu());
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
        FilterDeepInnerChildRealmProxy aFilterDeepInnerChild = (FilterDeepInnerChildRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aFilterDeepInnerChild.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aFilterDeepInnerChild.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aFilterDeepInnerChild.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
