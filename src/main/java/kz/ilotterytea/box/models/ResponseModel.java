package kz.ilotterytea.box.models;

/**
 * Response model.
 * @author ilotterytea
 * @since 1.0
 */
public class ResponseModel<T> {
    /** The status code of the response. */
    private final int status;
    /** The response. */
    private final T response;

    public ResponseModel(int status, T response) {
        this.status = status;
        this.response = response;
    }

    public int getStatus() { return status; }
    public T getResponse() { return response; }
}
