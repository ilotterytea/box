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

    public void setChars(String chars) { this.chars = chars; }
    public String getChars() { return chars; }

    public void setDefaultIdLength(int defaultIdLength) { this.defaultIdLength = defaultIdLength; }
    public int getDefaultIdLength() { return defaultIdLength; }

    public void setUploadedPath(String uploadedPath) { this.uploadedPath = uploadedPath; }
    public String getUploadedPath() { return uploadedPath; }

    public void setDataPath(String dataPath) { this.dataPath = dataPath; }
    public String getDataPath() { return dataPath; }

    public void setBoxName(String boxName) { this.boxName = boxName; }
    public String getBoxName() { return boxName; }

    public void setMaxFileAgeMs(long maxFileAgeMs) { this.maxFileAgeMs = maxFileAgeMs; }
    public long getMaxFileAgeMs() { return maxFileAgeMs; }

    public void setCollectExpiredFilesIntervalMs(long collectExpiredFilesIntervalMs) { this.collectExpiredFilesIntervalMs = collectExpiredFilesIntervalMs; }
    public long getCollectExpiredFilesIntervalMs() { return collectExpiredFilesIntervalMs; }

    public void setUseExpiredFilesCollector(boolean useExpiredFilesCollector) { this.useExpiredFilesCollector = useExpiredFilesCollector; }
    public boolean getUseExpiredFilesCollector() { return useExpiredFilesCollector; }

    public void setGenerateDataFiles(boolean generateDataFiles) { this.generateDataFiles = generateDataFiles; }
    public boolean getGenerateDataFiles() { return generateDataFiles; }

    public void setGenerateHashSum(boolean generateHashSum) { this.generateHashSum = generateHashSum; }
    public boolean getGenerateHashSum() { return generateHashSum; }
}
