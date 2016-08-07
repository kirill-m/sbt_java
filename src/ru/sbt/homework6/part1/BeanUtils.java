package ru.sbt.homework6.part1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kirill
 */
public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        Class<?> clazz1 = to.getClass();
        Class<?> clazz2 = from.getClass();
        Map<String, Method> gettersMap = new HashMap<>();
        Map<String, Method> settersMap = new HashMap<>();


        Method[] getters = clazz1.getMethods();
        for (Method getter : getters) {
            if (isPublicGetter(getter)) {
                gettersMap.put(getter.getName(), getter);
            }
        }

        Method[] setters = clazz2.getMethods();
        for (Method setter : setters) {
            if (isPublicSetter(setter)) {
                settersMap.put(setter.getName(), setter);
            }
        }

        for (Map.Entry<String, Method> entry : settersMap.entrySet()) {
            String getterName = entry.getKey().replace("set", "get");
            if (gettersMap.containsKey(getterName)) {
                Method getter = gettersMap.get(getterName);
                Method setter = entry.getValue();
                if (isCompatible(setter, getter)) {
                    try {
                        setter.invoke(to, getter.invoke(from));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException("Exception during reflection access", e);
                    }
                }
            }
        }
    }

    private static boolean isPublicGetter(Method method) {
        return method.getName().startsWith("get") &&
                method.getParameterCount() == 0 &&
                method.getReturnType() != void.class &&
                Modifier.isPublic(method.getModifiers());
    }

    private static boolean isPublicSetter(Method method) {
        return method.getName().startsWith("set") &&
                method.getParameterCount() == 1 &&
                method.getReturnType() == void.class &&
                Modifier.isPublic(method.getModifiers());
    }

    private static boolean isCompatible(Method setter, Method getter) {
        Class<?> setterType = setter.getParameterTypes()[0];
        Class<?> getterType = getter.getReturnType();
        return getterType.getName().equals(setterType.getName()) ||
                setterType.isAssignableFrom(getterType);
    }

}
