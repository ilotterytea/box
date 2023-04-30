package kz.ilotterytea.bin;

import io.micronaut.context.annotation.ConfigurationProperties;

/**
 * @author ilotterytea
 * @since 1.0
 */
@ConfigurationProperties("bin")
public class ApplicationProperties {
    private String tokenChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
    private int tokenLength = 6;
    private int keyLength = 16;

    public String getTokenChars() {
        return tokenChars;
    }

    public Integer getTokenLength() {
        return tokenLength;
    }

    public int getKeyLength() {
        return keyLength;
    }
}
