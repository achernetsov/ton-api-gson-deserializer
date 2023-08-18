package ru.archertech.ton.serialization;

import com.google.gson.*;
import drinkless.org.ton.TonApi;

import java.lang.reflect.Type;
import java.util.Set;

public class RawTransactionDeserializer extends TonDeserializer<TonApi.RawTransaction> {
    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(TonApi.InternalTransactionId.class, new InternalTransactionIdDeserializer())
            .registerTypeAdapter(TonApi.RawMessage.class, new RawMessageDeserializer())
            .create();

    @Override
    protected TonApi.RawTransaction deserializeInternal(JsonElement json,
                                                        Type typeOfT,
                                                        JsonDeserializationContext context) throws JsonParseException {
        return gson.fromJson(json, typeOfT);
    }

    @Override
    protected Set<String> stringFieldsToConvertToByteArrays() {
        return Set.of("data");
    }
}
