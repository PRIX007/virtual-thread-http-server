package org.priyanshu.httpserver.parser;

import java.io.*;
import java.util.*;

public class HttpRequestParser {

    public static HttpRequest parse(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // --- 1. Parse request line ---
        String requestLine = reader.readLine();
        if (requestLine == null || requestLine.isEmpty()) {
            throw new IOException("Invalid request line");
        }

        String[] parts = requestLine.split(" ");
        if (parts.length != 3) throw new IOException("Malformed request line");
        String method = parts[0];
        String path = parts[1];
        String version = parts[2];

        // --- 2. Parse headers ---
        Map<String, String> headers = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            int index = line.indexOf(':');
            if (index != -1) {
                String key = line.substring(0, index).trim();
                String value = line.substring(index + 1).trim();
                headers.put(key, value);
            }
        }

        // --- 3. Parse body if POST ---
        String body = null;
        if (method.equalsIgnoreCase("POST")) {
            int contentLength = 0;
            if (headers.containsKey("Content-Length")) {
                try {
                    contentLength = Integer.parseInt(headers.get("Content-Length"));
                } catch (NumberFormatException ignored) {}
            }

            char[] bodyChars = new char[contentLength];
            int read = reader.read(bodyChars, 0, contentLength);
            body = new String(bodyChars, 0, read);
        }

        return new HttpRequest(method, path, version, headers, body);
    }
}
