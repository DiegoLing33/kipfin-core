package me.ling.kipfin.timetable.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TeacherWithSubjects {


    @JsonProperty
    private String teacher;

    @JsonProperty
    private List<ClassroomsItem> items;

    public TeacherWithSubjects(){}
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
