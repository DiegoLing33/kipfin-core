package me.ling.kipfin.database.users;


import me.ling.kipfin.core.entities.users.User;
import me.ling.kipfin.core.EntityDB;
import me.ling.kipfin.core.sql.SQLObjectMapper;
import me.ling.kipfin.exceptions.users.UserNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.Map;

/**
 * База данных пользователей
 */
public class UsersDB extends EntityDB<User, UserNotFoundException> {

    /**
     * Элемент базы данных - синглтон
     */
    public static final UsersDB shared = new UsersDB();

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
    public Map<Integer, User> getAll() throws SQLException {
        return SQLObjectMapper.selectAllAndMap(User.class, UsersDB.TABLE_NAME, "user_id");
    }

    /**
     * Возвращает пользователя по логину
     *
     * @param login - логин пользователя
     * @return - пользователь
     */
    @NotNull
    public User getByLogin(String login) throws UserNotFoundException {
        var filtered = this.getCache().values().stream().filter(user -> user.getLogin().equals(login)).toArray(User[]::new);
        if (filtered.length == 0) throw new UserNotFoundException(login);
        return filtered[0];
    }
}
