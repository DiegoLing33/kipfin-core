package me.ling.kipfin.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Утилиты URL
 */
public class URLUtils {

    /**
     * Скачивает содержимое строки
     *
     * @param url - адрес
     * @return - содержимое
     * @throws IOException - исключение при чтении URL
     */
    public static String downloadString(String url) throws IOException {
        URL urlObject = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(urlObject.openStream()));
        StringBuilder sb = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }
        in.close();
        return sb.toString();
    }

    /**
     * Скачивает JSON файл
     *
     * @param tClass - представление JSON
     * @param url    - адрес
     * @param <T>    - тип представления
     * @return - представление через JSON
     * @throws IOException - исключение при чтении URL
     */
    public static <T> T downloadJson(Class<T> tClass, String url) throws IOException {
        return new ObjectMapper().readValue(new URL(url), tClass);
    }
}
