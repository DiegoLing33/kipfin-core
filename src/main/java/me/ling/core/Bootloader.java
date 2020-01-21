package me.ling.core;

import io.github.cdimascio.dotenv.Dotenv;
import me.ling.core.log.Logger;
import me.ling.core.log.WithLogger;
import me.ling.core.managers.FTPManager;
import me.ling.core.managers.SQLManager;
import me.ling.core.utils.DateUtils;
import me.ling.database.GroupsDB;
import me.ling.database.TeachersDB;
import me.ling.timetable.builders.MasterBuilder;

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
        MasterBuilder.START_STUDY_DATE = DateUtils.fromLocalDateString(env.get("START_STUDY_DATE"));
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
