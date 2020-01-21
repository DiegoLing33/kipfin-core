/*
 *
 *   ██████╗░██╗███████╗░██████╗░░█████╗░  ██╗░░░░░██╗███╗░░██╗░██████╗░
 *   ██╔══██╗██║██╔════╝██╔════╝░██╔══██╗  ██║░░░░░██║████╗░██║██╔════╝░
 *   ██║░░██║██║█████╗░░██║░░██╗░██║░░██║  ██║░░░░░██║██╔██╗██║██║░░██╗░
 *   ██║░░██║██║██╔══╝░░██║░░╚██╗██║░░██║  ██║░░░░░██║██║╚████║██║░░╚██╗
 *   ██████╔╝██║███████╗╚██████╔╝╚█████╔╝  ███████╗██║██║░╚███║╚██████╔╝
 *   ╚═════╝░╚═╝╚══════╝░╚═════╝░░╚════╝░  ╚══════╝╚═╝╚═╝░░╚══╝░╚═════╝░
 *
 *   Это программное обеспечение имеет лицензию, как это сказано в файле
 *   COPYING, который Вы должны были получить в рамках распространения ПО.
 *
 *   Использование, изменение, копирование, распространение, обмен/продажа
 *   могут выполняться исключительно в согласии с условиями файла COPYING.
 *
 *   Mail: diegoling33@gmail.com
 *
 */

package me.ling.kipfin.database;

import me.ling.kipfin.core.entities.Teacher;
import me.ling.kipfin.core.managers.SQLManager;
import org.jetbrains.annotations.Nullable;

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
        SQLManager.selectAll("teachers", resultSet ->
                teacherHashMap.put(resultSet.getInt("teacher_id"), new Teacher(
                resultSet.getInt("teacher_id"),
                resultSet.getInt("teacher_group_id"),
                resultSet.getString("teacher_name")
        )));
        return teacherHashMap;
    }

    /**
     * Выполняет поиск преподавателя по части его имени
     *
     * @param t- часть имени преподавателя
     * @return - полное имя преподавателя
     */
    @Nullable
    public Teacher easy(String t) {
        for (Teacher teacher : this.getCache().values())
            if (teacher.getName().toLowerCase().contains(t.toLowerCase())) return teacher;
        return null;
    }
}
