package me.quido.year2023.calibrating

import me.quido.util.Solver
import me.quido.util.nonBlank

enum class Number {
    ZERO,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE
}

class Trebuchet : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        val regularInput = input.map{ it.filter { it.isDigit()}}
        val cleanedInput = cleanCalibrationInput(input.nonBlank())

        return calibration(regularInput) to calibration(cleanedInput)
    }

    private fun calibration(calibrationInput: List<String>): Int {
        var calibrationValuesTotal = 0

        val itr = calibrationInput.listIterator()
        while (itr.hasNext()) {
            val digits = itr.next()
            if (digits.isNotEmpty()) {
                val firstDigit = digits.first().digitToInt()
                val lastDigit = digits.last().digitToInt()
                calibrationValuesTotal += lastDigit + firstDigit * 10
            }
        }
        return calibrationValuesTotal
    }


    private fun cleanCalibrationInput(calibrationInput: List<String>): List<String> {
        return calibrationInput.map { replaceWords(it) }
    }

    private fun replaceWords(calibrationLine: String): String {
        val digits = HashMap<Int, Int>()

        for (numberAsWord in Number.values()) {
            val indexes = calibrationLine.indexesOf(numberAsWord.toString()).toMutableList()
            indexes += calibrationLine.indexesOf(numberAsWord.ordinal.toString())
            for (index in indexes) {
                digits.put(index, numberAsWord.ordinal)
            }
        }
        return digits.toSortedMap().values.joinToString()
    }

    private fun ignoreCaseOpt(ignoreCase: Boolean) =
        if (ignoreCase) setOf(RegexOption.IGNORE_CASE) else emptySet()

    private fun String?.indexesOf(pat: String, ignoreCase: Boolean = true): List<Int> =
        pat.toRegex(ignoreCaseOpt(ignoreCase))
            .findAll(this?: "")
            .map { it.range.first }
            .toList()
}
