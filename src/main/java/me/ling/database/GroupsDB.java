package me.ling.database;

import me.ling.core.managers.SQLManager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * База данных - таблица группы
 */
public class GroupsDB extends EntityDB<String> {

    public final static GroupsDB shared = new GroupsDB();

    /**
     * Возвращает все группы из базы данных
     *
     * @return - карта групп
     * @throws SQLException - исключение
     */
    public Map<Integer, String> getAll() throws SQLException {
        HashMap<Integer, String> groups = new HashMap<>();
        SQLManager.selectAll("unv_groups", result -> {
            groups.put(result.getInt("group_id"), result.getString("group_title"));
        });
        return groups;
    }
}
