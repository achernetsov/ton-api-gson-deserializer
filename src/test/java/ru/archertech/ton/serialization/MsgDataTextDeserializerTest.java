package ru.archertech.ton.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import drinkless.org.ton.TonApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MsgDataTextDeserializerTest {
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(TonApi.MsgDataText.class, new MsgDataTextDeserializer())
            .create();

    @Test
    public void test() {
        String json = """
                {
                  "@type": "msg.dataText",
                  "text": ""
                }
                """;
        TonApi.MsgDataText msgDataText = gson.fromJson(json, TonApi.MsgDataText.class);
        assertEquals("", new String(msgDataText.text));
    }
}