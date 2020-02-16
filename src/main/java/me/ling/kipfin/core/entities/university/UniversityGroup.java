package me.ling.kipfin.core.entities.university;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Группа университета
 */
public class UniversityGroup {

    @JsonProperty("group_id")
    protected Integer groupId;

    @JsonProperty("group_title")
    protected String title;

    public UniversityGroup() {
    }

    public UniversityGroup(Integer groupId, String title) {
        this.groupId = groupId;
        this.title = title;
    }

    /**
     * Возвращает идентификатор группы
     * @return - идентификатор группы
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * Возвращает название группы
     * @return  - название группы
     */
    public String getTitle() {
        return title;
    }
}
