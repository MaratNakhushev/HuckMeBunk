package utils;

import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.qameta.allure.internal.shadowed.jackson.databind.node.JsonNodeType;

import java.io.File;
import java.io.IOException;

public class JsonReader {
    private static final ObjectMapper mapper = new ObjectMapper();

    private static JsonNode jsonRoot(String setFile) {
        var jsonFile = new File(setFile);

        try {
            return mapper.readTree(jsonFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getStringParam(String setFile, String param) {
        var node = jsonRoot(setFile).get(param);
        if (node.getNodeType() != JsonNodeType.STRING) {
            throw new RuntimeException("Param is not a string");
        }
        return node.asText();
    }

    public static Boolean getBoolParam(String setFile, String param) {
        var node = jsonRoot(setFile).get(param);
        if (node.getNodeType() != JsonNodeType.BOOLEAN) {
            throw new RuntimeException("Param is not a boolean");
        }
        return node.asBoolean();
    }

    public static int getNumParam(String setFile, String param) {
        var paramNode = jsonRoot(setFile).get(param);
        if (paramNode.getNodeType() != JsonNodeType.NUMBER) {
            throw new IllegalArgumentException("Invalid JSON format or WaitTime is not a number");
        }
        return paramNode.asInt();
    }
}