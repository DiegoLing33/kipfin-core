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

package me.ling.kipfin.core.managers;

import me.ling.kipfin.core.callbacks.CallbackWithException;
import me.ling.kipfin.core.callbacks.RCallbackWithException;
import me.ling.kipfin.core.log.Logger;
import me.ling.kipfin.core.log.LoggerColors;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * Менеджер SQL
 */
public class SQLManager {

    private static String connectionUrl = null;
    private static String connectionUser = null;
    private static String connectionPassword = null;

    /**
     * Настраивает менеджер
     *
     * @param url      - url подключения
     * @param user     - пользователь
     * @param password - пароль
     */
    public static void setUp(String url, String user, String password) {
        SQLManager.connectionUrl = url;
        SQLManager.connectionUser = user;
        SQLManager.connectionPassword = password;
    }

    /**
     * Возвращает соединение
     *
     * @param url      - строка url
     * @param user     - пользователь
     * @param password - пароль
     * @return - подключение
     * @throws SQLException - ошибка подключения
     */
    public static Connection getConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Возвращает соединение с базой данных
     *
     * @return - объект соединениея
     * @throws SQLException - исключение
     */
    public static Connection getConnection() throws SQLException {
        return SQLManager.getConnection(SQLManager.connectionUrl, SQLManager.connectionUser, SQLManager.connectionPassword);
    }

    /**
     * Выполняет запрос SELECT * в указанную таблицу
     *
     * @param tableName - имя табилцы
     * @param closure   - обработчик
     * @throws SQLException - исключение запроса
     */
    public static void selectAll(String tableName, CallbackWithException<ResultSet, SQLException> closure) throws SQLException {
        try (Connection connection = SQLManager.getConnection()) {
            Logger.logAs("SQL", "Загрузка данных из таблицы", LoggerColors.getColoredString(LoggerColors.ANSI_CYAN, tableName));
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from " + tableName);
            while (resultSet.next()) {
                closure.apply(resultSet);
            }
        }
    }

    /**
     * Выполняет запрос SELECT * в указанную таблицу и создает карту
     *
     * @param tableName - имя табилцы
     * @param colWithId - имя столбца с идентификатором
     * @param callback  - обработчик
     * @throws SQLException - исключение запроса
     */
    public static <T> HashMap<Integer, T>
    selectAllAndMap(String tableName, String colWithId, RCallbackWithException<ResultSet, T,
            SQLException> callback) throws SQLException {
        HashMap<Integer, T> hashMap = new HashMap<>();
        SQLManager.selectAll(tableName, resultSet -> hashMap.put(resultSet.getInt(colWithId), callback.apply(resultSet)));
        return hashMap;
    }
}
