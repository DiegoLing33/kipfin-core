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

package me.ling.kipfin.core.managers

import me.ling.kipfin.core.log.Logger
import me.ling.kipfin.core.log.LoggerColors
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

/**
 * Менеджер SQL
 */
object SQLManager {

    private var url: String = ""
    private var user: String = ""
    private var password: String = ""

    /**
     * Инициилизирует менеджер
     */
    fun init(url: String, user: String, password: String) {
        this.url = url
        this.user = user
        this.password = password
    }

    /**
     * Возвращает соединение. Использует стандартное подключение в объекте
     */
    fun getConnection(): Connection? {
        return DriverManager.getConnection(this.url, this.user, this.password)
    }

    /**
     * Делает полную выборку из таблицы
     */
    fun selectAll(table: String, handler: (ResultSet) -> Unit) {
        val connection = this.getConnection() ?: return
        Logger.logAs("SQL", "Загрузка данных из таблицы", LoggerColors.getColoredString(LoggerColors.ANSI_CYAN, table))
        val resultSet = connection.createStatement().executeQuery("SELECT  * FROM $table")
        while (resultSet.next()) handler(resultSet)
    }

    /**
     * Делает полную выборку и приводит к типу
     */
    fun <T> selectAllAndMap(table: String, colId: String, callback: (ResultSet) -> T): Map<Int, T> {
        val map = mutableMapOf<Int, T>()
        this.selectAll(table) {
            map[it.getInt(colId)] = callback(it)
        }
        return map
    }
}