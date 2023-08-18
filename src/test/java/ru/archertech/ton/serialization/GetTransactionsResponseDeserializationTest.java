package ru.archertech.ton.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import drinkless.org.ton.TonApi;
import org.junit.jupiter.api.Test;
import ru.archertech.ton.api.TonApiResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <a href="https://testnet.toncenter.com/api/v2/#/transactions/get_transactions_getTransactions_get">Swagger</a>
 */
public class GetTransactionsResponseDeserializationTest {
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(TonApi.RawTransaction.class, new RawTransactionDeserializer())
            .create();

    @Test
    public void test() {
        String json = TestUtils.readJson("ton/response_2tx.json");
        TonApiResponse<List<TonApi.RawTransaction>> getTransactions = gson.fromJson(json, new TypeToken<>() {
        });

        assertTrue(getTransactions.ok);
        assertEquals(2, getTransactions.result.size());
    }
}
