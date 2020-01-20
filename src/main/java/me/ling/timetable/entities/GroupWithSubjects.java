package me.ling.timetable.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Группа с предметами
 */
public class GroupWithSubjects {

    @JsonProperty
    private final String group;

    @JsonProperty
    private final List<GroupSubject> subjects;

    public GroupWithSubjects(String group, List<GroupSubject> subjects) {
        this.group = group;
        this.subjects = subjects;
    }

    /**
     * Возвращает группу
     * @return группа
     */
    public String getGroup() {
        return group;
    }

    /**
     * Возвращает предметы
     * @return предметы
     */
    public List<GroupSubject> getSubjects() {
        return subjects;
    }

}
