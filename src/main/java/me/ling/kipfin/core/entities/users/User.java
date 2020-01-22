package me.ling.kipfin.core.entities.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.ling.kipfin.core.sql.Column;

/**
 * Пользователь
 */
public class User {

    @Column(name = "user_id", type = Integer.class)
    @JsonProperty("user_id")
    protected Integer userId;

    @Column(name = "group_id", type = Integer.class)
    @JsonProperty("user_group_id")
    protected Integer groupId;

    @Column(name = "login")
    @JsonProperty("user_login")
    protected String login;

    @Column(name = "name")
    @JsonProperty("user_name")
    protected String name;

    public User() {
    }

    public User(Integer userId, Integer groupId, String login, String name) {
        this.userId = userId;
        this.groupId = groupId;
        this.login = login;
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }
}
