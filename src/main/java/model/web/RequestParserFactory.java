package model.web;

import demo.model.web.requestparser.LsInfoRequestParser;
import demo.model.web.requestparser.MemInfoRequestParser;
import demo.model.web.requestparser.SysInfoRequestParser;
import model.web.requestparser.FileRequestParser;
import model.web.requestparser.RequestParser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Static factory class for mapping requests to a handler.
 * Use regex patterns to generate a match for each supported requestUri.
 *
 * Note: There is no guarantee that the patterns will be tested in the order that they were specified!
 * Use patterns that match well!
 */

public class RequestParserFactory {

    private static final Map<Pattern, RequestParser> requestParserMap = new HashMap<>();

    static {
        FileRequestParser fileParser = new FileRequestParser();
        MemInfoRequestParser memInfoRequestParser = new MemInfoRequestParser();
        SysInfoRequestParser sysInfoRequestParser = new SysInfoRequestParser();
        LsInfoRequestParser lsInfoRequestParser = new LsInfoRequestParser();

        requestParserMap.put(createPattern("/"), fileParser);
        requestParserMap.put(createPattern("(/(.*))\\.(.+)"), fileParser);

        requestParserMap.put(createPattern("/memInfo"), memInfoRequestParser);
        requestParserMap.put(createPattern("/sysInfo"), sysInfoRequestParser);
        requestParserMap.put(createPattern("/ls(.*)"), lsInfoRequestParser);
    }

    public static RequestParser getRequestParser(String requestUri) {
        for(Map.Entry<Pattern, RequestParser> entry : requestParserMap.entrySet()) {
            Matcher matcher = entry.getKey().matcher(requestUri);
            if (matcher.matches()) {
                return entry.getValue();
            }
        }
        return null;
    }

    private static Pattern createPattern(String s) {
        return Pattern.compile(s);
    }
}
