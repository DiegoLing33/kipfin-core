package me.ling.kipfin.database.users;

import me.ling.kipfin.core.EntityDB;
import me.ling.kipfin.core.entities.users.User;
import me.ling.kipfin.core.entities.users.UserDevToken;
import me.ling.kipfin.core.sql.SQLObjectMapper;
import me.ling.kipfin.exceptions.users.UserDevTokenNotFoundException;
import me.ling.kipfin.exceptions.users.UserNotFoundException;

import java.sql.SQLException;
import java.util.Map;

/**
 * База данных токенов разработчика
 */
public class UserDevTokensDB extends EntityDB<UserDevToken, UserDevTokenNotFoundException> {

    /**
     * Элемент базы данных - синглтон
     */
    public static final UserDevTokensDB shared = new UserDevTokensDB();

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
    public Map<Integer, UserDevToken> getAll() throws SQLException {
        return SQLObjectMapper.selectAllAndMap(UserDevToken.class, UserDevTokensDB.TABLE_NAME, "dev_token_id");
    }

    /**
     * Возвращает пользователя ключа
     *
     * @param token - ключ
     * @return - пользователь
     * @throws UserNotFoundException - пользователь не найден
     */
    public User getUser(UserDevToken token) throws UserNotFoundException {
        return UsersDB.shared.getById(token.getUserId());
    }
}
