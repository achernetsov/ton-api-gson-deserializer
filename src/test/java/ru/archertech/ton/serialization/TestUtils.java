package ru.archertech.ton.serialization;

import java.io.IOException;
import java.util.Objects;

public class TestUtils {
    public static String readJson(String resourceFile) {
        try {
            return new String(Objects.requireNonNull(TestUtils.class.getClassLoader()
                    .getResourceAsStream(resourceFile)).readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
