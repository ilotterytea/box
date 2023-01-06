package kz.ilotterytea.box;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Box properties.
 * @author ilotterytea
 * @since 1.0
 */
@ConfigurationProperties(prefix = "box")
public class BoxProperties {
    /** Character pool. */
    private String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
    /** Default length for ID. */
    private int defaultIdLength = 6;
    /** Path to the folder where the uploaded files are to be stored. */
    private String uploadedPath = "./uploaded";
    /** Path to the folder where the data files are to be stored. */
    private String dataPath = "./data/file_records";
    /** The box name. */
    private String boxName = "Box";
    /** Maximum time age (in milliseconds) of the file.
     * @see kz.ilotterytea.box.components.CollectorComponent
     */
    private long maxFileAgeMs = 43_200_000L;
    /** Interval (in milliseconds) between collection of expired files.
     * @see kz.ilotterytea.box.components.CollectorComponent
     */
    private long collectExpiredFilesIntervalMs = 43_200_000L;
    /** Use the "expired files collector".
     * @see kz.ilotterytea.box.components.CollectorComponent
     */
    private boolean useExpiredFilesCollector = true;
    /** Generate data files of uploaded files. */
    private boolean generateDataFiles = true;
    /** Generate a hash summary of uploaded file. */
    private boolean generateHashSum = true;
    /** Use the file linker. */
    private boolean useFileLinker = true;

    public String getChars() { return chars; }
    public int getDefaultIdLength() { return defaultIdLength; }
    public String getUploadedPath() { return uploadedPath; }
    public String getDataPath() { return dataPath; }
    public String getBoxName() { return boxName; }
    public long getMaxFileAgeMs() { return maxFileAgeMs; }
    public long getCollectExpiredFilesIntervalMs() { return collectExpiredFilesIntervalMs; }
    public boolean getUseExpiredFilesCollector() { return useExpiredFilesCollector; }
    public boolean getGenerateDataFiles() { return generateDataFiles; }
    public boolean getGenerateHashSum() { return generateHashSum; }
    public boolean getUseFileLinker() { return useFileLinker; }
}
