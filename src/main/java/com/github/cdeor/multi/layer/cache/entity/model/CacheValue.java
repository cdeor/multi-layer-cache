package com.github.cdeor.multi.layer.cache.entity.model;

import java.lang.reflect.Type;

public class CacheValue {

    private final boolean empty;
    private final String value;
    private final String type;

    private CacheValue(boolean empty, String value, String type) {
        this.empty = empty;
        this.value = value;
        this.type = type;
    }

    private static final CacheValue EMPTY_VAL =
            new CacheValue(true, "", "");

    public CacheValue init(String value, String type) {
        return new CacheValue(false, value, type);
    }

    public static CacheValue empty() {
        return EMPTY_VAL;
    }

    protected Object getValue(Type type) {
        return value == null || value.isBlank() ? null : null;//SerializerManager.jsonSerializer().deserialize(value, type);
    }

    public String getValueType() {
        return type;
    }

    public boolean isValueEmpty() {
        return empty;
    }

    public boolean isSuccess() {
        return "success".equals(value);
    }
}
