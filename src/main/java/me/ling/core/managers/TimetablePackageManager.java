package me.ling.core.managers;

import me.ling.timetable.app.TimetablePackager;
import me.ling.timetable.entities.TimetableMaster;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static me.ling.timetable.app.TimetablePackager.TIMETABLE_PACKAGE_SUFFIX;

/**
 * Менеджер расписания
 */
public class TimetablePackageManager {

    /**
     * Общий менеджер пакетов
     */
    public static final TimetablePackageManager shared = new TimetablePackageManager();

    private String workingPath = ".";
    private final HashMap<String, TimetableMaster> cache = new HashMap<>();

    /**
     * Устанавливает рабочую директорию
     *
     * @param workingPath - рабочая директория
     */
    public void setWorkingPath(String workingPath) {
        this.workingPath = workingPath;
    }

    /**
     * Возвращает рабочую директорию
     *
     * @return - рабочая директория
     */
    public String getWorkingPath() {
        return workingPath;
    }

    /**
     * Возвращает локальный путь до пакета
     *
     * @param date - дата
     * @return - локальный путь
     */
    public String getPackagePath(String date) {
        return this.getWorkingPath() + "/" + date + TIMETABLE_PACKAGE_SUFFIX;
    }

    /**
     * Скачивает пакет с сервера
     *
     * @param date - дата
     * @return - пакет
     * @throws IOException - исключения
     */
    public boolean downloadPackage(String date) throws IOException {
        return TimetablePackager.downloadPackage(date, this.workingPath);
    }

    /**
     * Возвращает true, если пакет загружен
     *
     * @param date - дата пакета
     * @return - результат проверки
     */
    public boolean isPackageDownloaded(String date) {
        return new File(this.getPackagePath(date)).exists();
    }

    /**
     * Возвращает мастер данные из пакета
     *
     * @param date - дата пакета
     * @return - мастер-расписание
     * @throws IOException - исключения
     */
    @Nullable
    public TimetableMaster extractMasterFromPackage(String date) throws IOException {
        if (this.cache.containsKey(date)) return this.cache.get(date);
        if (this.isPackageDownloaded(date)) {
            var master = TimetablePackager.loadMaster(getPackagePath(date));
            this.cache.put(date, master);
            return master;
        }
        return null;
    }
}
