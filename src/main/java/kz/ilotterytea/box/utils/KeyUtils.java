package kz.ilotterytea.box.utils;

/**
 * Key utilities.
 * @author ilotterytea
 * @since 1.0
 */
public class KeyUtils {
    /**
     * Generate a random key from character pool.
     * @param length The max character length of key.
     * @return character key
     */
    public static String generateCharKey(String chars, int length) {
        char[] id = new char[length];

        for (int i = 0; i < length; i++) {
            id[i] = chars.charAt((int) Math.floor(Math.random() * chars.length()));
        }

        return new String(id);
    }
}
