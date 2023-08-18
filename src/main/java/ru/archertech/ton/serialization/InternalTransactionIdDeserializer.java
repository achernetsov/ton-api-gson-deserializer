package ru.archertech.ton.serialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import drinkless.org.ton.TonApi;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

public class InternalTransactionIdDeserializer extends TonDeserializer<TonApi.InternalTransactionId> {
    @Override
    public TonApi.InternalTransactionId deserialize(JsonElement jsonElement, Type typeOfT,
                                                    JsonDeserializationContext context) throws JsonParseException {
        Map<String, JsonElement> fieldsMap = jsonElement.getAsJsonObject().asMap();
        stringFieldsToByteArray(fieldsMap, "hash");
        return cleanGson.fromJson(jsonElement, typeOfT);
    }

    @Override
    protected Set<String> stringFieldsToConvertToByteArrays() {
        return Set.of("hash");
    }
}
