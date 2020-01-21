package me.ling.timetable.utils;

import java.util.Map;

public class TimetableUtils {

    /**
     * Распаковывает WHO карту -> преподаватели
     * @param who - WHO карта
     * @return      - преподаватели
     */
    public static String unpackWhoMapTeachers(Map<String, String> who) {
        return String.join(", ", who.keySet());
    }
    /**
     * Распаковывает WHO карту -> аудитории
     * @param who - WHO карта
     * @return      - аудитории
     */
    public static String unpackWhoMapClassrooms(Map<String, String> who) {
        return String.join(", ", who.values());
    }

}
