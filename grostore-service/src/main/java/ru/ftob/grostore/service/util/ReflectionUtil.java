package ru.ftob.grostore.service.util;

import java.lang.reflect.Field;
import java.util.Map;

public class ReflectionUtil {
    private ReflectionUtil() {

    }

    public static <T> T createInstance(Class<T> clz) {
        try {
            return clz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  void setFields(Object o, Map<String, String> fields) throws NoSuchFieldException{
        fields.entrySet().stream().forEach(f -> {
            try {
                Field fld = o.getClass().getDeclaredField(f.getKey());
                fld.setAccessible(true);
                fld.set(o, f.getValue());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}
