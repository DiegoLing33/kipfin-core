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

package me.ling;

import io.github.cdimascio.dotenv.Dotenv;
import me.ling.core.managers.FTPManager;
import me.ling.core.managers.TimetablePackageManager;
import me.ling.core.utils.JsonUtils;
import me.ling.timetable.app.TimetablePackager;
import me.ling.timetable.builders.MasterBuilder;
import me.ling.core.log.Logger;
import me.ling.core.managers.SQLManager;
import me.ling.core.utils.DateUtils;
import me.ling.database.GroupsDB;
import me.ling.database.TeachersDB;
import me.ling.timetable.TimetableWorkerApplication;
import me.ling.timetable.utils.TimetableUtils;

import java.util.Objects;


public class Main {

    /* Данные среды */
    private static Dotenv env = Dotenv.load();

    /* Данные SQL */
    private static String sqlUrl = env.get("sql_url");
    private static String sqlLogin = env.get("sql_login");
    private static String sqlPassword = env.get("sql_password");

    /* Данные FTP */
    private static String ftpHost = env.get("ftp_host");
    private static Integer ftpPort = Integer.parseInt(Objects.requireNonNull(env.get("ftp_port")));
    private static String ftpLogin = env.get("ftp_login");
    private static String ftpPassword = env.get("ftp_password");
    private static String ftpRootDir = env.get("ftp_root");

    public static void main(String[] args) throws Exception {
        Logger.logAs("Boost", "Запуск системы...");
        Dotenv env = Dotenv.load();

        Logger.logAs("Boost", "Установка настроек...");
        SQLManager.setUp(sqlUrl, sqlLogin, sqlPassword);
        FTPManager.setUp(ftpLogin, ftpPassword, ftpHost, ftpPort, ftpRootDir);

        MasterBuilder.START_STUDY_DATE = DateUtils.fromLocalDateString(env.get("START_STUDY_DATE"));

        Logger.logAs("Boost", Logger.WAIT, "Подключение к MySQL...");
        TeachersDB.shared.update();
        GroupsDB.shared.update();
        Logger.logAs("Boost", true, "Подключение к MySQL...");

//        TimetableWorkerApplication.createPackage(
//                "/Users/Diego/kipfin_bot/classrooms.xlsx",
//                "/Users/Diego/kipfin_bot/timetable.xls",
//                "."
//        );

    }
}
