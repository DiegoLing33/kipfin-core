package me.ling.kipfin.core.entities.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.ling.kipfin.core.sql.Column;

/**
 * Токен разработчика
 */
public class UserDevToken {

    @Column(name = "dev_token_id", type = Integer.class)
    @JsonProperty("dev_token_id")
    protected Integer tokenId;

    @Column(name = "dev_token_user_id", type = Integer.class)
    @JsonProperty("dev_token_user_id")
    protected Integer userId;

    @Column(name = "dev_token_state", type = Integer.class)
    @JsonProperty("dev_token_state")
    protected Integer state;

    @Column(name = "dev_token_string")
    @JsonProperty("dev_token_string")
    protected String tokenString;

    @Column(name = "dev_token_title")
    @JsonProperty("dev_token_title")
    protected String title;

    @Column(name = "dev_token_description")
    @JsonProperty("dev_token_description")
    protected String description;

    public UserDevToken() {
    }

    public UserDevToken(Integer tokenId, Integer userId, Integer state, String tokenString,
                        String title, String description) {
        this.tokenId = tokenId;
        this.userId = userId;
        this.state = state;
        this.tokenString = tokenString;
        this.title = title;
        this.description = description;
    }

    public Integer getTokenId() {
        return tokenId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getState() {
        return state;
    }

    public String getTokenString() {
        return tokenString;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
