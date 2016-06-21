package model.web;

import model.dto.Response;

public class RequestUtils {

    public static final Response BAD_REQUEST = new Response(400, "".getBytes());
    public static final Response NOT_FOUND = new Response(404, "".getBytes());

    /**
     * Splits a request /x/y/z/temp into its components ([x, y, z, temp]).
     *
     * @param requestUri The incoming requestUri.
     * @return The uri as its components.
     */
    public static String[] getComponents(String requestUri) {
        return requestUri.substring(1).split("/");
    }
}
