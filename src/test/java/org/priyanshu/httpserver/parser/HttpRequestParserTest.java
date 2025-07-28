package org.priyanshu.httpserver.parser;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HttpRequestParserTest {

    @Test
    void testParseSimpleGetRequest() throws Exception {
        String rawRequest = """
                GET /index.html HTTP/1.1\r
                Host: localhost\r
                User-Agent: curl/8.5.0\r
                Accept: */*\r
                \r
                """;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(rawRequest.getBytes(StandardCharsets.UTF_8));
        HttpRequest request = HttpRequestParser.parse(inputStream);

        assertEquals("GET", request.getMethod());
        assertEquals("/index.html", request.getPath());
        assertEquals("HTTP/1.1", request.getVersion());

        Map<String, String> headers = request.getHeaders();
        assertEquals("localhost", headers.get("Host"));
        assertEquals("curl/8.5.0", headers.get("User-Agent"));
        assertEquals("*/*", headers.get("Accept"));

        assertNull(request.getBody());
    }

    @Test
    void testParsePostRequestWithBody() throws Exception {
        String rawRequest = """
                POST /submit HTTP/1.1\r
                Host: localhost\r
                Content-Type: application/x-www-form-urlencoded\r
                Content-Length: 15\r
                \r
                name=Bablu+Bhai
                """;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(rawRequest.getBytes(StandardCharsets.UTF_8));
        HttpRequest request = HttpRequestParser.parse(inputStream);

        assertEquals("POST", request.getMethod());
        assertEquals("/submit", request.getPath());
        assertEquals("HTTP/1.1", request.getVersion());

        Map<String, String> headers = request.getHeaders();
        assertEquals("localhost", headers.get("Host"));
        assertEquals("application/x-www-form-urlencoded", headers.get("Content-Type"));

        assertEquals("name=Bablu+Bhai", request.getBody());
    }
}
