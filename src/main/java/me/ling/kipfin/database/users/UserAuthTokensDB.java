package me.ling.kipfin.database.users;

import me.ling.kipfin.core.EntityDB;
import me.ling.kipfin.core.entities.users.User;
import me.ling.kipfin.core.entities.users.UserAuthToken;
import me.ling.kipfin.core.sql.SQLObjectMapper;
import me.ling.kipfin.exceptions.users.UserAuthTokenNotFoundException;
import me.ling.kipfin.exceptions.users.UserNotFoundException;

import java.sql.SQLException;
import java.util.Map;

/**
 * База данных токенов авторизации
 */
public class UserAuthTokensDB extends EntityDB<UserAuthToken, UserAuthTokenNotFoundException> {

    /**
     * Элемент базы данных - синглтон
     */
    public static final UserAuthTokensDB shared = new UserAuthTokensDB();

    /**
     * Имя таблицы данных
     */
    public static String TABLE_NAME = "";

    /**
     * Загружает и возвращает все записи из таблицы. Используйте этот метод с осторжностью.
     * Для получения данных стоит при запуске приложения выполнить метод `EntityDB::update`,
     * и далее использовать метод `EntityDB::getCache`.
     *
     * @return - карта записей Identifier->Entity
     * @throws SQLException - исключения, которые могут возникнуть при работе с базой данных
     */
    @Override
    public Map<Integer, UserAuthToken> getAll() throws SQLException {
        return SQLObjectMapper.selectAllAndMap(UserAuthToken.class, UserAuthTokensDB.TABLE_NAME, "token_id");
    }

    /**
     * Возвращает пользователя ключа
     *
     * @param token - ключ
     * @return - пользователь
     * @throws UserNotFoundException - пользователь не найден
     */
    public User getUser(UserAuthToken token) throws UserNotFoundException {
        return UsersDB.shared.getById(token.getUserId());
    }
}
