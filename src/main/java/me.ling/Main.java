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
import me.ling.timetable.builders.MasterBuilder;
import me.ling.core.log.Logger;
import me.ling.core.managers.SQLManager;
import me.ling.core.utils.DateUtils;
import me.ling.database.GroupsDB;
import me.ling.database.TeachersDB;
import me.ling.timetable.TimetableWorkerApplication;


public class Main {


    public static void main(String[] args) throws Exception {
        Logger.logAs("Boost","Запуск системы...");
        Dotenv env = Dotenv.load();
        SQLManager.setUp(env.get("sql_url"), env.get("sql_login"), env.get("sql_password"));

        MasterBuilder.START_STUDY_DATE = DateUtils.fromLocalDateString(env.get("START_STUDY_DATE"));

        Logger.logAs("Boost",Logger.WAIT, "Подключение к MySQL...");
        TeachersDB.shared.update();
        GroupsDB.shared.update();
        Logger.logAs("Boost",true, "Подключение к MySQL...");

        TimetableWorkerApplication.createPackage(
                "/Users/Diego/kipfin_bot/classrooms.xlsx",
                "/Users/Diego/kipfin_bot/timetable.xls",
                "."
        );
    }
}
