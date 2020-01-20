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

package me.ling.core.entities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Элемент расписания
 */
public class TimetableItemEntity {

    @JsonProperty
    private final String group;

    @JsonProperty
    private final String[] subjects;

    @JsonProperty
    private final Integer dayOfWeekIndex;

    public TimetableItemEntity(String group, String[] subjects, Integer dayOfWeekIndex) {
        this.group = group;
        this.subjects = subjects;
        this.dayOfWeekIndex = dayOfWeekIndex;
    }

    /**
     * Возвращает группу
     * @return группа
     */
    public String getGroup() {
        return group;
    }

    /**
     * Возвращает массив предметов
     * @return массив предметов
     */
    public String[] getSubjects() {
        return subjects;
    }

    /**
     * Возвращает день недели
     * @return день недели (0...6)
     */
    public Integer getDayOfWeekIndex() {
        return dayOfWeekIndex;
    }
}
