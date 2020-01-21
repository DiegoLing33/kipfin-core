package me.ling.timetable.parsers;

import me.ling.core.parsers.ExcelParser;
import me.ling.timetable.entities.ClassroomsItem;
import me.ling.core.log.Logger;
import me.ling.core.log.LoggerColors;
import me.ling.core.utils.DateUtils;
import me.ling.database.GroupsDB;
import me.ling.database.TeachersDB;
import me.ling.timetable.exceptions.TimetableParserException;
import me.ling.timetable.fixer.TimetableFixer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Парсер аудиторий
 */
public class ClassroomsParser extends ExcelParser<List<ClassroomsItem>> {

    private static final String START_INDEX_FLAG = "Преподаватели";

    public ClassroomsParser(String path) throws IOException {
        super(path);
    }

    /**
     * Возвращает дату аудиторий (из файла)
     *
     * @return - дата аудиторий из файла
     * @throws DateTimeParseException - исключение при ошибке поиска даты в файле
     */
    public LocalDate getDate() throws DateTimeParseException {
        String raw = this.getStringValue(0, 0, "");
        String fix = raw.replace("Аудитории на ", "");
        return DateUtils.fromLocalDateString(fix);
    }

    @Override
    public List<ClassroomsItem> start() throws TimetableParserException, DateTimeParseException {
        log(Logger.WAIT, "Парсинг аудиторий...");
        List<ClassroomsItem> list = new LinkedList<>();

        LocalDate localDate = this.getDate();
        String localDateString = DateUtils.toLocalDateString(localDate);
        log("Аудитории на дату", localDateString);

        int startIndexRow = 0;
        while (!this.getStringValue(startIndexRow, 0, "").toLowerCase().equals(START_INDEX_FLAG.toLowerCase()))
            startIndexRow++;
        startIndexRow++;

        for (int i = startIndexRow; i < this.getRowsCount(); i += 2) {
            String currentTeacher = this.getTeacherName(i, 0);
            if (currentTeacher == null) continue;
            log("Преподаватель:", LoggerColors.getColoredString(LoggerColors.ANSI_CYAN, currentTeacher));

            for (int j = 1; j < this.getColsCount(i); j++) {
                final int subIndex = j - 1;
                final String currentGroup = this.getGroupName(i, j);
                if (currentGroup == null) continue;

                final String currentClassroom = this.getClassroom(i + 1, j);
                list.add(new ClassroomsItem(currentClassroom, currentTeacher, currentGroup, subIndex));
            }
        }

        return list;
    }

    /**
     * Возвращает аудиторию
     *
     * @param rowIndex - индекс строки
     * @param colIndex - индекс колонки
     * @return - строка или null
     */
    public String getClassroom(int rowIndex, int colIndex) {
        Cell cell = this.getCell(rowIndex, colIndex);
        if (cell.getCellType() == CellType.STRING)
            return cell.getStringCellValue();
        else if (cell.getCellType() == CellType.NUMERIC)
            return String.valueOf((int) cell.getNumericCellValue());
        return "UNDEFINED";
    }

    /**
     * Возвращает имя преподавателя
     *
     * @param rowIndex - индекс строки
     * @param colIndex - индекс колонки
     * @return - строка или null
     */
    @Nullable
    public String getTeacherName(int rowIndex, int colIndex) throws TimetableParserException {
        String val = this.getStringValue(rowIndex, colIndex);
        if (val != null) {
            String fixed = TimetableFixer.fixTeacherName(val);
            if (!TeachersDB.shared.contains(t -> t.getName().equals(fixed))) // Testing group DB
                throw new TimetableParserException("Преподаватель " + fixed + " не найден в базе данных!");
            return fixed;
        }
        return null;
    }

    /**
     * Возвращает имя группы
     *
     * @param rowIndex - индекс строки
     * @param colIndex - индекс колонки
     * @return - строка или null
     */
    @Nullable
    public String getGroupName(int rowIndex, int colIndex) throws TimetableParserException {
        String val = this.getStringValue(rowIndex, colIndex);
        if (val != null) {
            String fixed = TimetableFixer.fixGroupName(val);
            log("Проверка группы", fixed);
            if (fixed.contains("конс")) return fixed;
            if (fixed.contains(" ")) {
                log("Найден пробел. Выполняется проверка",
                        LoggerColors.getColoredString(LoggerColors.ANSI_YELLOW, "по частям"), fixed);
                String[] groups = fixed.split(" ");
                if (!Arrays.stream(groups).allMatch(GroupsDB.shared::contains))
                    throw new TimetableParserException("Здесь что-то не так: " + fixed);
            } else {
                if (!GroupsDB.shared.contains(fixed)) // Testing group DB
                    throw new TimetableParserException("Группа " + fixed + " не найдена в базе данных!");
            }
            return fixed;
        }
        return null;
    }
}
