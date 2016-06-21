package model.dto;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String requestURI,
                   requestMethod;
    private Map<String, Object> parameters;

    public Request(String requestURI, String requestMethod) {
        this(requestURI, requestMethod, new HashMap<>());
    }

    public Request(String requestURI, String requestMethod, Map<String, Object> parameters) {
        this.requestURI = requestURI;
        this.requestMethod = requestMethod;
        this.parameters = parameters;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Request request = (Request) o;

        if (requestURI != null ? !requestURI.equals(request.requestURI) : request.requestURI != null)
            return false;
        if (requestMethod != null ? !requestMethod.equals(request.requestMethod) : request.requestMethod != null)
            return false;
        return parameters != null ? parameters.equals(request.parameters) : request.parameters == null;

    }

    @Override
    public int hashCode() {
        int result = requestURI != null ? requestURI.hashCode() : 0;
        result = 31 * result + (requestMethod != null ? requestMethod.hashCode() : 0);
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
            "requestURI='" + requestURI + '\'' +
            ", requestMethod='" + requestMethod + '\'' +
            ", parameters=" + parameters +
            '}';
    }
}
