package model.web.requestparser;

import model.PropertyManager;
import model.dto.Request;
import model.dto.Response;
import model.web.RequestUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * RequestParser for ordinary files.
 */
public class FileRequestParser implements RequestParser {

    private static final String FILE_PATH = "../../../www/";

    @Override
    public Response parseRequest(Request request) {
        String[] components = RequestUtils.getComponents(request.getRequestURI());

        if (components.length == 0) {
            return RequestUtils.NOT_FOUND;
        }

        if (components[0].isEmpty()) {
            return getFile(PropertyManager.getStringProperty("webserver.indexpage", "index.html"));
        } else if (isRegularFile(request.getRequestURI())) {
            return getFile(request.getRequestURI());
        }

        return RequestUtils.NOT_FOUND;
    }

    private boolean isRegularFile(String file) {
        return getClass().getResourceAsStream(FILE_PATH + file) != null;
    }

    private Response getFile(String filename) {
        String contentType;
        byte[] data;

        try {
            InputStream input = getClass().getResourceAsStream(FILE_PATH + filename);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while (true) {
                int r = input.read(buffer);
                if (r == -1) {
                    break;
                }
                out.write(buffer, 0, r);
            }

            data = out.toByteArray();

            Path filepath = Paths.get(filename);
            contentType = Files.probeContentType(filepath);
        } catch (IOException e) {
            return RequestUtils.NOT_FOUND;
        }
        return new Response(200, data, contentType);
    }
}
