package me.quido.year2022.day1

import me.quido.util.getInputAsText

fun main() {
    val input = getInputAsText(2022, 1) ?: error("Input file does not exist")
    val day1 = Day1(input)

    println(day1.getCaloriesCarriedByTheTopElves(1))
    println(day1.getCaloriesCarriedByTheTopElves(3))
}

class Day1(private val input: String) {
    fun getCaloriesCarriedByTheTopElves(amountOfTopElves: Int): Int =
        cleanedCalorieLog(input)
            .map { it.sum() }
            .sortedDescending()
            .take(amountOfTopElves)
            .sum()

    private fun cleanedCalorieLog(input: String): List<List<Int>> =
        input
            .split("\n\n")
            .map { elf ->
                elf
                    .lines()
                    .map { it.toInt() }
            }
}
