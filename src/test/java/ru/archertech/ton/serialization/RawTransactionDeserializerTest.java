package ru.archertech.ton.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import drinkless.org.ton.TonApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RawTransactionDeserializerTest {
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(TonApi.RawTransaction.class, new RawTransactionDeserializer())
            .create();

    @Test
    public void test() {
        String txJson = TestUtils.readJson("ton/tx.json");
        TonApi.RawTransaction tx = gson.fromJson(txJson, TonApi.RawTransaction.class);
        assertEquals(1692132157, tx.utime);
        assertEquals("te6cckECDQEAAtsAA7VyPBdMrTUovBSIrroRzMQIK/fDS34qcqtcggGze+rY6LAAAMxH1XWgE8kKe4JIoyLbRLPhXPezmcvBYxMFiSKqRTfSjpRdapIgAADMR8zgXDZNvjPQACRrw2QoAQIDAgHgBAUAgnJbKC6C2ka8d89mEgVieBT3iW1biMkfQpwRO69ksvHIxM9dul6pMcbPUTsJdRFVCYYIeQDrIbfRSHuGZ05x3kWfAg8MQEYZZBREQAsMAt+IAEeC6ZWmpReCkRXXQjmYgQV++GlvxU5Va5BANm99Wx0WGE2AiWaLH4k5/zlnsnFGIU75O5ZVRKE2TdysfjnxcjITIV6DumoGZ/cgJFtXljdJyvWnCHrBl25+abfgpp8kSAimpoxf/////AAAAAAOBgcBAd8KAgE0CAkAamIAWA+WZ1ZR+soI7d5lYo4UMT7mqZcDxKtJUA9Jftktw6moCUj8TQAAAAAAAAAAAAAAAAAAAMD/ACDdIIIBTJe6lzDtRNDXCx/gpPJggwjXGCDTH9Mf0x/4IxO78mPtRNDTH9Mf0//RUTK68qFRRLryogT5AVQQVfkQ8qP4AJMg10qW0wfUAvsA6NEBpMjLH8sfy//J7VQAUAAAAAApqaMXJpky0i5DOY/7buQbM6sH+BpFvgWFzpBzivQ2OHWtLz8As2gAR4LplaalF4KRFddCOZiBBX74aW/FTlVrkEA2b31bHRcALAfLM6so/WUEdu8ysUcKGJ9zVMuB4lWkqAekv2yW4dTUBKR+JoAGFFhgAAAZiPqutATJt8Z6QACdQWyjE4gAAAAAAAAAAA+AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIABvyYehIEwUWEAAAAAAAAIAAAAAAAMo6Z/ZLG/gQFbUDZjMAnLaodvdfN8qHuQRYl/VQzkAdEBQFkw6Dx3/",
                new String(tx.data));

        TonApi.InternalTransactionId internalTransactionId = new TonApi.InternalTransactionId(14038056000001L,
                "jJbBpM/CQIGNfCi7wpFJ8C0L9Pbp93vzgQI/UxBWQ2s=".getBytes());
        assertEquals(internalTransactionId.lt, tx.transactionId.lt);
        assertEquals(new String(internalTransactionId.hash), new String(tx.transactionId.hash));

        assertEquals(6834001L, tx.fee);
        assertEquals(1L, tx.storageFee);
        assertEquals(6834000L, tx.otherFee);

        assertEquals("EQAjwXTK01KLwUiK66EczECCv3w0t-KnKrXIIBs3vq2Oi7BH", tx.inMsg.destination.accountAddress);

        TonApi.MsgDataRaw msgDataRaw = (TonApi.MsgDataRaw) tx.inMsg.msgData;
        assertEquals("te6cckEBAgEAhwABmhNgIlmix+JOf85Z7JxRiFO+TuWVUShNk3crH458XIyEyFeg7pqBmf3ICRbV5Y3Scr1pwh6wZdufmm34KafJEgIpqaMX/////wAAAAADAQBqYgBYD5ZnVlH6ygjt3mVijhQxPuaplwPEq0lQD0l+2S3DqagJSPxNAAAAAAAAAAAAAAAAAADIYOBr",
                new String(msgDataRaw.body));

        assertEquals(1, tx.outMsgs.length);
        TonApi.RawMessage outMsg = tx.outMsgs[0];
        assertEquals("EQAjwXTK01KLwUiK66EczECCv3w0t-KnKrXIIBs3vq2Oi7BH", outMsg.source.accountAddress);
    }

}