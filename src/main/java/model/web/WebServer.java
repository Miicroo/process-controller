package model.web;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.PropertyManager;
import model.dto.Request;
import model.dto.Response;
import model.web.requestparser.RequestParser;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class WebServer implements HttpHandler {

    @Override
    public void handle(final HttpExchange req) {
        System.out.println("Got request: " + req.getRequestURI());

        boolean multithreaded = PropertyManager.getBooleanProperty("system.multithreaded", false);
        if(multithreaded) {
            new Thread(() -> handleRequest(req)).start();
        } else {
            handleRequest(req);
        }
    }

    private void handleRequest(HttpExchange req) {
        Response response = parseRequest(req);

        if (response.getContentType() != null) {
            Headers headers = req.getResponseHeaders();
            headers.add("Content-Type", response.getContentType());
        }

        try {
            req.sendResponseHeaders(response.getCode(), response.getData().length);

            System.out.println("Sending response");
            System.out.println("Code: " + response.getCode());
            System.out.println("Data: " + response.getData());
            System.out.println();

            OutputStream os = req.getResponseBody();
            os.write(response.getData());
            os.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private Response parseRequest(HttpExchange requestExchange) {
        if (requestExchange.getRequestURI() == null) {
            return RequestUtils.NOT_FOUND;
        }

        Request request = getRequest(requestExchange);
        RequestParser parser = RequestParserFactory.getRequestParser(request.getRequestURI());

        if (parser == null) {
            return RequestUtils.NOT_FOUND; // No handler, return not found
        } else {
            return parser.parseRequest(request);
        }
    }

    private Request getRequest(HttpExchange requestExchange) {
        return new Request(requestExchange.getRequestURI().toString(), requestExchange.getRequestMethod(),
            (Map<String, Object>) requestExchange.getAttribute("parameters"));
    }
}
