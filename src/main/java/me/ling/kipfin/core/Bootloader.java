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
import me.ling.kipfin.database.university.GroupsDB;
import me.ling.kipfin.database.university.TeachersDB;
import me.ling.kipfin.database.users.UserAuthTokensDB;
import me.ling.kipfin.database.users.UserDevTokensDB;
import me.ling.kipfin.database.users.UserGroupsDB;
import me.ling.kipfin.database.users.UsersDB;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;

/**
 * Загрузчик
 */
public class Bootloader extends WithLogger {

    private final Dotenv env;

    /**
     * Конструктор
     *
     * @param env - env данные
     */
    public Bootloader(Dotenv env) {
        super("Bootloader");
        this.env = env;

        /* Данные SQL */
        String sqlUrl = env.get("sql_url");
        String sqlLogin = env.get("sql_login");
        String sqlPassword = env.get("sql_password");

        /* Данные FTP */
        String ftpHost = env.get("ftp_host");
        Integer ftpPort = this.getEnvInteger("ftp_port", 21);
        String ftpLogin = env.get("ftp_login");
        String ftpPassword = env.get("ftp_password");
        String ftpRootDir = env.get("ftp_root");

        this.log("Установка настроек...");
        SQLManager.INSTANCE.init(sqlUrl, sqlLogin, sqlPassword);
        FTPManager.setUp(ftpLogin, ftpPassword, ftpHost, ftpPort, ftpRootDir);

        GroupsDB.TABLE_NAME = env.get("sql.tables.groups");
        TeachersDB.TABLE_NAME = env.get("sql.tables.teachers");

        UsersDB.TABLE_NAME = env.get("sql.tables.users");
        UserGroupsDB.TABLE_NAME = env.get("sql.tables.user_groups");
        UserAuthTokensDB.TABLE_NAME = env.get("sql.tables.user_auth_tokens");
        UserDevTokensDB.TABLE_NAME = env.get("sql.tables.user_dev_tokens");
    }

    /**
     * Конструктор
     */
    public Bootloader() {
        this(Dotenv.load());
    }

    /**
     * Возвращает данные среды
     *
     * @return - env
     */
    public Dotenv getEnv() {
        return env;
    }

    /**
     * Возвращает строку из env
     *
     * @param param - параметр
     * @param def   - значение по умолчанию
     * @return значение параметра
     */
    @Nullable
    public String getEnvString(String param, @Nullable String def) {
        try {
            return this.getEnv().get(param);
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * Возвращает строку из env
     *
     * @param param - параметр
     * @return значение параметра
     */
    @Nullable
    public String getEnvString(String param) {
        return this.getEnvString(param, null);
    }

    /**
     * Возвращает число из env
     *
     * @param param - параметр
     * @param def   - значение по умолчанию
     * @return значение параметра
     */
    @Nullable
    public Integer getEnvInteger(String param, @Nullable Integer def) {
        try {
            var val = this.getEnv().get(param);
            if (val == null) return def;
            return Integer.parseInt(val);
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * Возвращает число из env
     *
     * @param param - параметр
     * @return значение параметра
     */
    @Nullable
    public Integer getEnvInteger(String param) {
        return this.getEnvInteger(param, null);
    }

    /**
     * Возвращает булевый тип из env
     *
     * @param param - параметр
     * @param def   - значение по умолчанию
     * @return значение параметра
     */
    public boolean getEnvBoolean(String param, boolean def) {
        try {
            var val = this.getEnv().get(param);
            if (val == null) return def;
            val = val.toLowerCase();
            return val.equals("1") || val.equals("true") || val.equals("yes");
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * Возвращает булевый тип из env
     *
     * @param param - параметр
     * @return значение параметра
     */
    public boolean getEnvBoolean(String param) {
        return this.getEnvBoolean(param, false);
    }

    /**
     * Обновление баз данных
     *
     * @param extended - полная загрузка данных
     * @throws SQLException - исключения обновления данных
     */
    public void updateDatabase(Boolean extended) throws SQLException {
        this.log(Logger.WAIT, "Подключение к MySQL...");
        TeachersDB.shared.update();
        GroupsDB.shared.update();
        if (extended) {
            UsersDB.shared.update();
            UserGroupsDB.shared.update();
            UserAuthTokensDB.shared.update();
            UserDevTokensDB.shared.update();
        }
        this.log(true, "Подключение к MySQL...");
    }

    /**
     * Обновление баз данных
     *
     * @throws SQLException - исключения обновления данных
     */
    public void updateDatabase() throws SQLException {
        this.updateDatabase(false);
    }

}
