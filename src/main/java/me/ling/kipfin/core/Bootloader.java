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

package me.ling.kipfin.core;

import io.github.cdimascio.dotenv.Dotenv;
import me.ling.kipfin.core.log.Logger;
import me.ling.kipfin.core.log.WithLogger;
import me.ling.kipfin.core.managers.FTPManager;
import me.ling.kipfin.core.managers.SQLManager;
import me.ling.kipfin.database.GroupsDB;
import me.ling.kipfin.database.TeachersDB;

import java.sql.SQLException;
import java.util.Objects;

/**
 * Загрузчик
 */
public class Bootloader extends WithLogger {

    public Bootloader(Dotenv env) {
        super("Bootloader");

        /* Данные SQL */
        String sqlUrl = env.get("sql_url");
        String sqlLogin = env.get("sql_login");
        String sqlPassword = env.get("sql_password");

        /* Данные FTP */
        String ftpHost = env.get("ftp_host");
        Integer ftpPort = Integer.parseInt(Objects.requireNonNull(env.get("ftp_port")));
        String ftpLogin = env.get("ftp_login");
        String ftpPassword = env.get("ftp_password");
        String ftpRootDir = env.get("ftp_root");

        this.log("Установка настроек...");
        SQLManager.setUp(sqlUrl, sqlLogin, sqlPassword);
        FTPManager.setUp(ftpLogin, ftpPassword, ftpHost, ftpPort, ftpRootDir);
    }

    /**
     * Обновление баз данных
     *
     * @throws SQLException - исключения обновления данных
     */
    public void updateDatabase() throws SQLException {
        this.log(Logger.WAIT, "Подключение к MySQL...");
        TeachersDB.shared.update();
        GroupsDB.shared.update();
        this.log(true, "Подключение к MySQL...");
    }

}
