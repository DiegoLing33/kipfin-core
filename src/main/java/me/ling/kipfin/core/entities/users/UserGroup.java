package me.ling.kipfin.core.entities.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.ling.kipfin.core.sql.Column;

/**
 * Группа пользователя
 */
public class UserGroup {

    @Column(name = "group_id")
    @JsonProperty("group_id")
    protected Integer groupId;

    @Column(name = "title")
    @JsonProperty("group_title")
    protected String title;

    @Column(name = "access")
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
