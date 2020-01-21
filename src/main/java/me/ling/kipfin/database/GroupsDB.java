package me.ling.kipfin.database;

import me.ling.kipfin.core.managers.SQLManager;
import org.jetbrains.annotations.Nullable;

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
        SQLManager.selectAll("unv_groups", result ->
                groups.put(result.getInt("group_id"), result.getString("group_title")));
        return groups;
    }

    /**
     * Выполняет поиск группы по ее части
     * @param g - часть группы
     * @return  - полное название группы
     */
    @Nullable
    public String easy(String g) {
        for (String group : this.getCache().values())
            if (group.toLowerCase().contains(g.toLowerCase())) return group;
        return null;
    }
}
