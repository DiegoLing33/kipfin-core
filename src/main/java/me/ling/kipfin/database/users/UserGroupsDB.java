package me.ling.kipfin.database.users;

import me.ling.kipfin.core.entities.users.User;
import me.ling.kipfin.core.entities.users.UserGroup;
import me.ling.kipfin.core.EntityDB;
import me.ling.kipfin.core.sql.SQLObjectMapper;
import me.ling.kipfin.exceptions.users.UserGroupNotFoundException;

import java.sql.SQLException;
import java.util.Map;

/**
 * База данных групп пользователей
 */
public class UserGroupsDB extends EntityDB<UserGroup, UserGroupNotFoundException> {

    /**
     * Элемент базы данных - синглтон
     */
    public static final UserGroupsDB shared = new UserGroupsDB();

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
    public Map<Integer, UserGroup> getAll() throws SQLException {
        return SQLObjectMapper.selectAllAndMap(UserGroup.class, UserGroupsDB.TABLE_NAME, "group_id");
    }

    /**
     * Возвращает группу пользователя
     *
     * @param user - пользовтель
     * @return - группа
     * @throws UserGroupNotFoundException - группа не найдена
     */
    public UserGroup getGroup(User user) throws UserGroupNotFoundException {
        for (UserGroup userGroup : this.getCache().values())
            if (userGroup.getGroupId().equals(user.getGroupId()))
                return userGroup;
        throw new UserGroupNotFoundException(user.getGroupId());
    }
}
