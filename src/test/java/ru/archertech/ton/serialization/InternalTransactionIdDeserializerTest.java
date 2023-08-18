package ru.archertech.ton.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import drinkless.org.ton.TonApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InternalTransactionIdDeserializerTest {
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(TonApi.InternalTransactionId.class, new InternalTransactionIdDeserializer())
            .create();

    @Test
    public void test() {
        String json = """
                {
                    "@type": "internal.transactionId",
                    "lt": "14038056000001",
                    "hash": "jJbBpM/CQIGNfCi7wpFJ8C0L9Pbp93vzgQI/UxBWQ2s="
                  }
                """;
        TonApi.InternalTransactionId internalTransactionId = gson.fromJson(json, TonApi.InternalTransactionId.class);
        assertEquals(14038056000001L, internalTransactionId.lt);
        assertEquals("jJbBpM/CQIGNfCi7wpFJ8C0L9Pbp93vzgQI/UxBWQ2s=", new String(internalTransactionId.hash));
    }

}