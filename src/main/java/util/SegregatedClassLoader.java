package util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SegregatedClassLoader extends ClassLoader {

    private static Map<String, byte[]> segregatedSubClasses = new ConcurrentHashMap<>();

    public static void storeClass(String name, byte[] content) {
        segregatedSubClasses.put(name, content);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("======" + name);
        if(segregatedSubClasses.containsKey(name)) {
            byte[] bytes = segregatedSubClasses.get(name);
            return defineClass(name, bytes, 0, bytes.length);
        }

        return super.loadClass(name);
    }

}
