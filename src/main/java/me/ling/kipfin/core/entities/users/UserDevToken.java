package me.ling.kipfin.core.entities.users;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Токен разработчика
 */
public class UserDevToken {

    @JsonProperty("dev_token_id")
    protected Integer tokenId;

    @JsonProperty("dev_token_user_id")
    protected Integer userId;

    @JsonProperty("dev_token_state")
    protected Integer state;

    @JsonProperty("dev_token_string")
    protected String tokenString;

    @JsonProperty("dev_token_title")
    protected String title;

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
