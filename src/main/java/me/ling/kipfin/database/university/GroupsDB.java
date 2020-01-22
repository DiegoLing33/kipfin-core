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
import me.ling.kipfin.core.entities.university.UniversityGroup;
import me.ling.kipfin.core.sql.SQLObjectMapper;
import me.ling.kipfin.exceptions.university.GroupNotFoundExceptionDatabase;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.Map;

/**
 * База данных - таблица группы
 */
public class GroupsDB extends EntityDB<UniversityGroup, GroupNotFoundExceptionDatabase> {

    /**
     * Элемент базы данных - синглтон
     */
    public final static GroupsDB shared = new GroupsDB();

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
    public Map<Integer, UniversityGroup> getAll() throws SQLException {
        return SQLObjectMapper.selectAllAndMap(UniversityGroup.class, GroupsDB.TABLE_NAME, "group_id");
    }

    /**
     * Выполняет поиск группы по ее части
     * @param g - часть группы
     * @return  - полное название группы
     */

    @Nullable
    public UniversityGroup easy(String g) {
        for (UniversityGroup group : this.getCache().values())
            if (group.getTitle().toLowerCase().contains(g.toLowerCase())) return group;
        throw new GroupNotFoundExceptionDatabase(g);
    }
}
