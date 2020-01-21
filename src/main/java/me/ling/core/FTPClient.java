package me.ling.core;

import me.ling.core.log.WithLogger;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Клиент ФТП
 */
public final class FTPClient extends WithLogger {
    /**
     * Клиент
     */
    private final org.apache.commons.net.ftp.FTPClient client;

    public FTPClient(org.apache.commons.net.ftp.FTPClient client) {
        super("FTP");
        this.client = client;
    }

    /**
     * Возвращает исходный Apache клиент
     *
     * @return - объект клиента
     */
    public org.apache.commons.net.ftp.FTPClient getRawClient() {
        return this.client;
    }

    /**
     * Загрузка файла на сервер
     *
     * @param file       - локальный файл
     * @param serverPath - полное имя файла на сервере
     * @return - результат загрузки
     */
    public boolean upload(String file, String serverPath) throws IOException {
        this.wait("Загрузка файла", file, "->", serverPath);
        return this.result(this.client.storeFile(serverPath, new FileInputStream(file)));
    }

    /**
     * Загрузка файла
     *
     * @param serverPath - путь до файла на сервере
     * @param localPath  - путь локальный
     * @return - результат
     * @throws IOException - исключения при выполнении метода
     */
    public boolean download(String serverPath, String localPath) throws IOException {
        this.wait("Получения файла", serverPath, "->", localPath);
        FileOutputStream fos = new FileOutputStream(localPath);
        return this.result(client.retrieveFile(serverPath, fos));
    }

    /**
     * Возвращает список директорий в каталоге
     *
     * @return - список директорий в каталоге
     */
    public List<String> getDirectories() throws IOException {
        this.wait("Получение списка директорий");
        this.result(true);
        return Arrays.stream(this.getRawClient().listDirectories()).map(FTPFile::getName)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список файлов в каталоге
     *
     * @return - список файлов в каталоге
     */
    public List<String> getFiles() throws IOException {
        this.wait("Получение списка файлов");
        this.result(true);
        return Arrays.stream(this.getRawClient().listFiles()).map(FTPFile::getName)
                .collect(Collectors.toList());
    }

    /**
     * Удаляет директорию
     *
     * @param path - путь до директории
     * @return - результат выполнения метода
     */
    public Boolean removeDirectory(String path) throws IOException {
        this.wait("Удаление директории", path);
        this.getRawClient().rmd(path);
        return this.result(true);
    }

    /**
     * Удаляет файл
     *
     * @param path - путь до файла
     * @return - результат выполнения метода
     */
    public Boolean removeFile(String path) throws IOException {
        this.wait("Удаление файла", path);
        return this.result(this.getRawClient().deleteFile(path));
    }

    /**
     * Создает директорию
     *
     * @param path - путь до директории
     * @return - результат выполнения метода
     */
    public Boolean createDirectory(String path) throws IOException {
        this.wait("Создание директории", path);
        this.getRawClient().mkd(path);
        return this.result(true);
    }

    /**
     * Проверяет данные
     * @param name  - имя
     * @return      - результат выполнения метода
     */
    public Boolean exists(String name) throws IOException {
        this.wait("Проверка вхождения", name);
        return this.result(Arrays.asList(this.getRawClient().listNames()).contains(name));
    }
}
