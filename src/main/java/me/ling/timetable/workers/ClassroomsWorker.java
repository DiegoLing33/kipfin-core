package me.ling.timetable.workers;

import me.ling.timetable.entities.ClassroomsItem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Обработчик аудиторий
 */
public class ClassroomsWorker {

    private final List<ClassroomsItem> classrooms;

    /**
     * Конструктор
     *
     * @param classrooms - аудитории
     */
    public ClassroomsWorker(List<ClassroomsItem> classrooms) {
        this.classrooms = classrooms;
    }

    /**
     * Возвращает элементы по группе
     *
     * @param q - группа
     * @return - результат
     */
    public List<ClassroomsItem> getItemsByGroup(String q) {
        return this.classrooms.stream().filter(v -> v.getGroup().toLowerCase().contains(q.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Возвращает элементы по имени преподавателя
     *
     * @param q - имя преподавателя
     * @return - результат
     */
    public List<ClassroomsItem> getItemsByWho(String q) {
        return this.classrooms.stream().filter(v -> v.getWho().toLowerCase().contains(q.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Возвращает элементы по месту проведения
     *
     * @param q - место проведения
     * @return - результат
     */
    public List<ClassroomsItem> getItemsByWhere(String q) {
        return this.classrooms.stream().filter(v -> v.getWhere().toLowerCase().contains(q.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Возвращает элементы по месту индексу
     *
     * @param q - индекс
     * @return - результат
     */
    public List<ClassroomsItem> getItemsByIndex(int q) {
        return this.classrooms.stream().filter(v -> v.getIndex().equals(q)).collect(Collectors.toList());
    }
}
