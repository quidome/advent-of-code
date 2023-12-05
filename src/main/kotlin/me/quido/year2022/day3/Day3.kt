package me.quido.year2022.day3

import me.quido.util.getInputAsText

fun main() {
    val input = getInputAsText(2022, 3) ?: error("Input file does not exist")
    val solver = Day3(input)

    println("part 1: ${solver.part1()}")
    println("part 2: ${solver.part2()}")
}


class Day3(private val input: String) {

    fun part1(): Int = input.lines()
        .map { line -> Rucksack(line) }
        .sumOf { rucksack -> rucksack.fixPriority() }


    fun part2(): Int {
        return 2
    }
}