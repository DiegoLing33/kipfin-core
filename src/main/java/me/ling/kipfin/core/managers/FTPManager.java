package me.ling.kipfin.core.managers;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

/**
 * Менеджер FTP
 */
public class FTPManager {

    private static String ftpServer;
    private static Integer ftpPort;
    private static String ftpLogin;
    private static String ftpPassword;
    private static String ftpRootDir;

    /**
     * Настраивает FTP соединение
     *
     * @param ftpLogin    - лоигн
     * @param ftpPassword - пароль
     * @param ftpServer   - сервер
     * @param ftpPort     - порт
     * @param ftpRootDir  - стартовая директория
     */
    public static void setUp(String ftpLogin, String ftpPassword, String ftpServer, Integer ftpPort, String ftpRootDir) {
        FTPManager.ftpLogin = ftpLogin;
        FTPManager.ftpPassword = ftpPassword;
        FTPManager.ftpServer = ftpServer;
        FTPManager.ftpPort = ftpPort;
        FTPManager.ftpRootDir = ftpRootDir;
    }

    /**
     * Настраивает FTP соединение
     *
     * @param ftpLogin    - лоигн
     * @param ftpPassword - пароль
     * @param ftpServer   - сервер
     * @param ftpPort     - порт
     */
    public static void setUp(String ftpLogin, String ftpPassword, String ftpServer, Integer ftpPort) {
        FTPManager.setUp(ftpLogin, ftpPassword, ftpServer, ftpPort, null);
    }

    /**
     * Настраивает FTP соединение
     *
     * @param ftpLogin    - лоигн
     * @param ftpPassword - пароль
     * @param ftpServer   - сервер
     */
    public static void setUp(String ftpLogin, String ftpPassword, String ftpServer) {
        FTPManager.setUp(ftpLogin, ftpPassword, ftpServer, null, null);
    }

    /**
     * Возвращает подключение
     *
     * @param ftpLogin    - лоигн
     * @param ftpPassword - пароль
     * @param ftpServer   - сервер
     * @param ftpPort     - порт
     * @param ftpRootDir  - стартовая директория
     * @return - FTP клиент
     * @throws IOException - ошибки подключения
     */
    public static FTPClient getConnection(String ftpLogin, String ftpPassword, String ftpServer, Integer ftpPort, String ftpRootDir) throws IOException {
        var client = new FTPClient();
        client.connect(ftpServer, ftpPort);
        client.login(ftpLogin, ftpPassword);
        if (ftpRootDir != null) client.cwd(ftpRootDir);
        return client;
    }

    /**
     * Возвращает подключение к FTP серверу
     *
     * @return - объект FTPClient (Apache)
     * @throws IOException - оишбки подключения
     */
    public static FTPClient getConnection() throws IOException {
        return FTPManager.getConnection(
                FTPManager.ftpLogin,
                FTPManager.ftpPassword,
                FTPManager.ftpServer,
                FTPManager.ftpPort,
                FTPManager.ftpRootDir
        );
    }


    /**
     * Создает новый Ling FTP клиент
     * @param connection    - соединение
     * @return  - элемент клиента
     */
    public static me.ling.kipfin.core.FTPClient createClient(FTPClient connection){
        return new me.ling.kipfin.core.FTPClient(connection);
    }

    /**
     * Создает новый Ling FTP клиент
     * @return  - элемент клиента
     */
    public static me.ling.kipfin.core.FTPClient createClient() throws IOException {
        return new me.ling.kipfin.core.FTPClient(FTPManager.getConnection());
    }
}
