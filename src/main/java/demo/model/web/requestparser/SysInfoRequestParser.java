package demo.model.web.requestparser;

import demo.model.controller.ScriptController;
import demo.model.dto.SysInfo;
import exception.ProcessException;
import model.JSONConverter;
import model.dto.Request;
import model.dto.Response;
import model.web.RequestUtils;
import model.web.requestparser.RequestParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Request parser for system info.
 */
public class SysInfoRequestParser implements RequestParser {
    
    @Override
    public Response parseRequest(Request request) {
        String[] components = RequestUtils.getComponents(request.getRequestURI());

        if (components.length == 0) {
            return RequestUtils.NOT_FOUND;
        }

        if (components[0].equalsIgnoreCase("sysInfo")) {
            return getSystemInfo();
        }

        return RequestUtils.NOT_FOUND;
    }

    private Response getSystemInfo() {
        ScriptController controller = new ScriptController();
        try {
            String data = controller.runScript("sysinfo.sh");

            Map<String, SysInfo> map = new HashMap<>();
            map.put("sysInfo", parseData(data));

            return new Response(200, JSONConverter.mapToString(map).getBytes());
        } catch (ProcessException e) {
            return new Response(500, "Could not get system info".getBytes());
        }
    }

    private SysInfo parseData(String data) {
        String[] lines = data.split("\\n");
        Map<String, String> system = new HashMap<>();
        Map<String, String> user = new HashMap<>();
        Map<String, String> network = new HashMap<>();
        Map<String, String> current = null;
        String key = null;
        for(String line : lines) {
            if(line.startsWith("<=== SYSTEM ===>")) {
                current = system;
                key = null;
            } else if(line.startsWith("<=== USER ===>")) {
                current = user;
                key = null;
            } else if(line.startsWith("<=== NETWORK ===>")) {
                current = network;
                key = null;
            }

            final String delimiter = "===:";
            int valueStart = 0;
            if(line.contains(delimiter)) {
                key = line.substring(0, line.indexOf(delimiter));
                valueStart = line.indexOf(delimiter)+delimiter.length();
            }

            String value = (current.get(key) == null ? "" : current.get(key)) + line.substring(valueStart, line.length())+"\n";
            if(key != null) {
                current.put(key, value);
            }
        }

        return new SysInfo(system, user, network);
    }
}
