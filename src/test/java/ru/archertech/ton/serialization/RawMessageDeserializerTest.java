package ru.archertech.ton.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import drinkless.org.ton.TonApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RawMessageDeserializerTest {
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(TonApi.RawMessage.class, new RawMessageDeserializer())
            .create();

    @Test
    public void test() {
        String json = TestUtils.readJson("ton/rawMessage.json");
        TonApi.RawMessage rawMessage = gson.fromJson(json, TonApi.RawMessage.class);
        assertEquals(4984900000L, rawMessage.value);
        assertEquals("EQAjwXTK01KLwUiK66EczECCv3w0t-KnKrXIIBs3vq2Oi7BH", rawMessage.source.accountAddress);
        assertEquals("EQCwHyzOrKP1lBHbvMrFHChifc1TLgeJVpKgHpL9sluHU-gV", rawMessage.destination.accountAddress);
        assertEquals(666672L, rawMessage.fwdFee);
        assertEquals(0, rawMessage.ihrFee);
        assertEquals(14038056000002L, rawMessage.createdLt);
        assertEquals("lqKW0iTyhcZ77pPDD4owkVfw2qNdxbh+QQt4YwoJz8c=", new String(rawMessage.bodyHash));

        TonApi.MsgDataRaw msgData = (TonApi.MsgDataRaw) rawMessage.msgData;
        assertEquals("te6cckEBAQEAAgAAAEysuc0=", new String(msgData.body));
    }
}