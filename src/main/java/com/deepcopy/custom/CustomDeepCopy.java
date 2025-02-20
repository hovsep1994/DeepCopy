package com.deepcopy.custom;

import com.deepcopy.DeepCopyUtil;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CustomDeepCopy {

    public static Object copy(Object original,
                              Map<Object, Object> visited) throws Exception {
        final Class<?> clazz = original.getClass();
        Object copy = createObjectInstance(clazz, original);
        visited.put(original, copy);

        for (Field field : getAllFields(clazz)) {
            field.setAccessible(true);
            Object fieldValue = field.get(original);
            Object copiedValue = DeepCopyUtil.deepCopyInternal(fieldValue);
            field.set(copy, copiedValue);
        }

        return copy;
    }

    private static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    private static Object createObjectInstance(Class<?> clazz, Object original) throws Exception {
        try {
            Constructor<?> noArgConstructor = clazz.getDeclaredConstructor();
            noArgConstructor.setAccessible(true);
            return noArgConstructor.newInstance();
        } catch (NoSuchMethodException e) {
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                if (constructor.getParameterCount() == 1
                        && constructor.getParameterTypes()[0].isAssignableFrom(clazz)) {
                    constructor.setAccessible(true);
                    return constructor.newInstance(original);
                }
            }
            return instantiateUsingUnsafe(clazz);
        }
    }

    private static Object instantiateUsingUnsafe(Class<?> clazz) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        return unsafe.allocateInstance(clazz);
    }

}
