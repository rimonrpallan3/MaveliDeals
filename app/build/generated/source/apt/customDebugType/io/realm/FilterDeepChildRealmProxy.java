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
public class FilterDeepChildRealmProxy extends com.voyager.nearbystores_v2.classes.FilterDeepChild
    implements RealmObjectProxy, FilterDeepChildRealmProxyInterface {

    static final class FilterDeepChildColumnInfo extends ColumnInfo {
        long numFiltIndex;
        long typeIndex;
        long nameFiltIndex;
        long parentCategoryIndex;
        long statusIndex;
        long menuIndex;
        long filterDeepInnerChildrenIndex;

        FilterDeepChildColumnInfo(OsSchemaInfo schemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("FilterDeepChild");
            this.numFiltIndex = addColumnDetails("numFilt", objectSchemaInfo);
            this.typeIndex = addColumnDetails("type", objectSchemaInfo);
            this.nameFiltIndex = addColumnDetails("nameFilt", objectSchemaInfo);
            this.parentCategoryIndex = addColumnDetails("parentCategory", objectSchemaInfo);
            this.statusIndex = addColumnDetails("status", objectSchemaInfo);
            this.menuIndex = addColumnDetails("menu", objectSchemaInfo);
            this.filterDeepInnerChildrenIndex = addColumnDetails("filterDeepInnerChildren", objectSchemaInfo);
        }

        FilterDeepChildColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new FilterDeepChildColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final FilterDeepChildColumnInfo src = (FilterDeepChildColumnInfo) rawSrc;
            final FilterDeepChildColumnInfo dst = (FilterDeepChildColumnInfo) rawDst;
            dst.numFiltIndex = src.numFiltIndex;
            dst.typeIndex = src.typeIndex;
            dst.nameFiltIndex = src.nameFiltIndex;
            dst.parentCategoryIndex = src.parentCategoryIndex;
            dst.statusIndex = src.statusIndex;
            dst.menuIndex = src.menuIndex;
            dst.filterDeepInnerChildrenIndex = src.filterDeepInnerChildrenIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(7);
        fieldNames.add("numFilt");
        fieldNames.add("type");
        fieldNames.add("nameFilt");
        fieldNames.add("parentCategory");
        fieldNames.add("status");
        fieldNames.add("menu");
        fieldNames.add("filterDeepInnerChildren");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private FilterDeepChildColumnInfo columnInfo;
    private ProxyState<com.voyager.nearbystores_v2.classes.FilterDeepChild> proxyState;
    private RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild> filterDeepInnerChildrenRealmList;

    FilterDeepChildRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (FilterDeepChildColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.voyager.nearbystores_v2.classes.FilterDeepChild>(this);
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

    @Override
    public RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild> realmGet$filterDeepInnerChildren() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (filterDeepInnerChildrenRealmList != null) {
            return filterDeepInnerChildrenRealmList;
        } else {
            OsList osList = proxyState.getRow$realm().getModelList(columnInfo.filterDeepInnerChildrenIndex);
            filterDeepInnerChildrenRealmList = new RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild>(com.voyager.nearbystores_v2.classes.FilterDeepInnerChild.class, osList, proxyState.getRealm$realm());
            return filterDeepInnerChildrenRealmList;
        }
    }

    @Override
    public void realmSet$filterDeepInnerChildren(RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("filterDeepInnerChildren")) {
                return;
            }
            // if the list contains unmanaged RealmObjects, convert them to managed.
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild> original = value;
                value = new RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild>();
                for (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        OsList osList = proxyState.getRow$realm().getModelList(columnInfo.filterDeepInnerChildrenIndex);
        // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
        if (value != null && value.size() == osList.size()) {
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.voyager.nearbystores_v2.classes.FilterDeepInnerChild linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.setRow(i, ((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else {
            osList.removeAll();
            if (value == null) {
                return;
            }
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.voyager.nearbystores_v2.classes.FilterDeepInnerChild linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.addRow(((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getIndex());
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("FilterDeepChild", 7, 0);
        builder.addPersistedProperty("numFilt", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("type", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("nameFilt", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("parentCategory", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("status", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("menu", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedLinkProperty("filterDeepInnerChildren", RealmFieldType.LIST, "FilterDeepInnerChild");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static FilterDeepChildColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new FilterDeepChildColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "FilterDeepChild";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.voyager.nearbystores_v2.classes.FilterDeepChild createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        com.voyager.nearbystores_v2.classes.FilterDeepChild obj = null;
        if (update) {
            Table table = realm.getTable(com.voyager.nearbystores_v2.classes.FilterDeepChild.class);
            FilterDeepChildColumnInfo columnInfo = (FilterDeepChildColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.FilterDeepChild.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.FilterDeepChild.class), false, Collections.<String> emptyList());
                    obj = new io.realm.FilterDeepChildRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("filterDeepInnerChildren")) {
                excludeFields.add("filterDeepInnerChildren");
            }
            if (json.has("numFilt")) {
                if (json.isNull("numFilt")) {
                    obj = (io.realm.FilterDeepChildRealmProxy) realm.createObjectInternal(com.voyager.nearbystores_v2.classes.FilterDeepChild.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.FilterDeepChildRealmProxy) realm.createObjectInternal(com.voyager.nearbystores_v2.classes.FilterDeepChild.class, json.getInt("numFilt"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'numFilt'.");
            }
        }

        final FilterDeepChildRealmProxyInterface objProxy = (FilterDeepChildRealmProxyInterface) obj;
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
        if (json.has("filterDeepInnerChildren")) {
            if (json.isNull("filterDeepInnerChildren")) {
                objProxy.realmSet$filterDeepInnerChildren(null);
            } else {
                objProxy.realmGet$filterDeepInnerChildren().clear();
                JSONArray array = json.getJSONArray("filterDeepInnerChildren");
                for (int i = 0; i < array.length(); i++) {
                    com.voyager.nearbystores_v2.classes.FilterDeepInnerChild item = FilterDeepInnerChildRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    objProxy.realmGet$filterDeepInnerChildren().add(item);
                }
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.voyager.nearbystores_v2.classes.FilterDeepChild createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.voyager.nearbystores_v2.classes.FilterDeepChild obj = new com.voyager.nearbystores_v2.classes.FilterDeepChild();
        final FilterDeepChildRealmProxyInterface objProxy = (FilterDeepChildRealmProxyInterface) obj;
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
            } else if (name.equals("filterDeepInnerChildren")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$filterDeepInnerChildren(null);
                } else {
                    objProxy.realmSet$filterDeepInnerChildren(new RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.voyager.nearbystores_v2.classes.FilterDeepInnerChild item = FilterDeepInnerChildRealmProxy.createUsingJsonStream(realm, reader);
                        objProxy.realmGet$filterDeepInnerChildren().add(item);
                    }
                    reader.endArray();
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

    public static com.voyager.nearbystores_v2.classes.FilterDeepChild copyOrUpdate(Realm realm, com.voyager.nearbystores_v2.classes.FilterDeepChild object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
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
            return (com.voyager.nearbystores_v2.classes.FilterDeepChild) cachedRealmObject;
        }

        com.voyager.nearbystores_v2.classes.FilterDeepChild realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.voyager.nearbystores_v2.classes.FilterDeepChild.class);
            FilterDeepChildColumnInfo columnInfo = (FilterDeepChildColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.FilterDeepChild.class);
            long pkColumnIndex = columnInfo.numFiltIndex;
            Number value = ((FilterDeepChildRealmProxyInterface) object).realmGet$numFilt();
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.FilterDeepChild.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.FilterDeepChildRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, realmObject, object, cache) : copy(realm, object, update, cache);
    }

    public static com.voyager.nearbystores_v2.classes.FilterDeepChild copy(Realm realm, com.voyager.nearbystores_v2.classes.FilterDeepChild newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.voyager.nearbystores_v2.classes.FilterDeepChild) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.voyager.nearbystores_v2.classes.FilterDeepChild realmObject = realm.createObjectInternal(com.voyager.nearbystores_v2.classes.FilterDeepChild.class, ((FilterDeepChildRealmProxyInterface) newObject).realmGet$numFilt(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        FilterDeepChildRealmProxyInterface realmObjectSource = (FilterDeepChildRealmProxyInterface) newObject;
        FilterDeepChildRealmProxyInterface realmObjectCopy = (FilterDeepChildRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectCopy.realmSet$nameFilt(realmObjectSource.realmGet$nameFilt());
        realmObjectCopy.realmSet$parentCategory(realmObjectSource.realmGet$parentCategory());
        realmObjectCopy.realmSet$status(realmObjectSource.realmGet$status());
        realmObjectCopy.realmSet$menu(realmObjectSource.realmGet$menu());

        RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild> filterDeepInnerChildrenList = realmObjectSource.realmGet$filterDeepInnerChildren();
        if (filterDeepInnerChildrenList != null) {
            RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild> filterDeepInnerChildrenRealmList = realmObjectCopy.realmGet$filterDeepInnerChildren();
            filterDeepInnerChildrenRealmList.clear();
            for (int i = 0; i < filterDeepInnerChildrenList.size(); i++) {
                com.voyager.nearbystores_v2.classes.FilterDeepInnerChild filterDeepInnerChildrenItem = filterDeepInnerChildrenList.get(i);
                com.voyager.nearbystores_v2.classes.FilterDeepInnerChild cachefilterDeepInnerChildren = (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild) cache.get(filterDeepInnerChildrenItem);
                if (cachefilterDeepInnerChildren != null) {
                    filterDeepInnerChildrenRealmList.add(cachefilterDeepInnerChildren);
                } else {
                    filterDeepInnerChildrenRealmList.add(FilterDeepInnerChildRealmProxy.copyOrUpdate(realm, filterDeepInnerChildrenItem, update, cache));
                }
            }
        }

        return realmObject;
    }

    public static long insert(Realm realm, com.voyager.nearbystores_v2.classes.FilterDeepChild object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.voyager.nearbystores_v2.classes.FilterDeepChild.class);
        long tableNativePtr = table.getNativePtr();
        FilterDeepChildColumnInfo columnInfo = (FilterDeepChildColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.FilterDeepChild.class);
        long pkColumnIndex = columnInfo.numFiltIndex;
        Object primaryKeyValue = ((FilterDeepChildRealmProxyInterface) object).realmGet$numFilt();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$numFilt());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$numFilt());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.typeIndex, rowIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$type(), false);
        String realmGet$nameFilt = ((FilterDeepChildRealmProxyInterface) object).realmGet$nameFilt();
        if (realmGet$nameFilt != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameFiltIndex, rowIndex, realmGet$nameFilt, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.parentCategoryIndex, rowIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$parentCategory(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$status(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.menuIndex, rowIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$menu(), false);

        RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild> filterDeepInnerChildrenList = ((FilterDeepChildRealmProxyInterface) object).realmGet$filterDeepInnerChildren();
        if (filterDeepInnerChildrenList != null) {
            OsList filterDeepInnerChildrenOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.filterDeepInnerChildrenIndex);
            for (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild filterDeepInnerChildrenItem : filterDeepInnerChildrenList) {
                Long cacheItemIndexfilterDeepInnerChildren = cache.get(filterDeepInnerChildrenItem);
                if (cacheItemIndexfilterDeepInnerChildren == null) {
                    cacheItemIndexfilterDeepInnerChildren = FilterDeepInnerChildRealmProxy.insert(realm, filterDeepInnerChildrenItem, cache);
                }
                filterDeepInnerChildrenOsList.addRow(cacheItemIndexfilterDeepInnerChildren);
            }
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.voyager.nearbystores_v2.classes.FilterDeepChild.class);
        long tableNativePtr = table.getNativePtr();
        FilterDeepChildColumnInfo columnInfo = (FilterDeepChildColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.FilterDeepChild.class);
        long pkColumnIndex = columnInfo.numFiltIndex;
        com.voyager.nearbystores_v2.classes.FilterDeepChild object = null;
        while (objects.hasNext()) {
            object = (com.voyager.nearbystores_v2.classes.FilterDeepChild) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            Object primaryKeyValue = ((FilterDeepChildRealmProxyInterface) object).realmGet$numFilt();
            long rowIndex = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
            } else {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$numFilt());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$numFilt());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.typeIndex, rowIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$type(), false);
            String realmGet$nameFilt = ((FilterDeepChildRealmProxyInterface) object).realmGet$nameFilt();
            if (realmGet$nameFilt != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameFiltIndex, rowIndex, realmGet$nameFilt, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.parentCategoryIndex, rowIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$parentCategory(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$status(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.menuIndex, rowIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$menu(), false);

            RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild> filterDeepInnerChildrenList = ((FilterDeepChildRealmProxyInterface) object).realmGet$filterDeepInnerChildren();
            if (filterDeepInnerChildrenList != null) {
                OsList filterDeepInnerChildrenOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.filterDeepInnerChildrenIndex);
                for (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild filterDeepInnerChildrenItem : filterDeepInnerChildrenList) {
                    Long cacheItemIndexfilterDeepInnerChildren = cache.get(filterDeepInnerChildrenItem);
                    if (cacheItemIndexfilterDeepInnerChildren == null) {
                        cacheItemIndexfilterDeepInnerChildren = FilterDeepInnerChildRealmProxy.insert(realm, filterDeepInnerChildrenItem, cache);
                    }
                    filterDeepInnerChildrenOsList.addRow(cacheItemIndexfilterDeepInnerChildren);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.voyager.nearbystores_v2.classes.FilterDeepChild object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.voyager.nearbystores_v2.classes.FilterDeepChild.class);
        long tableNativePtr = table.getNativePtr();
        FilterDeepChildColumnInfo columnInfo = (FilterDeepChildColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.FilterDeepChild.class);
        long pkColumnIndex = columnInfo.numFiltIndex;
        Object primaryKeyValue = ((FilterDeepChildRealmProxyInterface) object).realmGet$numFilt();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$numFilt());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$numFilt());
        }
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.typeIndex, rowIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$type(), false);
        String realmGet$nameFilt = ((FilterDeepChildRealmProxyInterface) object).realmGet$nameFilt();
        if (realmGet$nameFilt != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameFiltIndex, rowIndex, realmGet$nameFilt, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameFiltIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.parentCategoryIndex, rowIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$parentCategory(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$status(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.menuIndex, rowIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$menu(), false);

        OsList filterDeepInnerChildrenOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.filterDeepInnerChildrenIndex);
        RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild> filterDeepInnerChildrenList = ((FilterDeepChildRealmProxyInterface) object).realmGet$filterDeepInnerChildren();
        if (filterDeepInnerChildrenList != null && filterDeepInnerChildrenList.size() == filterDeepInnerChildrenOsList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = filterDeepInnerChildrenList.size();
            for (int i = 0; i < objects; i++) {
                com.voyager.nearbystores_v2.classes.FilterDeepInnerChild filterDeepInnerChildrenItem = filterDeepInnerChildrenList.get(i);
                Long cacheItemIndexfilterDeepInnerChildren = cache.get(filterDeepInnerChildrenItem);
                if (cacheItemIndexfilterDeepInnerChildren == null) {
                    cacheItemIndexfilterDeepInnerChildren = FilterDeepInnerChildRealmProxy.insertOrUpdate(realm, filterDeepInnerChildrenItem, cache);
                }
                filterDeepInnerChildrenOsList.setRow(i, cacheItemIndexfilterDeepInnerChildren);
            }
        } else {
            filterDeepInnerChildrenOsList.removeAll();
            if (filterDeepInnerChildrenList != null) {
                for (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild filterDeepInnerChildrenItem : filterDeepInnerChildrenList) {
                    Long cacheItemIndexfilterDeepInnerChildren = cache.get(filterDeepInnerChildrenItem);
                    if (cacheItemIndexfilterDeepInnerChildren == null) {
                        cacheItemIndexfilterDeepInnerChildren = FilterDeepInnerChildRealmProxy.insertOrUpdate(realm, filterDeepInnerChildrenItem, cache);
                    }
                    filterDeepInnerChildrenOsList.addRow(cacheItemIndexfilterDeepInnerChildren);
                }
            }
        }

        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.voyager.nearbystores_v2.classes.FilterDeepChild.class);
        long tableNativePtr = table.getNativePtr();
        FilterDeepChildColumnInfo columnInfo = (FilterDeepChildColumnInfo) realm.getSchema().getColumnInfo(com.voyager.nearbystores_v2.classes.FilterDeepChild.class);
        long pkColumnIndex = columnInfo.numFiltIndex;
        com.voyager.nearbystores_v2.classes.FilterDeepChild object = null;
        while (objects.hasNext()) {
            object = (com.voyager.nearbystores_v2.classes.FilterDeepChild) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            Object primaryKeyValue = ((FilterDeepChildRealmProxyInterface) object).realmGet$numFilt();
            long rowIndex = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
            } else {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$numFilt());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$numFilt());
            }
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.typeIndex, rowIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$type(), false);
            String realmGet$nameFilt = ((FilterDeepChildRealmProxyInterface) object).realmGet$nameFilt();
            if (realmGet$nameFilt != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameFiltIndex, rowIndex, realmGet$nameFilt, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameFiltIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.parentCategoryIndex, rowIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$parentCategory(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$status(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.menuIndex, rowIndex, ((FilterDeepChildRealmProxyInterface) object).realmGet$menu(), false);

            OsList filterDeepInnerChildrenOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.filterDeepInnerChildrenIndex);
            RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild> filterDeepInnerChildrenList = ((FilterDeepChildRealmProxyInterface) object).realmGet$filterDeepInnerChildren();
            if (filterDeepInnerChildrenList != null && filterDeepInnerChildrenList.size() == filterDeepInnerChildrenOsList.size()) {
                // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
                int objectCount = filterDeepInnerChildrenList.size();
                for (int i = 0; i < objectCount; i++) {
                    com.voyager.nearbystores_v2.classes.FilterDeepInnerChild filterDeepInnerChildrenItem = filterDeepInnerChildrenList.get(i);
                    Long cacheItemIndexfilterDeepInnerChildren = cache.get(filterDeepInnerChildrenItem);
                    if (cacheItemIndexfilterDeepInnerChildren == null) {
                        cacheItemIndexfilterDeepInnerChildren = FilterDeepInnerChildRealmProxy.insertOrUpdate(realm, filterDeepInnerChildrenItem, cache);
                    }
                    filterDeepInnerChildrenOsList.setRow(i, cacheItemIndexfilterDeepInnerChildren);
                }
            } else {
                filterDeepInnerChildrenOsList.removeAll();
                if (filterDeepInnerChildrenList != null) {
                    for (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild filterDeepInnerChildrenItem : filterDeepInnerChildrenList) {
                        Long cacheItemIndexfilterDeepInnerChildren = cache.get(filterDeepInnerChildrenItem);
                        if (cacheItemIndexfilterDeepInnerChildren == null) {
                            cacheItemIndexfilterDeepInnerChildren = FilterDeepInnerChildRealmProxy.insertOrUpdate(realm, filterDeepInnerChildrenItem, cache);
                        }
                        filterDeepInnerChildrenOsList.addRow(cacheItemIndexfilterDeepInnerChildren);
                    }
                }
            }

        }
    }

    public static com.voyager.nearbystores_v2.classes.FilterDeepChild createDetachedCopy(com.voyager.nearbystores_v2.classes.FilterDeepChild realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.voyager.nearbystores_v2.classes.FilterDeepChild unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.voyager.nearbystores_v2.classes.FilterDeepChild();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.voyager.nearbystores_v2.classes.FilterDeepChild) cachedObject.object;
            }
            unmanagedObject = (com.voyager.nearbystores_v2.classes.FilterDeepChild) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        FilterDeepChildRealmProxyInterface unmanagedCopy = (FilterDeepChildRealmProxyInterface) unmanagedObject;
        FilterDeepChildRealmProxyInterface realmSource = (FilterDeepChildRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$numFilt(realmSource.realmGet$numFilt());
        unmanagedCopy.realmSet$type(realmSource.realmGet$type());
        unmanagedCopy.realmSet$nameFilt(realmSource.realmGet$nameFilt());
        unmanagedCopy.realmSet$parentCategory(realmSource.realmGet$parentCategory());
        unmanagedCopy.realmSet$status(realmSource.realmGet$status());
        unmanagedCopy.realmSet$menu(realmSource.realmGet$menu());

        // Deep copy of filterDeepInnerChildren
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$filterDeepInnerChildren(null);
        } else {
            RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild> managedfilterDeepInnerChildrenList = realmSource.realmGet$filterDeepInnerChildren();
            RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild> unmanagedfilterDeepInnerChildrenList = new RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild>();
            unmanagedCopy.realmSet$filterDeepInnerChildren(unmanagedfilterDeepInnerChildrenList);
            int nextDepth = currentDepth + 1;
            int size = managedfilterDeepInnerChildrenList.size();
            for (int i = 0; i < size; i++) {
                com.voyager.nearbystores_v2.classes.FilterDeepInnerChild item = FilterDeepInnerChildRealmProxy.createDetachedCopy(managedfilterDeepInnerChildrenList.get(i), nextDepth, maxDepth, cache);
                unmanagedfilterDeepInnerChildrenList.add(item);
            }
        }

        return unmanagedObject;
    }

    static com.voyager.nearbystores_v2.classes.FilterDeepChild update(Realm realm, com.voyager.nearbystores_v2.classes.FilterDeepChild realmObject, com.voyager.nearbystores_v2.classes.FilterDeepChild newObject, Map<RealmModel, RealmObjectProxy> cache) {
        FilterDeepChildRealmProxyInterface realmObjectTarget = (FilterDeepChildRealmProxyInterface) realmObject;
        FilterDeepChildRealmProxyInterface realmObjectSource = (FilterDeepChildRealmProxyInterface) newObject;
        realmObjectTarget.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectTarget.realmSet$nameFilt(realmObjectSource.realmGet$nameFilt());
        realmObjectTarget.realmSet$parentCategory(realmObjectSource.realmGet$parentCategory());
        realmObjectTarget.realmSet$status(realmObjectSource.realmGet$status());
        realmObjectTarget.realmSet$menu(realmObjectSource.realmGet$menu());
        RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild> filterDeepInnerChildrenList = realmObjectSource.realmGet$filterDeepInnerChildren();
        RealmList<com.voyager.nearbystores_v2.classes.FilterDeepInnerChild> filterDeepInnerChildrenRealmList = realmObjectTarget.realmGet$filterDeepInnerChildren();
        if (filterDeepInnerChildrenList != null && filterDeepInnerChildrenList.size() == filterDeepInnerChildrenRealmList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = filterDeepInnerChildrenList.size();
            for (int i = 0; i < objects; i++) {
                com.voyager.nearbystores_v2.classes.FilterDeepInnerChild filterDeepInnerChildrenItem = filterDeepInnerChildrenList.get(i);
                com.voyager.nearbystores_v2.classes.FilterDeepInnerChild cachefilterDeepInnerChildren = (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild) cache.get(filterDeepInnerChildrenItem);
                if (cachefilterDeepInnerChildren != null) {
                    filterDeepInnerChildrenRealmList.set(i, cachefilterDeepInnerChildren);
                } else {
                    filterDeepInnerChildrenRealmList.set(i, FilterDeepInnerChildRealmProxy.copyOrUpdate(realm, filterDeepInnerChildrenItem, true, cache));
                }
            }
        } else {
            filterDeepInnerChildrenRealmList.clear();
            if (filterDeepInnerChildrenList != null) {
                for (int i = 0; i < filterDeepInnerChildrenList.size(); i++) {
                    com.voyager.nearbystores_v2.classes.FilterDeepInnerChild filterDeepInnerChildrenItem = filterDeepInnerChildrenList.get(i);
                    com.voyager.nearbystores_v2.classes.FilterDeepInnerChild cachefilterDeepInnerChildren = (com.voyager.nearbystores_v2.classes.FilterDeepInnerChild) cache.get(filterDeepInnerChildrenItem);
                    if (cachefilterDeepInnerChildren != null) {
                        filterDeepInnerChildrenRealmList.add(cachefilterDeepInnerChildren);
                    } else {
                        filterDeepInnerChildrenRealmList.add(FilterDeepInnerChildRealmProxy.copyOrUpdate(realm, filterDeepInnerChildrenItem, true, cache));
                    }
                }
            }
        }
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("FilterDeepChild = proxy[");
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
        stringBuilder.append(",");
        stringBuilder.append("{filterDeepInnerChildren:");
        stringBuilder.append("RealmList<FilterDeepInnerChild>[").append(realmGet$filterDeepInnerChildren().size()).append("]");
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
        FilterDeepChildRealmProxy aFilterDeepChild = (FilterDeepChildRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aFilterDeepChild.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aFilterDeepChild.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aFilterDeepChild.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
