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

package me.ling.kipfin.core.utils;

import me.ling.kipfin.core.entities.university.Teacher;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListUtilsTest {

    List<Teacher> teachers = List.of(
            new Teacher(1, 1, "Пестов"),
            new Teacher(2, 3, "Козлобаев")
    );

    List<String> strings = List.of("One", "Two", "Free");

    @Test
    void testContains() {
        assertTrue(ListUtils.contains(strings, "One"));
        assertFalse(ListUtils.contains(strings, "Four"));

        assertTrue(ListUtils.<String>contains(strings, s -> s.startsWith("O")));
        assertFalse(ListUtils.<String>contains(strings, s -> s.startsWith("X")));
    }

    @Test
    void testContainsWhere() {

        assertTrue(ListUtils.containsWhere(teachers, Teacher::getName, "Пестов"));
        assertFalse(ListUtils.containsWhere(teachers, Teacher::getGroupId, 4));
    }

    @Test
    void testContainsLike() {
        assertTrue(ListUtils.containsLike(strings, "wo"));
        assertTrue(ListUtils.containsLike(strings, "ee"));
        assertTrue(ListUtils.containsLike(strings, "one"));

        assertFalse(ListUtils.containsLike(strings, "!"));

        assertTrue(ListUtils.containsLike(teachers, Teacher::getName, "пес"));
        assertTrue(ListUtils.containsLike(teachers, Teacher::getName, "зло"));
    }
}