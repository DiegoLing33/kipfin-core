/*
 *
 *   ██████╗░██╗███████╗░██████╗░░█████╗░  ██╗░░░░░██╗███╗░░██╗░██████╗░
 *   ██╔══██╗██║██╔════╝██╔════╝░██╔══██╗  ██║░░░░░██║████╗░██║██╔════╝░
 *   ██║░░██║██║█████╗░░██║░░██╗░██║░░██║  ██║░░░░░██║██╔██╗██║██║░░██╗░
 *   ██║░░██║██║██╔══╝░░██║░░╚██╗██║░░██║  ██║░░░░░██║██║╚████║██║░░╚██╗
 *   ██████╔╝██║███████╗╚██████╔╝╚█████╔╝  ███████╗██║██║░╚███║╚██████╔╝
 *   ╚═════╝░╚═╝╚══════╝░╚═════╝░░╚════╝░  ╚══════╝╚═╝╚═╝░░╚══╝░╚═════╝░
 *
 *   Это программное обеспечение имеет лицензию, как это сказано в файле
 *   COPYING, который Вы должны были получить в рамках распространения ПО.
 *
 *   Использование, изменение, копирование, распространение, обмен/продажа
 *   могут выполняться исключительно в согласии с условиями файла COPYING.
 *
 *   Mail: diegoling33@gmail.com
 *
 */

package me.ling.kipfin.core.utils;

import org.jetbrains.annotations.NotNull;

/**
 * Утилиты строк
 */
public class StringUtils {

    /**
     * Удаляет все лишиние пробелы
     *
     * @param s - входная строка
     * @return - выходная строка
     */
    @NotNull
    public static String removeAllSpaces(@NotNull String s) {
        return s.replaceAll("^\\s+|\\s+$", "").replaceAll("\\s+", " ");
    }

    /**
     * Возвращает строку с заглавной буквы
     *
     * @param s - исходная строка
     * @return - строка с заглавной буквы
     */
    @NotNull
    public static String title(@NotNull String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * Создает и возвращает случайную строку длиной n из символов source
     *
     * @param n      - длина
     * @param source - алфавит символов
     * @return - случайная сгенерированная стрка
     */
    @NotNull
    public static String getRandomString(int n, String source) {
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (source.length() * Math.random());
            sb.append(source.charAt(index));
        }
        return sb.toString();
    }

    /**
     * Возвращает случайную строку размера n
     *
     * @param n - длина случайной строки
     * @return - случайная строка
     */
    @NotNull
    public static String getAlphaNumericString(int n) {
        return StringUtils.getRandomString(n,
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz");
    }
}
