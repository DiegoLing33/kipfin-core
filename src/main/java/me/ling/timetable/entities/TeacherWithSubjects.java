package me.ling.timetable.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TeacherWithSubjects {


    @JsonProperty
    private final String teacher;

    @JsonProperty
    private final List<ClassroomsItem> items;

    public TeacherWithSubjects(String teacher, List<ClassroomsItem> items) {
        this.teacher = teacher;
        this.items = items;
    }

    /**
     * Возвращает преподавателя
     *
     * @return группа
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     * Возвращает предметы
     *
     * @return предметы
     */
    public List<ClassroomsItem> getItems() {
        return items;
    }
}
