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

package me.ling.kipfin.timetable.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Мастер-расписание
 */
public class TimetableMaster {

    @JsonProperty
    private Integer weekIndex;
    @JsonProperty
    private Integer dayOfWeekIndex;
    @JsonProperty
    private String date;
    @JsonProperty
    private List<GroupWithSubjects> timetable;
    @JsonProperty
    private List<TeacherWithSubjects> teachers;

    public TimetableMaster() {
    }

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

    /**
     * Возвращает расписание для группы
     *
     * @param groupName - группа
     * @return - расписание
     */
    @Nullable
    public GroupWithSubjects getSubjects(String groupName) {
        List<GroupWithSubjects> group = this.getTimetable().stream().filter(g -> g.getGroup().equals(groupName))
                .collect(Collectors.toList());
        return group.size() > 0 ? group.get(0) : null;
    }

    /**
     * Возвращает расписание для преподавателя
     *
     * @param teacherName - имя преподавателя
     * @return - расписание для преподавателя
     */
    @Nullable
    public TeacherWithSubjects getTeacher(String teacherName) {
        List<TeacherWithSubjects> teacher = this.getTeachers().stream().filter(g -> g.getTeacher().equals(teacherName))
                .collect(Collectors.toList());
        return teacher.size() > 0 ? teacher.get(0) : null;
    }
}
