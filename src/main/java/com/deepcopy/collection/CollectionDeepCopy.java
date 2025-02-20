package com.deepcopy.collection;

import com.deepcopy.DeepCopyUtil;

import java.util.*;

public class CollectionDeepCopy {

    public static Collection<?> copy(Collection<?> original,
                                     Map<Object, Object> visited) {
        Collection<Object> copy = createEmptyCollection(original);
        visited.put(original, copy);
        for (Object item : original) {
            copy.add(DeepCopyUtil.deepCopyInternal(item));
        }
        return copy;
    }

    private static Collection<Object> createEmptyCollection(Collection<?> original) {
        try {
            return (Collection<Object>) original.getClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return getFallbackCollection(original);
        }
    }

    private static Collection<Object> getFallbackCollection(Collection<?> original) {
        if (original instanceof LinkedList) return new LinkedList<>();
        if (original instanceof ArrayList) return new ArrayList<>();
        if (original instanceof List<?>) return new ArrayList<>();
        if (original instanceof HashSet) return new HashSet<>();
        if (original instanceof TreeSet) return new TreeSet<>();
        if (original instanceof Deque) return new LinkedList<>();
        if (original instanceof Queue) return new LinkedList<>();

        throw new RuntimeException("Unsupported collection type: " + original.getClass().getName());
    }
}
