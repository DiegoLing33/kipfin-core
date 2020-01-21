package me.ling.core.parsers;

import me.ling.core.log.Logger;
import me.ling.core.log.WithLogger;
import me.ling.core.utils.StringUtils;
import me.ling.timetable.exceptions.TimetableParserException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.Nullable;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Парсер EXCEL
 *
 * @param <T> - представление excel как объекта
 */
public abstract class ExcelParser<T> extends WithLogger {

    private final Workbook workbook;
    private final Sheet sheet;

    public ExcelParser(String path) throws IOException {
        super("Parser");
        log(Logger.WAIT, "Получение Excel файла...");
        if (path.endsWith(".xls")) {
            this.workbook = new HSSFWorkbook(new FileInputStream(path));
        } else {
            this.workbook = new XSSFWorkbook(new FileInputStream(path));
        }
        this.sheet = this.workbook.getSheetAt(0);
        log(Logger.YES, "Получение Excel файла...");
    }

    /**
     * Возвращает ячейку
     *
     * @param rowIndex - индекс строки
     * @param colIndex - индекс столбца
     * @return - ячейка
     */
    public Cell getCell(int rowIndex, int colIndex) {
        return this.sheet.getRow(rowIndex).getCell(colIndex);
    }

    /**
     * Выполняет работу парсера
     *
     * @return - результат
     */
    public abstract T start() throws TimetableParserException;

    /**
     * Возвращает строку
     *
     * @param rowIndex - индекс строки
     * @return - массив колонок
     */
    public Row getRow(int rowIndex) {
        return this.sheet.getRow(rowIndex);
    }

    /**
     * Возвращает кол-во строк
     *
     * @return количество строк в таблице
     */
    public int getRowsCount() {
        return this.sheet.getLastRowNum() + 1;
    }

    /**
     * Возвращает кол-во колонок на строке
     *
     * @param rowIndex - индекс строки
     * @return кол-во колонок
     */
    public int getColsCount(int rowIndex) {
        return this.sheet.getRow(rowIndex).getLastCellNum() + 1;
    }

    /**
     * Возвращает строковое значение или def
     *
     * @param rowIndex - индекс строки
     * @param colIndex - индекс колонки
     * @param def - значение по умолчанию
     * @return - значение или def
     */
    public String getStringValue(int rowIndex, int colIndex, String def) {
        Cell cell = this.getCell(rowIndex, colIndex);
        if (cell != null && cell.getCellType() == CellType.STRING)
            return StringUtils.removeAllSpaces(cell.getStringCellValue());
        return def;
    }

    /**
     * Возвращает строковое значение или null
     *
     * @param rowIndex - индекс строки
     * @param colIndex - индекс колонки
     * @return - значение или null
     */
    @Nullable
    public String getStringValue(int rowIndex, int colIndex){
        return this.getStringValue(rowIndex, colIndex, null);
    }
}
