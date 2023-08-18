# Overview

Deserialize [TON API](https://testnet.toncenter.com/api/v2/) responses
to [tonlib-java](https://github.com/achernetsov/tonlib-java) POJOs

Uses GSON

# Usage

GetTransactionsResponseDeserializationTest:

```java
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
```

# Supported API methods

- https://testnet.toncenter.com/api/v2/#/transactions/get_transactions_getTransactions_get