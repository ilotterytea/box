package kz.ilotterytea.box;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Box properties.
 * @author ilotterytea
 * @since 1.0
 */
@ConfigurationProperties("box")
public class BoxProperties {
    /** Character pool. */
    @Value("${box.chars}")
    private String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
    /** Default length for ID. */
    @Value("${box.defaultIdLength}")
    private int defaultIdLength = 6;
    /** Path to the folder where the uploaded files are to be stored. */
    @Value("${box.uploadedPath}")
    private String uploadedPath = "./uploaded";

    public void setChars(String chars) { this.chars = chars; }
    public String getChars() { return chars; }

    public void setDefaultIdLength(int defaultIdLength) { this.defaultIdLength = defaultIdLength; }
    public int getDefaultIdLength() { return defaultIdLength; }

    public void setUploadedPath(String uploadedPath) { this.uploadedPath = uploadedPath; }
    public String getUploadedPath() { return uploadedPath; }
}
