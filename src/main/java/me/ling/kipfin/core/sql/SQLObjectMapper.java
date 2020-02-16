package me.ling.kipfin.core.sql;


import me.ling.kipfin.core.managers.SQLManager;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Привязывает SQL объекты к сущностям
 */
public class SQLObjectMapper {

    /**
     * Выбирает все данные из таблицы и выполняет привязку
     *
     * @param tClass    - класс сущности
     * @param table     - таблица сущностей
     * @param colWithId - имя колонки с ижентификаторами
     * @param <T>       - тип сущности
     * @return - связку Identifier->T
     * @throws SQLException - исключения при работе SQL
     */
    public static <T> Map<Integer, T> selectAllAndMap(Class<T> tClass, String table, String colWithId) throws SQLException {
        return SQLManager.INSTANCE.selectAllAndMap(table, colWithId, resultSet -> SQLObjectMapper.map(tClass, resultSet));
    }

    /**
     * Выполняет соединение ResultSet и JavaObject
     *
     * @param tClass    - класс сущности
     * @param resultSet - результат SQ:
     * @param <T>       - тип сущности
     * @return - сущность
     */
    public static <T> T map(Class<T> tClass, ResultSet resultSet) {
        try {
            List<Field> fields = Arrays.asList(tClass.getDeclaredFields());
            fields.forEach(field -> field.setAccessible(true));
            T dto = tClass.getConstructor().newInstance();

            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);
                if (column != null) {
                    var name = column.name();
                    var type = column.type();
                    Object value = null;
                    if (type.equals(String.class)) value = resultSet.getString(name);
                    if (type.equals(Integer.class)) value = resultSet.getInt(name);
                    field.set(dto, value);
                }
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
