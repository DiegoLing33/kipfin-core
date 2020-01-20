package me.ling.timetable.app;

import me.ling.timetable.entities.ClassroomsItem;
import me.ling.timetable.entities.TimetableItem;
import me.ling.timetable.entities.TimetableMaster;
import me.ling.core.log.Logger;
import me.ling.core.log.WithLogger;
import me.ling.core.utils.JsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Упаковщик расписания
 */
public class MasterTimetablePackager extends WithLogger {

    private final List<TimetableItem> week;
    private final List<ClassroomsItem> classrooms;
    private final TimetableMaster master;

    public MasterTimetablePackager(List<TimetableItem> week, List<ClassroomsItem> classrooms, TimetableMaster master) {
        super("Packager");
        this.week = week;
        this.classrooms = classrooms;
        this.master = master;
    }

    /**
     * Создает пакет с расписанием
     * @param path - путь до директории, в которой будет создан пакет
     * @throws IOException - ошибки при работе с файловой системой
     */
    public void createPackage(String path) throws IOException {
        String packPath = path + String.format("/%s.kftp", master.getDate());
        File pack = new File(packPath);
        log(Logger.WAIT, "Началась упаковка", packPath);
        if (pack.exists()) pack.delete();
        log("Создание архитектуры...");
        pack.mkdir();
        JsonUtils.saveJson(this.week, packPath + "/week.json");
        JsonUtils.saveJson(this.classrooms, packPath + "/classrooms.json");
        JsonUtils.saveJson(this.master, packPath + "/master.json");
        log(Logger.YES, "Готово!");
    }
}
