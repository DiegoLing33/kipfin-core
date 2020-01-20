package me.ling.database;

import me.ling.core.entities.Teacher;
import me.ling.core.managers.SQLManager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * База данных преподавателей
 */
public class TeachersDB extends EntityDB<Teacher> {

    public final static TeachersDB shared = new TeachersDB();

    /**
     * Возвращает всех преподавателей
     *
     * @return - карта преподавателей
     * @throws SQLException - исключение
     */
    public Map<Integer, Teacher> getAll() throws SQLException {
        HashMap<Integer, Teacher> teacherHashMap = new HashMap<>();
        SQLManager.selectAll("teachers", resultSet -> {
            teacherHashMap.put(resultSet.getInt("teacher_id"), new Teacher(
                    resultSet.getInt("teacher_id"),
                    resultSet.getInt("teacher_group_id"),
                    resultSet.getString("teacher_name")
            ));
        });
        return teacherHashMap;
    }
}
