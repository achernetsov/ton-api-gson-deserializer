package ru.archertech.ton.serialization;

import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class TonDeserializer<T> implements JsonDeserializer<T> {
    protected static final Gson cleanGson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Map<String, JsonElement> fieldsMap = json.getAsJsonObject().asMap();
        if (Objects.nonNull(stringFieldsToConvertToByteArrays())) {
            stringFieldsToConvertToByteArrays().forEach(field -> stringFieldsToByteArray(fieldsMap, field));
        }

        return deserializeInternal(json, typeOfT, context);
    }

    protected T deserializeInternal(JsonElement json,
                                    Type typeOfT,
                                    JsonDeserializationContext context)
            throws JsonParseException {
        return cleanGson.fromJson(json, typeOfT);
    }

    protected Set<String> stringFieldsToConvertToByteArrays() {
        return null;
    }

    /**
     * Substitutes String json element to bytes array json element
     */
    protected static void stringFieldsToByteArray(Map<String, JsonElement> fieldsMap, String fieldName) {
        JsonElement jsonElement = fieldsMap.get(fieldName);
        if (jsonElement.isJsonArray()) return;
        String dataStr = jsonElement.getAsString();
        byte[] dataBytes = dataStr.getBytes();
        JsonElement dataBytesJsonElement = cleanGson.toJsonTree(dataBytes);
        fieldsMap.put(fieldName, dataBytesJsonElement);
    }

    protected static Class<?> tonClass(Map<String, JsonElement> fieldsMap) {
        String typeName = fieldsMap.get("@type").getAsString();
        return tonClass(typeName);
    }

    private static Class<?> tonClass(String typeFromJson) {
        String simpleName = Arrays.stream(typeFromJson.split("\\.")).map(StringUtils::capitalize).collect(Collectors.joining());
        String className = "drinkless.org.ton.TonApi$" + simpleName;
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
