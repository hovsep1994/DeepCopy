package com.deepcopy.map;

import com.deepcopy.DeepCopyUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapDeepCopy {

    private MapDeepCopy() {
    }

    public static Map<?, ?> copy(Map<?, ?> original,
                                 Map<Object, Object> visited) {
        Map<Object, Object> copy = createEmptyMap(original);
        visited.put(original, copy);
        for (Map.Entry<?, ?> entry : original.entrySet()) {
            Object copiedKey = DeepCopyUtil.deepCopyInternal(entry.getKey());
            Object copiedValue = DeepCopyUtil.deepCopyInternal(entry.getValue());
            copy.put(copiedKey, copiedValue);
        }
        return copy;
    }

    private static Map<Object, Object> createEmptyMap(Map<?, ?> original) {
        try {
            return (Map<Object, Object>) original.getClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            if (original instanceof LinkedHashMap) return new LinkedHashMap<>();
            if (original instanceof HashMap) return new HashMap<>();
            if (original instanceof TreeMap) return new TreeMap<>();
            return new HashMap<>();
        }
    }
}
