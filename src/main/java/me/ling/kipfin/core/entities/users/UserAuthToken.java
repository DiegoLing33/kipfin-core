package me.ling.kipfin.core.entities.users;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Токен авторизации пользователя
 */
public class UserAuthToken {

    @JsonProperty("token_id")
    protected Integer tokenId;

    @JsonProperty("token_user_id")
    protected Integer userId;

    @JsonProperty("token_string")
    protected String token;

    @JsonProperty("token_state")
    protected Integer state;

    @JsonProperty("token_ip")
    protected String ip;

    @JsonProperty("token_agent")
    protected String agent;

    @JsonProperty("token_date")
    protected String date;

    public UserAuthToken() {
    }

    public UserAuthToken(Integer tokenId, Integer userId, String token,
                         Integer state, String ip, String agent, String date) {
        this.tokenId = tokenId;
        this.userId = userId;
        this.token = token;
        this.state = state;
        this.ip = ip;
        this.agent = agent;
        this.date = date;
    }

    /**
     * Возвращает id токена
     * @return  - иденитфикатор токена
     */
    public Integer getTokenId() {
        return tokenId;
    }

    /**
     * Возвращает id пользователя токена
     * @return  - идентификатор пользователя токена
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Возвращает токен
     * @return  - токен
     */
    public String getToken() {
        return token;
    }

    /**
     * Возвращает состояние токена
     * @return  - состояние
     */
    public Integer getState() {
        return state;
    }

    /**
     * Возвращает IP адрес токена
     * @return - IP адрес
     */
    public String getIp() {
        return ip;
    }

    /**
     * Возвращает HTTP User Agent
     * @return  - User Agent
     */
    public String getAgent() {
        return agent;
    }

    /**
     * Возвращает дату создания токена
     * @return  - дата создания токена
     */
    public String getDate() {
        return date;
    }
}
