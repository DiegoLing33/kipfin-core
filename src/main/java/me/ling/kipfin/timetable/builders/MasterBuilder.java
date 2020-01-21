package me.ling.kipfin.timetable.builders;

import me.ling.kipfin.core.log.Logger;
import me.ling.kipfin.core.log.LoggerColors;
import me.ling.kipfin.core.log.WithLogger;
import me.ling.kipfin.core.utils.DateUtils;
import me.ling.kipfin.database.GroupsDB;
import me.ling.kipfin.database.TeachersDB;
import me.ling.kipfin.timetable.entities.*;
import me.ling.kipfin.timetable.workers.ClassroomsWorker;
import me.ling.kipfin.timetable.workers.WeekWorker;
import me.ling.timetable.entities.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Строитель мастер файла
 */
public class MasterBuilder extends WithLogger {

    /**
     * Дата начала обучения
     */
    public static LocalDate START_STUDY_DATE;

    private final LocalDate date;
    private final List<TimetableItem> timetable;
    private final List<ClassroomsItem> classrooms;

    public MasterBuilder(LocalDate date, List<TimetableItem> timetable, List<ClassroomsItem> classrooms) {
        super("Master Builder");
        this.date = date;
        this.timetable = timetable;
        this.classrooms = classrooms;
    }

    public List<TeacherWithSubjects> createTeachersTimetable() {
        if (TeachersDB.shared.getCache() != null) {
            return TeachersDB.shared.getCache().values().stream().map(teacher -> {
                List<ClassroomsItem> __items = new ClassroomsWorker(this.classrooms).getItemsByWho(teacher.getName());
                return new TeacherWithSubjects(teacher.getName(), __items.stream().map(value ->
                        new ClassroomsItem(value.getWhere(), value.getWho(), value.getGroup(), value.getIndex()))
                        .collect(Collectors.toList()));
            }).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public TimetableMaster build() {
        String dateString = DateUtils.toLocalDateString(this.date);
        int weekDayIndex = DateUtils.getLocalWeekDay(this.date);
        int weeksCount = DateUtils.getWeeksCount(START_STUDY_DATE, this.date);
        boolean even = weeksCount % 2 > 0;

        log(Logger.WAIT, "Начато построение мастер файла");
        log("Дата:", dateString);
        log("День недели:", DateUtils.weekDaysNames[weekDayIndex]);
        log("Неделя №", LoggerColors.getColoredString(LoggerColors.ANSI_CYAN, String.valueOf(weeksCount)));

        List<GroupWithSubjects> results = new ArrayList<>();
        var timetable = new WeekWorker(this.timetable).getItemsByDayIndex(weekDayIndex);
        var classrooms = new ClassroomsWorker(this.classrooms);

        if (GroupsDB.shared.getCache() != null) {
            GroupsDB.shared.getCache().values().forEach(group -> {
                var groupSubjects = new WeekWorker(timetable).getItemsByGroup(group);
                var groupClassrooms = classrooms.getItemsByGroup(group);

                List<GroupSubject> __subjects = new ArrayList<>();
                final int[] index = {-1};
                groupSubjects.get(0).getSubjects().forEach(subjectTitle -> {
                    index[0]++;
                    if (subjectTitle == null || subjectTitle.isEmpty()) return;
                    subjectTitle = subjectTitle.contains("/") ? subjectTitle.split("/")[even ? 0 : 1]
                            : subjectTitle;
                    var who = new HashMap<String, String>();
                    new ClassroomsWorker(groupClassrooms)
                            .getItemsByIndex(index[0]).forEach(c -> who.put(c.getWho(), c.getWhere()));
                    __subjects.add(new GroupSubject(subjectTitle, who, index[0]));
                });
                results.add(new GroupWithSubjects(group, __subjects));
                if (__subjects.size() == 0) log("У группы",
                        Logger.inQuad(LoggerColors.getColoredString(LoggerColors.ANSI_GREEN, group)), "нет пар!");
            });
        }
        return new TimetableMaster(weeksCount, weekDayIndex, dateString, results, this.createTeachersTimetable());
    }
}
