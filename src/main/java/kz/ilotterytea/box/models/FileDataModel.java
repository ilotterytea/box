package kz.ilotterytea.box.models;

/**
 * File data model.
 * @author ilotterytea
 * @since 1.0
 */
public class FileDataModel {
    /** The ID of the file. */
    private final String id;

    /** The MIME type of the file. */
    private final String mime;

    /** The extension of the file. */
    private final String ext;

    /** The size of the file (in bytes). */
    private final long size;

    /** The hash summary of the file. */
    private String sum;

    /** The GET link to the file. */
    private String get;

    public FileDataModel(
            String id,
            String mime,
            String ext,
            long size,
            String sum,
            String get
    ) {
        this.id = id;
        this.mime = mime;
        this.ext = ext;
        this.size = size;
        this.sum = sum;
        this.get = get;
    }

    public FileDataModel(
            String id,
            String mime,
            String ext,
            long size
    ) {
        this.id = id;
        this.mime = mime;
        this.ext = ext;
        this.size = size;
    }

    public String getId() { return id; }
    public String getMime() { return mime; }
    public String getExt() { return ext; }
    public long getSize() { return size; }
    public String getSum() { return sum; }
    public void setSum(String sum) { this.sum = sum; }
    public String getGet() { return get; }
    public void setGet(String get) { this.get = get; }
}
