package ru.archertech.ton.serialization;

import com.google.gson.*;
import drinkless.org.ton.TonApi;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MsgDataRawDeserializer extends TonDeserializer<TonApi.MsgDataRaw> {
    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    @Override
    public TonApi.MsgDataRaw deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Map<String, JsonElement> fieldsMap = json.getAsJsonObject().asMap();
        if (Objects.nonNull(stringFieldsToConvertToByteArrays())) {
            stringFieldsToConvertToByteArrays().forEach(field -> stringFieldsToByteArray(fieldsMap, field));
        }
        return gson.fromJson(json, typeOfT);
    }

    @Override
    protected Set<String> stringFieldsToConvertToByteArrays() {
        return Set.of("body", "init_state");
    }
}
