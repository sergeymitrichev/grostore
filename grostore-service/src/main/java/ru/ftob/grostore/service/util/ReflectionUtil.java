package ru.ftob.grostore.service.util;

import org.springframework.util.ReflectionUtils;
import ru.ftob.grostore.service.util.exception.ConfigurationException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReflectionUtil {
    private ReflectionUtil() {
    }
    public static <T> T createInstance(Class<T> clz)
            throws ConfigurationException {
        try {
            return clz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ConfigurationException(e);
        }
    }

    public static List<String> findSimilarFields(Class<?> clz1, Class<?> clz2)
            throws ConfigurationException {
        try {
            Field[] fields = clz1.getDeclaredFields();
            List<String> targetFields = Stream.of(clz2.getDeclaredFields())
                    .map(Field::getName)
                    .collect(Collectors.toList());
            return Stream.of(fields)
                    .map(Field::getName)
                    .filter(targetFields::contains)
                    .collect(Collectors.toList());
        } catch (SecurityException ex) {
            throw new ConfigurationException(ex);
        }
    }

    public static void copyFields(Object src, Object dest, List<String> fields)
            throws ConfigurationException {

        try {
            for (String field : fields) {
                Field fld = src.getClass().getDeclaredField(field);
                // Skip unknown fields
                if (fld != null) {
                    fld.setAccessible(true);
                    Object value = fld.get(src);
                    Field fldDest = ReflectionUtils.findField(dest.getClass(), field);
                    //TODO add parameter to product imports "overwrite null values": true/false
                    if (fldDest != null && value != null) {
                        fldDest.setAccessible(true);
                        fldDest.set(dest, value);
                    }
                }
            }
        } catch (SecurityException | ReflectiveOperationException
                | IllegalArgumentException ex) {
            throw new ConfigurationException(ex);
        }
    }
}
