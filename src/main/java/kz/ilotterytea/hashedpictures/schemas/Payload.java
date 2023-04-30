package kz.ilotterytea.hashedpictures.schemas;

/**
 * @author ilotterytea
 * @since 1.0
 */
public class Payload<T> {
    private Integer status;
    private String message;
    private T data;

    public Payload(Integer status, String message, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public Payload(T data) {
        this.data = data;
        this.status = 200;
        this.message = "Success!";
    }

    public Payload() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
