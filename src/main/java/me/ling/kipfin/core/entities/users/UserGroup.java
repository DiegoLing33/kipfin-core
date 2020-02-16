package me.ling.kipfin.core.entities.users;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Группа пользователя
 */
public class UserGroup {

    @JsonProperty("group_id")
    protected Integer groupId;

    @JsonProperty("group_title")
    protected String title;

    @JsonProperty("group_access")
    protected String access;

    public UserGroup() {
    }

    public UserGroup(Integer groupId, String title, String access) {
        this.groupId = groupId;
        this.title = title;
        this.access = access;
    }

    /**
     * Возвращает ID группы пользователей
     *
     * @return - идентификатор группы пользователей
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * Возвращает название группы пользователей
     *
     * @return - название группы пользователей
     */
    public String getTitle() {
        return title;
    }

    /**
     * Возвращает строку доступа группы пользователей
     *
     * @return - строка доступа
     */
    public String getAccess() {
        return access;
    }
}
