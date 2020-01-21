package me.ling.kipfin.timetable;

import me.ling.kipfin.timetable.app.TimetablePackager;
import me.ling.kipfin.timetable.entities.ClassroomsItem;
import me.ling.kipfin.timetable.entities.TimetableItem;
import me.ling.kipfin.timetable.entities.TimetableMaster;
import me.ling.kipfin.timetable.exceptions.TimetableParserException;
import me.ling.kipfin.timetable.parsers.ClassroomsParser;
import me.ling.kipfin.timetable.parsers.TimetableParser;
import me.ling.kipfin.timetable.builders.MasterBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Приложение для обработки дома
 */
public class TimetableWorkerApplication {

    /**
     * Создает объект недельного расписания
     *
     * @param fromFile - исходный файл
     * @return - объект расписания
     * @throws IOException              - исключение
     * @throws TimetableParserException - ошибка парсинга расписания
     */
    public static List<TimetableItem> createWeekObject(String fromFile) throws IOException, TimetableParserException {
        return new TimetableParser(fromFile).start();
    }

    /**
     * Создает объект расписания аудиторий
     *
     * @param fromFile - исходный файл
     * @return - объект расписания
     * @throws IOException              - исключение
     * @throws TimetableParserException - ошибка парсинга расписания
     */
    public static List<ClassroomsItem> createClassroomsObject(String fromFile) throws IOException, TimetableParserException {
        return new ClassroomsParser(fromFile).start();
    }

    /**
     * Создает объект мастер-сборки
     *
     * @param localDate  - дата сборки
     * @param timetable  - расписание
     * @param classrooms - аудитории
     * @return = мастер объект
     */
    public static TimetableMaster createMasterObject(LocalDate localDate, List<TimetableItem> timetable,
                                                     List<ClassroomsItem> classrooms) {
        return new MasterBuilder(localDate, timetable, classrooms).build();
    }

    /**
     * Создает парсер аудиторий
     *
     * @param path - путь до файла
     * @return - парсер аудиторий
     * @throws IOException - исключение файла
     */
    public static ClassroomsParser createClassroomsParser(String path) throws IOException {
        return new ClassroomsParser(path);
    }

    /**
     * Создает парсер расписания
     *
     * @param path - путь до файла
     * @return - парсер расписания
     * @throws IOException - исключение файла
     */
    public static TimetableParser createTimetableParser(String path) throws IOException {
        return new TimetableParser(path);
    }

    /**
     * Создает пакет из расписания
     *
     * @param classroomsPath - путь до аудиторий
     * @param weekPath       - путь до недельного расписания
     * @param outputPath     - выходной путь пакета
     * @return - мастер-расписание
     * @throws IOException              - исключение файлов
     * @throws TimetableParserException - исключение расписания
     */
    public static TimetablePackager createPackage(String classroomsPath, String weekPath, String outputPath) throws IOException, TimetableParserException {
        var classroomsParser = TimetableWorkerApplication.createClassroomsParser(classroomsPath);
        var timetableParser = TimetableWorkerApplication.createTimetableParser(weekPath);
        var classrooms = classroomsParser.start();
        var timetable = timetableParser.start();
        var master = TimetableWorkerApplication.createMasterObject(classroomsParser.getDate(), timetable, classrooms);
        var packager = new TimetablePackager(timetable, classrooms, master, outputPath);
        packager.createPackage();
        return packager;
    }
}
