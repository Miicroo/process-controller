package model.web.requestparser;

import model.dto.Response;
import model.dto.Request;

/**
 * Interface specifying that a class can handle requests.
 * All requests must return a Response-object.
 */
public interface RequestParser {
    Response parseRequest(Request requestURI);
}
