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

package me.ling.core.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Инструменты логгера
 */
public final class Logger {

    // Литералы
    public static final String YES = String.format("[ %s ]", LoggerColors.getColoredString(LoggerColors.ANSI_GREEN, "YES"));
    public static final String NO = String.format("[ %s ]", LoggerColors.getColoredString(LoggerColors.ANSI_RED, "NO "));
    public static final String WAIT = String.format("[ %s ]", LoggerColors.getColoredString(LoggerColors.ANSI_YELLOW, "~~~"));

    /**
     * Тсандартный фильтр имени логгера
     *
     * @param name - имя логгера
     * @return - имя логгера со стилем
     */
    private static String filterName(String name) {
        if (name.equals("Builder")) return LoggerColors.getColoredString(LoggerColors.ANSI_CYAN, name);
        if (name.equals("Tester")) return LoggerColors.getColoredString(LoggerColors.ANSI_RED, name);
        if (name.equals("Parser")) return LoggerColors.getColoredString(LoggerColors.ANSI_PURPLE, name);
        if (name.equals("Logger")) return LoggerColors.getColoredString(LoggerColors.ANSI_BLUE, name);
        return name;
    }

    /**
     * Возвращает стркоу в квадратных скобках
     *
     * @param s - исходная строка
     * @return - формитрованная строка
     */
    public static String inQuad(String s) {
        return String.format("[ %s ]", s);
    }

    /**
     * Формирует строку логирования
     *
     * @param name    - имя логгера
     * @param message - сообщение
     * @return - строка логирования
     */
    public static String getLoggerString(String name, Object... message) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        Object[] strings = Arrays.stream(message).map(o -> {
            if (o instanceof Boolean) return ((Boolean) o) ? YES : NO;
            return o.toString();
        }).toArray();
        return String.format("[%s][%s]: %s", dateTime.format(simpleDateFormat), filterName(name),
                String.join(" ", Arrays.copyOf(strings, strings.length, String[].class)));
    }

    /**
     * Выводит лог от имени
     *
     * @param name    - имя логгера
     * @param message - строка логирования
     */
    public static void logAs(String name, Object... message) {
        System.out.println(Logger.getLoggerString(name, message));
    }

    /**
     * Выводит лог
     *
     * @param message - строка логирования
     */
    public static void log(Object... message) {
        Logger.logAs("Logger", message);
    }

    /**
     * Создает логгер
     *
     * @param name - имя логгера
     * @return - лямбда логгер
     */
    public static Consumer<Object[]> createLogger(String name) {
        return objects -> Logger.logAs(name, objects);
    }
}
