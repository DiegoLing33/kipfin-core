package me.ling.timetable.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ling.core.FTPClient;
import me.ling.core.managers.FTPManager;
import me.ling.timetable.entities.ClassroomsItem;
import me.ling.timetable.entities.TimetableItem;
import me.ling.timetable.entities.TimetableMaster;
import me.ling.core.log.Logger;
import me.ling.core.log.WithLogger;
import me.ling.core.utils.JsonUtils;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Упаковщик расписания
 */
public class TimetablePackager extends WithLogger {

    public static final String TIMETABLE_PACKAGE_SUFFIX = ".kftp";

    private final List<TimetableItem> week;
    private final List<ClassroomsItem> classrooms;
    private final TimetableMaster master;
    private final String workingPath;

    /**
     * Загружает мастер расписание
     * @param packagePath   - путь до пакета
     * @return              - масте-расписание
     */
    public static TimetableMaster loadMaster(String packagePath) throws IOException {
        String masterPath = String.format("%s/master.json", packagePath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(masterPath), TimetableMaster.class);
    }

    public TimetablePackager(List<TimetableItem> week, List<ClassroomsItem> classrooms, TimetableMaster master, String workingPath) {
        super("Packager");
        this.week = week;
        this.classrooms = classrooms;
        this.master = master;
        this.workingPath = workingPath;
    }

    /**
     * Создает пакет с расписанием
     * @throws IOException - ошибки при работе с файловой системой
     */
    public void createPackage() throws IOException {
        String packPath = this.workingPath + String.format("/%s.kftp", master.getDate());
        File pack = new File(packPath);
        log( "Началась упаковка", packPath);
        if (pack.exists()) {
            boolean delete = pack.delete();
        }
        this.wait("Создание архитектуры");
        Boolean __ = pack.mkdir();
        JsonUtils.saveJson(this.week, packPath + "/week.json");
        JsonUtils.saveJson(this.classrooms, packPath + "/classrooms.json");
        JsonUtils.saveJson(this.master, packPath + "/master.json");
        this.result(true);
    }

    /**
     * Загружает пакет
     * @throws IOException  - исключения
     */
    public void uploadPackage() throws IOException {
        String packPath = this.workingPath + master.getDate() + TIMETABLE_PACKAGE_SUFFIX;
        String packageName = master.getDate() + TIMETABLE_PACKAGE_SUFFIX;
        String ftpPath = String.format("./%s", packageName);

        log("Загрузка пакета", packageName, "на FTP сервер");
        FTPClient ftpClient = FTPManager.createClient();

        var directories = ftpClient.getDirectories();
        if(directories.stream().anyMatch(ftpFile -> ftpFile.equals(packageName))){
            ftpClient.removeDirectory(ftpPath);
        }

        ftpClient.createDirectory(ftpPath);
        ftpClient.upload(packPath + "/week.json", ftpPath + "/week.json");
        ftpClient.upload(packPath + "/classrooms.json", ftpPath + "/classrooms.json");
        ftpClient.upload(packPath + "/master.json", ftpPath + "/master.json");
        ftpClient.getRawClient().disconnect();
        log(Logger.YES, "Готово!");
    }

    public static boolean downloadPackage(String date, String outputPath) throws IOException {
        String packName = date + TIMETABLE_PACKAGE_SUFFIX;
        File outputDirectory = new File(outputPath + "/" + packName);
        FTPClient client = FTPManager.createClient();
        if(!client.exists(packName)) return false;
        if(outputDirectory.exists()) {
            boolean delete = outputDirectory.delete();
        }
        boolean mkdir = outputDirectory.mkdir();
        client.download(packName + "/week.json", outputDirectory + "/week.json");
        client.download(packName + "/classrooms.json", outputDirectory + "/classrooms.json");
        client.download(packName + "/master.json", outputDirectory + "/master.json");
        return true;
    }
}
