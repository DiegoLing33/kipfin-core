package me.ling.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

/**
 * Сущность преподавателя
 */
public class Teacher {

    @JsonProperty("teacher_id")
    private final Integer teacherId;

    @JsonProperty("teacher_group_id")
    private final Integer groupId;

    @JsonProperty("teacher_name")
    private final String name;

    public Teacher(Integer teacherId, Integer groupId, String name) {
        this.teacherId = teacherId;
        this.groupId = groupId;
        this.name = name;
    }

    /**
     * Возвращает ID преподавателя
     * @return ID преподавателя
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * Возвращает ID курируемой группы преподавателями
     * @return - ID группы
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * Возвращает имя преподавателя
     * @return - имя преподавателя
     */
    public String getName() {
        return name;
    }
}
