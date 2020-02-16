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

package me.ling.kipfin.database.university;

import me.ling.kipfin.core.EntityDB;
import me.ling.kipfin.core.entities.university.Teacher;
import me.ling.kipfin.core.sql.SQLObjectMapper;
import me.ling.kipfin.exceptions.university.TeacherNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.Map;

/**
 * База данных преподавателей
 */
public class TeachersDB extends EntityDB<Teacher, TeacherNotFoundException> {

    /**
     * Элемент базы данных - синглтон
     */
    public final static TeachersDB shared = new TeachersDB();

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
    public Map<Integer, Teacher> getAll() throws SQLException {
        return SQLObjectMapper.selectAllAndMap(Teacher.class, TeachersDB.TABLE_NAME, "teacher_id");
    }

    /**
     * Выполняет поиск преподавателя по части его имени
     *
     * @param t- часть имени преподавателя
     * @return - полное имя преподавателя
     */
    @NotNull
    public Teacher easy(String t) {
        for (Teacher teacher : this.getCache().values())
            if (teacher.getName().toLowerCase().contains(t.toLowerCase())) return teacher;
        throw new TeacherNotFoundException(t);
    }
}
