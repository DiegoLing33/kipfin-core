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

import me.ling.kipfin.exceptions.WrongDateException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Утилиты даты
 */
public final class DateUtils {

    public static String TIME_FORMAT = "HH:mm:ss";
    public static String DATE_FORMAT = "dd.MM.yyyy";
    public static final String[] weekDaysNames = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
    public static final String localDatePattern = "^[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}$";

    /**
     * Формирует дату в RUS строку
     *
     * @param date - дата
     * @return - строка даты
     */
    @NotNull
    public static String toLocalDateString(@NotNull LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return date.format(formatter);
    }

    /**
     * Преобразует локальный ввод в дату
     *
     * @param localDateString - строка в фодмате дд.мм.ГГГГ
     * @return - объект даты
     */
    public static LocalDate fromLocalDateString(String localDateString) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(localDateString, formatter);
    }

    /**
     * Возвращает true, если введенная строка проходит по паттерну
     *
     * @param localDateString - исходная стркоа в РУС формате
     * @return - результат тестирования
     */
    @NotNull
    @Contract(pure = true)
    public static Boolean isStringDateInLocalFormat(@NotNull String localDateString) {
        return localDateString.matches(DateUtils.localDatePattern);
    }

    /**
     * Проверяет строку даты на формат и выбрасывает исключение, в случае ошибки
     *
     * @param localDateString - строка даты
     * @return - проверяемая стркоа даты
     * @throws WrongDateException - ошибка проверки даты
     */
    public static String testDateFormat(String localDateString) throws WrongDateException {
        if (DateUtils.isStringDateInLocalFormat(localDateString)) return localDateString;
        throw new WrongDateException(localDateString);
    }

    /**
     * Преобразует время в локальную строку
     * @param time  - время
     * @return  - строка в формате `DateUtils::TIME_FORMAT`
     */
    @NotNull
    public static String toLocalTimeString(@NotNull LocalTime time){
        return time.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    /**
     * Возвращает время из локальной строки времени
     * @param localTime - локальная строка времени
     * @return  - объект LocalTime
     */
    public static LocalTime getTimeFromLocalTimeString(String localTime){
        return LocalTime.parse(localTime, DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    /**
     * Возвращает день недели
     *
     * @param date - дата
     * @return - день недели (индекс)
     */
    @NotNull
    public static Integer getLocalWeekDay(@NotNull LocalDate date) {
        return date.getDayOfWeek().getValue() - 1;
    }

    /**
     * Возвращает разницу между датами в кол-ве недель
     *
     * @param startDate   - первая дата
     * @param currentDate - вторая дата
     * @return - количество недель между датами
     */
    @NotNull
    public static Integer getWeeksCount(@NotNull LocalDate startDate, @NotNull LocalDate currentDate) {
        long ms1 = startDate.toEpochDay();
        long ms2 = currentDate.toEpochDay();
        long dif = Math.abs(ms2 - ms1);

        return ((int) Math.floor(dif / 7.0));
    }
}
