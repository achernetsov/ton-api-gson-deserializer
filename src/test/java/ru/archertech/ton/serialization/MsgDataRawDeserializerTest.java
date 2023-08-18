package ru.archertech.ton.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import drinkless.org.ton.TonApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MsgDataRawDeserializerTest {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(TonApi.MsgDataRaw.class, new MsgDataRawDeserializer())
            .create();

    @Test
    public void test() {
        String json = """
                {
                    "@type": "msg.dataRaw",
                    "body": "te6cckEBAQEAAgAAAEysuc0=",
                    "init_state": ""
                  }
                """;
        TonApi.MsgDataRaw msgDataRaw = gson.fromJson(json, TonApi.MsgDataRaw.class);
        assertEquals("te6cckEBAQEAAgAAAEysuc0=", new String(msgDataRaw.body));
    }

}