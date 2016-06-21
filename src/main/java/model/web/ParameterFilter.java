package model.web;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import model.PropertyManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copied from Leonardo Marcelino (leonardo.marcelino@gmail.com) (only minor cosmetic fixes done).
 * Original code available at https://leonardom.wordpress.com/2009/08/06/getting-parameters-from-httpexchange/
 */
public class ParameterFilter extends Filter {

    private static final String DEFAULT_ENCODING = "UTF-8";

    @Override
    public String description() {
        return "Parses the requested URI for parameters";
    }

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        parseGetParameters(exchange);
        parsePostParameters(exchange);
        chain.doFilter(exchange);
    }

    private void parseGetParameters(HttpExchange exchange) throws UnsupportedEncodingException {
        URI requestedUri = exchange.getRequestURI();
        String query = requestedUri.getRawQuery();

        exchange.setAttribute("parameters", parseQuery(query));
    }

    private void parsePostParameters(HttpExchange exchange) throws IOException {
        if ("post".equalsIgnoreCase(exchange.getRequestMethod())) {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(),
                PropertyManager.getStringProperty("system.encoding", DEFAULT_ENCODING));
            BufferedReader br = new BufferedReader(isr);
            String query = br.readLine();
            br.close();
            isr.close();

            Map<String, Object> httpParameters = (Map<String, Object>) exchange.getAttribute("parameters");
            httpParameters.putAll(parseQuery(query));
        }
    }

    private Map<String, Object> parseQuery(String query) throws UnsupportedEncodingException {
        Map<String, Object> parameters = new HashMap<>();

        if (query != null) {
            String pairs[] = query.split("[&]");

            for (String pair : pairs) {
                String param[] = pair.split("[=]");

                String key = null;
                String value = null;
                if (param.length > 0) {
                    key = URLDecoder.decode(param[0], System.getProperty("system.encoding", DEFAULT_ENCODING));
                }

                if (param.length > 1) {
                    value = URLDecoder.decode(param[1], System.getProperty("system.encoding", DEFAULT_ENCODING));
                }

                if (parameters.containsKey(key)) {
                    Object obj = parameters.get(key);
                    if (obj instanceof List<?>) {
                        List<String> values = (List<String>) obj;
                        values.add(value);
                    } else if (obj instanceof String) {
                        List<String> values = new ArrayList<>();
                        values.add((String) obj);
                        values.add(value);
                        parameters.put(key, values);
                    }
                } else {
                    parameters.put(key, value);
                }
            }
        }

        return parameters;
    }
}
