package me.ling.kipfin.timetable.workers;

import me.ling.kipfin.timetable.entities.TimetableItem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Обработчик данных недели
 */
public class WeekWorker {

    /**
     * Неделя
     */
    protected final List<TimetableItem> week;

    /**
     * Обработчик недели
     *
     * @param source - исходные данные
     */
    public WeekWorker(List<TimetableItem> source) {
        this.week = source;
    }

    /**
     * Возвращает элементы по группе
     *
     * @param q - группа
     * @return - результат
     */
    public List<TimetableItem> getItemsByGroup(String q) {
        return this.week.stream().filter(v -> v.getGroup().toLowerCase()
                .contains(q.toLowerCase())).collect(Collectors.toList());
    }

    /**
     * Возвращает элементы по дню недели
     *
     * @param index - день недели
     * @return - результат
     */
    public List<TimetableItem> getItemsByDayIndex(int index) {
        return this.week.stream().filter(v -> v.getDayOfWeekIndex().equals(index)).collect(Collectors.toList());
    }

}
