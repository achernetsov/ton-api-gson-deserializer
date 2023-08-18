package ru.archertech.ton.serialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import drinkless.org.ton.TonApi;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MsgDataTextDeserializer extends TonDeserializer<TonApi.MsgDataText> {
    @Override
    public TonApi.MsgDataText deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Map<String, JsonElement> fieldsMap = json.getAsJsonObject().asMap();
        if (Objects.nonNull(stringFieldsToConvertToByteArrays())) {
            stringFieldsToConvertToByteArrays().forEach(field -> stringFieldsToByteArray(fieldsMap, field));
        }
        return cleanGson.fromJson(json, typeOfT);
    }

    @Override
    protected Set<String> stringFieldsToConvertToByteArrays() {
        return Set.of("text");
    }
}
