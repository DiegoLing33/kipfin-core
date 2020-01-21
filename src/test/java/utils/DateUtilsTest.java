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

package utils;

import me.ling.kipfin.core.utils.DateUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;


public class DateUtilsTest {

    @Test
    public void toLocalDateString() {
        LocalDate localDateMay = LocalDate.of(2020, Month.MAY, 23);
        assertEquals("23.05.2020", DateUtils.toLocalDateString(localDateMay));
    }

    @Test
    public void fromLocalDateString() throws DateTimeParseException {
        LocalDate localDate = LocalDate.of(2020, Month.JANUARY, 1);
        LocalDate localDateMay = LocalDate.of(2020, Month.MAY, 23);
        assertEquals(localDate, DateUtils.fromLocalDateString("01.01.2020"));
        assertEquals(localDateMay, DateUtils.fromLocalDateString("23.05.2020"));
        assertThrows(DateTimeParseException.class, () -> DateUtils.fromLocalDateString("aaa"));
    }

    @Test
    public void isStringDateInLocalFormat() {
        assertTrue(DateUtils.isStringDateInLocalFormat("22.12.2019"));
        assertTrue(DateUtils.isStringDateInLocalFormat("01.05.2020"));
        assertFalse(DateUtils.isStringDateInLocalFormat("01.5.2020"));
        assertFalse(DateUtils.isStringDateInLocalFormat("01.aa5.2020"));
    }

    @Test
    public void getLocalWeekDay() {
        assertEquals(6, DateUtils.getLocalWeekDay(DateUtils.fromLocalDateString("19.01.2020")));
        assertEquals(0, DateUtils.getLocalWeekDay(DateUtils.fromLocalDateString("20.01.2020")));
        assertEquals(1, DateUtils.getLocalWeekDay(DateUtils.fromLocalDateString("21.01.2020")));
    }

    @Test
    public void getWeeksCount() {
        assertEquals(21, DateUtils.getWeeksCount(DateUtils.fromLocalDateString("01.09.2019"), DateUtils.fromLocalDateString("27.01.2020")));
        assertEquals(20, DateUtils.getWeeksCount(DateUtils.fromLocalDateString("01.09.2019"), DateUtils.fromLocalDateString("24.01.2020")));
        assertEquals(20, DateUtils.getWeeksCount(DateUtils.fromLocalDateString("01.09.2019"), DateUtils.fromLocalDateString("20.01.2020")));
        assertEquals(19, DateUtils.getWeeksCount(DateUtils.fromLocalDateString("01.09.2019"), DateUtils.fromLocalDateString("17.01.2020")));
    }
}