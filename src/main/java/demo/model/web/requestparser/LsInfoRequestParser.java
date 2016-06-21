package demo.model.web.requestparser;

import demo.model.controller.LsController;
import exception.ProcessException;
import model.JSONConverter;
import model.PropertyManager;
import model.dto.Request;
import model.dto.Response;
import model.web.RequestUtils;
import model.web.requestparser.RequestParser;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Request parser for dir content.
 */
public class LsInfoRequestParser implements RequestParser {
    
    @Override
    public Response parseRequest(Request request) {
        String[] components = RequestUtils.getComponents(request.getRequestURI());

        if (components.length == 0) {
            return RequestUtils.NOT_FOUND;
        }

        if (components[0].startsWith("ls?")) {
            if (!request.getParameters().containsKey("dir")) {
                return RequestUtils.BAD_REQUEST;
            }
            try {
                return getDirContent(URLDecoder.decode(request.getParameters().get("dir").toString(), PropertyManager.getStringProperty("system.encoding", "UTF-8")));
            } catch (UnsupportedEncodingException e) {
                return RequestUtils.BAD_REQUEST;
            }
        }

        return RequestUtils.NOT_FOUND;
    }

    private Response getDirContent(String dir) {
        LsController controller = new LsController();
        try {
            String data = controller.getDirContent(dir);

            Map<String, String> map = new HashMap<>();
            map.put("lsInfo", data);

            return new Response(200, JSONConverter.mapToString(map).getBytes());
        } catch (ProcessException e) {
            return new Response(500, "Could not get directory content".getBytes());
        }
    }
}
