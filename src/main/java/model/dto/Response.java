package model.dto;

import java.util.Arrays;

public class Response {
    private int code;
    private byte[] data;
    private String contentType;

    public Response(int code) {
        this(code, new byte[0]);
    }

    public Response(int code, byte[] data) {
        this(code, data, null);
    }

    public Response(int code, byte[] data, String contentType) {
        this.code = code;
        this.data = data;
        this.contentType = contentType;
    }

    public int getCode() {
        return code;
    }

    public byte[] getData() {
        return data;
    }

    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Response response = (Response) o;

        if (code != response.code)
            return false;
        if (!Arrays.equals(data, response.data))
            return false;
        return contentType != null ? contentType.equals(response.contentType) : response.contentType == null;

    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + Arrays.hashCode(data);
        result = 31 * result + (contentType != null ? contentType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
            "code=" + code +
            ", data=" + Arrays.toString(data) +
            ", contentType='" + contentType + '\'' +
            '}';
    }
}
