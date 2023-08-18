package ru.archertech.ton.serialization;

import com.google.gson.*;
import drinkless.org.ton.TonApi;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class RawMessageDeserializer extends TonDeserializer<TonApi.RawMessage> {
    // https://stackoverflow.com/a/8683689/827704
    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeHierarchyAdapter(TonApi.MsgData.class, new MsgDataDeserializer())
            .create();

    @Override
    public TonApi.RawMessage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Map<String, JsonElement> fieldsMap = json.getAsJsonObject().asMap();
        if (Objects.nonNull(stringFieldsToConvertToByteArrays())) {
            stringFieldsToConvertToByteArrays().forEach(field -> stringFieldsToByteArray(fieldsMap, field));
        }

        String sourceStr = fieldsMap.get("source").getAsString();
        TonApi.AccountAddress source = new TonApi.AccountAddress(sourceStr);
        fieldsMap.put("source", cleanGson.toJsonTree(source));

        String destinationStr = fieldsMap.get("destination").getAsString();
        TonApi.AccountAddress destination = new TonApi.AccountAddress(destinationStr);
        fieldsMap.put("destination", cleanGson.toJsonTree(destination));

        return gson.fromJson(json, typeOfT);
    }

    @Override
    protected Set<String> stringFieldsToConvertToByteArrays() {
        return Set.of("body_hash");
    }
}
