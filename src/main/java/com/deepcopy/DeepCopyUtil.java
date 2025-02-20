package com.deepcopy;

import com.deepcopy.array.ArrayDeepCopy;
import com.deepcopy.collection.CollectionDeepCopy;
import com.deepcopy.custom.CustomDeepCopy;
import com.deepcopy.immutable.ImmutableHelper;
import com.deepcopy.map.MapDeepCopy;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;

public class DeepCopyUtil {

    private static final Map<Object, Object> visited = new IdentityHashMap<>();

    private DeepCopyUtil() {
    }

    public static <T> T deepCopy(final T original) {
        try {
            return (T) deepCopyInternal(original);
        } finally {
            visited.clear();
        }
    }

    public static Object deepCopyInternal(final Object original) {
        if (original == null) return null;

        if (visited.containsKey(original)) {
            return visited.get(original);
        }

        final Class<?> clazz = original.getClass();

        if (ImmutableHelper.isImmutable(clazz)) {
            return original;
        }

        if (clazz.isArray()) {
            return ArrayDeepCopy.copy(original, visited);
        }

        if (original instanceof Collection) {
            return CollectionDeepCopy.copy((Collection<?>) original, visited);
        }

        if (original instanceof Map) {
            return MapDeepCopy.copy((Map<?, ?>) original, visited);
        }

        try {
            return CustomDeepCopy.copy(original, visited);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deep copy object", e);
        }
    }

}
