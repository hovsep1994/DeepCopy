package com.deepcopy.array;

import com.deepcopy.DeepCopyUtil;

import java.lang.reflect.*;
import java.util.*;

public class ArrayDeepCopy {

    public static Object copy(final Object array,
                              final Map<Object, Object> visited) {
        int length = Array.getLength(array);
        Object copy = Array.newInstance(array.getClass().getComponentType(), length);
        visited.put(array, copy);
        for (int i = 0; i < length; i++) {
            Array.set(copy, i, DeepCopyUtil.deepCopyInternal(Array.get(array, i)));
        }
        return copy;
    }
}
