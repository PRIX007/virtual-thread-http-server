package org.priyanshu.httpserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
    private static ObjectMapper objectMapper= defaultObjectMapper();

    private static ObjectMapper defaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return mapper;
    }

    public static JsonNode parse(String jsonStr) throws JsonProcessingException {

        return objectMapper.readTree(jsonStr);
    }

    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node, clazz);
    }

    public static JsonNode toJson(Object obj) {
        return objectMapper.valueToTree(obj);
    }

    public static String stringify(Object obj) throws JsonProcessingException {
        return generateJson(obj, false);
    }
    public static String stringifyPretty(Object obj) throws JsonProcessingException {
        return generateJson(obj, true);
    }

    public static String generateJson(Object obj, boolean pretty) throws JsonProcessingException {
        return pretty ? objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj) : objectMapper.writeValueAsString(obj);
    }
}
