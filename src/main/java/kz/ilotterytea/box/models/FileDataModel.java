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

    public FileDataModel(String id, String mime, String ext) {
        this.id = id;
        this.mime = mime;
        this.ext = ext;
    }

    public String getId() { return id; }
    public String getMime() { return mime; }
    public String getExt() { return ext; }
}
