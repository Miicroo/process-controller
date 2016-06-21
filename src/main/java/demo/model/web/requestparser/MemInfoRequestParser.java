package demo.model.web.requestparser;

import demo.model.controller.ScriptController;
import exception.ProcessException;
import model.JSONConverter;
import model.dto.Request;
import model.dto.Response;
import model.web.RequestUtils;
import model.web.requestparser.RequestParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Request parser for memory info.
 */
public class MemInfoRequestParser implements RequestParser {
    
    @Override
    public Response parseRequest(Request request) {
        String[] components = RequestUtils.getComponents(request.getRequestURI());

        if (components.length == 0) {
            return RequestUtils.NOT_FOUND;
        }

        if (components[0].equalsIgnoreCase("memInfo")) {
            return getMemoryInfo();
        }

        return RequestUtils.NOT_FOUND;
    }

    private Response getMemoryInfo() {
        ScriptController controller = new ScriptController();
        try {
            String data = controller.runScript("meminfo.sh");

            Map<String, String> map = new HashMap<>();
            map.put("memInfo", data);

            return new Response(200, JSONConverter.mapToString(map).getBytes());
        } catch (ProcessException e) {
            return new Response(500, "Could not get memory info".getBytes());
        }
    }
}
