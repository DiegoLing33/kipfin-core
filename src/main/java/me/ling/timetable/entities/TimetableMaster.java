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

package me.ling.timetable.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Мастер-расписание
 */
public class TimetableMaster {

    @JsonProperty
    private final Integer weekIndex;
    @JsonProperty
    private final Integer dayOfWeekIndex;
    @JsonProperty
    private final String date;
    @JsonProperty
    private final List<GroupWithSubjects> timetable;
    @JsonProperty
    private final List<TeacherWithSubjects> teachers;

    public TimetableMaster(Integer weekIndex, Integer dayOfWeekIndex, String date, List<GroupWithSubjects> timetable,
                           List<TeacherWithSubjects> teachers) {
        this.weekIndex = weekIndex;
        this.dayOfWeekIndex = dayOfWeekIndex;
        this.date = date;
        this.timetable = timetable;
        this.teachers = teachers;
    }

    /**
     * Возвращает индекс недели для определения четности/нечетности
     *
     * @return индекс недели
     */
    public Integer getWeekIndex() {
        return weekIndex;
    }

    /**
     * Возвращает индекс дня недели
     *
     * @return индекс дня недели 0...6
     */
    public Integer getDayOfWeekIndex() {
        return dayOfWeekIndex;
    }

    /**
     * Возвращает дату
     *
     * @return дата расписания
     */
    public String getDate() {
        return date;
    }

    /**
     * Возвращает расписание для студентов
     *
     * @return объекты расписания
     */
    public List<GroupWithSubjects> getTimetable() {
        return timetable;
    }

    /**
     * Возвращает расписание для преподавателей
     *
     * @return аудитории
     */
    public List<TeacherWithSubjects> getTeachers() {
        return teachers;
    }
}
