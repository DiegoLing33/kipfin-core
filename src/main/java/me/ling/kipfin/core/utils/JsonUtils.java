package me.ling.kipfin.core.utils;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Утилиты JSON
 */
public class JsonUtils {

    /**
     * Сохраняет объект в JSON
     *
     * @param o    - объект для сериализации
     * @param path - сохранение
     * @throws IOException - исключение сохранения
     */
    public static void saveJson(Object o, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValue(new File(path), o);
    }

}
