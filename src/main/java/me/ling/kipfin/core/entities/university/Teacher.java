/*
 *
 *   ██████╗░██╗███████╗░██████╗░░█████╗░  ██╗░░░░░██╗███╗░░██╗░██████╗░
 *   ██╔══██╗██║██╔════╝██╔════╝░██╔══██╗  ██║░░░░░██║████╗░██║██╔════╝░
 *   ██║░░██║██║█████╗░░██║░░██╗░██║░░██║  ██║░░░░░██║██╔██╗██║██║░░██╗░
 *   ██║░░██║██║██╔══╝░░██║░░╚██╗██║░░██║  ██║░░░░░██║██║╚████║██║░░╚██╗
 *   ██████╔╝██║███████╗╚██████╔╝╚█████╔╝  ███████╗██║██║░╚███║╚██████╔╝
 *   ╚═════╝░╚═╝╚══════╝░╚═════╝░░╚════╝░  ╚══════╝╚═╝╚═╝░░╚══╝░╚═════╝░
 *
 *   Это программное обеспечение имеет лицензию, как это сказано в файле
 *   COPYING, который Вы должны были получить в рамках распространения ПО.
 *
 *   Использование, изменение, копирование, распространение, обмен/продажа
 *   могут выполняться исключительно в согласии с условиями файла COPYING.
 *
 *   Mail: diegoling33@gmail.com
 *
 */

package me.ling.kipfin.core.entities.university;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.ling.kipfin.core.sql.Column;
import org.jetbrains.annotations.Nullable;

/**
 * Сущность преподавателя
 */
public class Teacher {

    @Column(name="teacher_id", type=Integer.class)
    @JsonProperty("teacher_id")
    private Integer teacherId;

    @Column(name="teacher_group_id", type=Integer.class)
    @JsonProperty("teacher_group_id")
    private Integer groupId;

    @Column(name="teacher_name")
    @JsonProperty("teacher_name")
    private String name;

    public Teacher(){}
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
