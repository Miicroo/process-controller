import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import model.PropertyManager;
import model.web.ParameterFilter;
import model.web.WebServer;

import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {
        int port = PropertyManager.getIntProperty("webserver.port", 8000);

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        HttpContext context = server.createContext("/", new WebServer());
        context.getFilters().add(new ParameterFilter()); // Filter setting correct parameters
        server.setExecutor(null);
        server.start();

        System.out.println("Started server on port "+port);
    }
}
