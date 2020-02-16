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

package me.ling.kipfin.core.parsers

import me.ling.kipfin.core.log.WithLogger
import me.ling.kipfin.core.utils.removeAllSpaces
import org.apache.poi.ss.usermodel.*
import java.io.ByteArrayInputStream
import java.io.FileInputStream
import java.io.IOException

/**
 * Парсер excel файлов
 */
class XSSDocumentParser : WithLogger {

    private var workbook: Workbook
    private var sheet: Sheet

    /**
     * Чтение Excel файла по байтам
     */
    @Throws(IOException::class)
    constructor(bytes: ByteArray) : super("Parser") {
        wait("Чтение Excel файла по байтам...")
        workbook = WorkbookFactory.create(ByteArrayInputStream(bytes))
        sheet = workbook.getSheetAt(0)
        result(true)
    }

    /**
     * Чтение Excel из файла
     */
    @Throws(IOException::class)
    constructor(path: String) : super("Parser") {
        wait("Получение Excel файла...")
        workbook = WorkbookFactory.create(FileInputStream(path))
        sheet = workbook.getSheetAt(0)
        result(true)
    }

    /**
     * Возвращает ячейку
     */
    fun getCell(row: Int, col: Int): Cell? = this.sheet.getRow(row).getCell(col)

    /**
     * Возвращает строку
     */
    fun getRow(row: Int): Row? = this.sheet.getRow(row)

    /**
     * Возвращает кол-во строк
     */
    fun getRowsCount(): Int = this.sheet.lastRowNum + 1

    /**
     * Возвращает кол-во ячеек
     */
    fun getColsCount(row: Int): Int = (this.getRow(row)?.lastCellNum ?: -1) + 1

    /**
     * Возвращает строковое значение (даже если в таблице число)
     */
    fun getString(row: Int, col: Int): String? {
        val cell = this.getCell(row, col) ?: return null
        return when (cell.cellType) {
            CellType.STRING -> cell.stringCellValue
            CellType.NUMERIC -> {
                val v = cell.numericCellValue.toString()
                return if (v.endsWith(".0")) v.split(".").first() else v
            }
            else -> null
        }?.removeAllSpaces()
    }

    /**
     * Возвращает дробное число
     */
    fun getDouble(row: Int, col: Int): Double? {
        val cell = this.getCell(row, col) ?: return null
        return when (cell.cellType) {
            CellType.NUMERIC -> cell.numericCellValue
            else -> null
        }
    }

    /**
     * Возвращает число
     */
    fun getInt(row: Int, col: Int): Int? {
        val cell = this.getDouble(row, col) ?: return null
        return cell.toInt()
    }
}