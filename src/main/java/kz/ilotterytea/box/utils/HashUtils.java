package kz.ilotterytea.box.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hash utilities.
 * @author ilotterytea
 * @since 1.0
 */
public class HashUtils {
    /**
     * Generate an SHA-256 hash.
     */
    public static String generateSHA256Hash(File file) {
        String sum = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            sum = fileChecksum(file, digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return sum;
    }

    /**
     * Generate a MD5 hash.
     */
    public static String generateMD5Hash(File file) {
        String sum = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            sum = fileChecksum(file, digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return sum;
    }

    private static String fileChecksum(File file, MessageDigest digest) {
        String sum = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int bytesCount = 0;

            while ((bytesCount = fis.read(bytes)) != -1) {
                digest.update(bytes, 0, bytesCount);
            }

            fis.close();

            byte[] bytes1 = digest.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : bytes1) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

            sum = sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sum;
    }
}
