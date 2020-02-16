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

package me.ling.kipfin.core.utils

/**
 * Удаляет все лишние пробелы
 */
fun String.removeAllSpaces(): String = this.replace(Regex("^\\s+|\\s+$"), "").replace(Regex("\\s+"), " ")

/**
 * Делает первую букву загловной
 */
fun String.title(): String = this.substring(0, 1).toUpperCase() + this.substring(1)

/**
 * Возвращает рандомную строку
 */
fun String.randomString(n: Int): String {
    val sb = StringBuilder(n)
    for (i in 0 until n) sb.append(this[(this.length * Math.random()).toInt()])
    return sb.toString()
}

/**
 * Возвращает случайную строку размера n
 */
fun String.Companion.getAlphaNumericString(n: Int): String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz".randomString(n)
