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

package me.ling.core.entities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    private final TimetableWithClassroomItemEntity[] timetable;
    @JsonProperty
    private final ClassroomsItem[] classrooms;

    public TimetableMaster(Integer weekIndex, Integer dayOfWeekIndex, String date,
                           TimetableWithClassroomItemEntity[] timetable, ClassroomsItem[] classrooms) {
        this.weekIndex = weekIndex;
        this.dayOfWeekIndex = dayOfWeekIndex;
        this.date = date;
        this.timetable = timetable;
        this.classrooms = classrooms;
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
    public TimetableWithClassroomItemEntity[] getTimetable() {
        return timetable;
    }

    /**
     * Возвращает расписание для преподавателей
     *
     * @return аудитории
     */
    public ClassroomsItem[] getClassrooms() {
        return classrooms;
    }
}
