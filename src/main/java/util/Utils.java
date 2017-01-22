package util;

public class Utils {
    public static <T> Class<T> getClass(Class<T> clazz, Client client) {
        try {
            return (Class<T>) Class.forName(makeClassName(clazz, client));
        } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
        }
    }

    public static String makeClassName(Class<?> baseClass, Client client) {
        return baseClass.getName().concat("$$$").concat(client.name());
    }
}
