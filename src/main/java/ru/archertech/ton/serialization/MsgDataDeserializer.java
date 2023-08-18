package ru.archertech.ton.serialization;

import com.google.gson.*;
import drinkless.org.ton.TonApi;

import java.lang.reflect.Type;
import java.util.Map;

public class MsgDataDeserializer extends TonDeserializer<TonApi.MsgData> {
    // // https://stackoverflow.com/a/8683689/827704
    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(TonApi.MsgDataRaw.class, new MsgDataRawDeserializer())
            .registerTypeAdapter(TonApi.MsgDataText.class, new MsgDataTextDeserializer())
            .create();

    @Override
    public TonApi.MsgData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Map<String, JsonElement> fieldsMap = json.getAsJsonObject().asMap();
        //noinspection unchecked
        Class<? extends TonApi.MsgData> msgDataClass = (Class<? extends TonApi.MsgData>) tonClass(fieldsMap);
        return gson.fromJson(json, msgDataClass);
    }
}
