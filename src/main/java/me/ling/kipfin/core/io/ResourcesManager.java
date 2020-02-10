package me.ling.kipfin.core.io;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Менеджер ресурсов
 */
public class ResourcesManager {

    /**
     * Путь для выгрузки ресурсов
     */
    public static File PATH = new File("./common");


    /**
     * Возвращает файл реусрка
     * @param name  - имя файла
     * @return  - файл ресурса
     */
    @NotNull
    @Contract("_ -> new")
    public static File get(String name) {
        return new File(PATH + "/" + name);
    }

    /**
     * Распаковывает реусурс
     *
     * @param name - имя ресурса
     */
    public static void unpack(String name) throws IOException {
        if (!PATH.exists()) PATH.mkdir();
        var is = ResourcesManager.class.getResourceAsStream("/" + name);
        copyInputStreamToFile(is, new File(PATH.toString() + "/" + name));
    }

    /**
     * Возвращает true, если файл распакован
     * @param name  - файл
     * @return  - результат проверки
     */
    public static boolean isUnpacked(String name){
        return get(name).exists();
    }

    /**
     * Копирует поток в файл
     *
     * @param inputStream - поток
     * @param file        - файл
     * @throws IOException - исключение
     */
    public static void copyInputStreamToFile(@NotNull InputStream inputStream, File file) throws IOException {

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
    }

}
