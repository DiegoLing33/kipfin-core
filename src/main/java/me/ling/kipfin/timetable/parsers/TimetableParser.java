package me.ling.kipfin.timetable.parsers;

import me.ling.kipfin.core.parsers.ExcelParser;
import me.ling.kipfin.timetable.entities.TimetableItem;
import me.ling.kipfin.core.log.Logger;
import me.ling.kipfin.core.log.LoggerColors;
import me.ling.kipfin.core.utils.DateUtils;
import me.ling.kipfin.database.GroupsDB;
import me.ling.kipfin.timetable.exceptions.TimetableParserException;
import me.ling.kipfin.timetable.fixer.TimetableFixer;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Парсер расписания
 */
public class TimetableParser extends ExcelParser<List<TimetableItem>> {

    public TimetableParser(String path) throws IOException {
        super(path);
    }

    @Override
    public List<TimetableItem> start() throws TimetableParserException {
        log(Logger.WAIT, "Парсинг расписания...");
        List<TimetableItem> list = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            String dayOfWeekName = DateUtils.weekDaysNames[i];
            int dayOfWeekRowIndex = 0;
            log(Logger.WAIT, "Поиск дня недели:", dayOfWeekName);

            for (int j = 0; j < this.getRowsCount(); j++) {
                String cell = this.getStringValue(j, 0);
                if (cell != null && cell.equals(dayOfWeekName)) {
                    dayOfWeekRowIndex = j;
                    log(Logger.YES, "Поиск дня недели:", dayOfWeekName, i);
                    break;
                }
            }

            int colsCount = this.getColsCount(dayOfWeekRowIndex);

            for (int colIndex = 1; colIndex < colsCount; colIndex++) {
                String groupName = this.getGroupName(dayOfWeekRowIndex, colIndex);
                if (groupName == null) continue;

                String[] subjects = new String[6];
                for (int n = 1; n < 6; n++) {
                    String subjectName = this.getSubjectName(dayOfWeekRowIndex + n, colIndex);
                    subjects[n - 1] = (subjectName == null || subjectName.isEmpty()) ? null : subjectName;
                }

                list.add(new TimetableItem(groupName, Arrays.asList(subjects), i));
            }
        }

        log(Logger.YES, "Парсинг расписания...");
        return list;
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
            if (fixed.contains(" ")) {
                log("Найден пробел. Выполняет проверка",
                        LoggerColors.getColoredString(LoggerColors.ANSI_YELLOW, "по частям"));
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

    /**
     * Возвращает название предмета
     *
     * @param rowIndex - индекс строки
     * @param colIndex - индекс колонки
     * @return - название предмета или null
     */
    @Nullable
    public String getSubjectName(int rowIndex, int colIndex) {
        String val = this.getStringValue(rowIndex, colIndex);
        return val != null ? TimetableFixer.fixSubjectName(val) : null;
    }
}
