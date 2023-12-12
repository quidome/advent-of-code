package me.quido.year2023.gaming

import me.quido.util.Solver
import me.quido.util.nonBlank

class ScratchCards : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        val numbers = input.nonBlank()
            .map { extractNumbers(it) }
            .map { (winning, ours) -> winning.intersect(ours.toSet())}

        return numbers.sumOf { power(2, it.size - 1) } to 0
    }

    private fun extractNumbers(card: String): List<List<Int>> {
        val numbersRegex = """^.*:(.*)\|(.*)$""".toRegex()
        val (winning, ours) = numbersRegex.find(card)!!.destructured

        return listOf(
            splitStringToInts(winning),
            splitStringToInts(ours)
        )
    }

    private fun splitStringToInts(winning: String) = winning.trim().split(' ')
        .filter { it.isNotEmpty() }
        .map { it.toInt() }

    private fun power(baseVal: Int, exponentVal: Int): Int {
        return if (exponentVal < 0 ) {
            0
        } else if (exponentVal != 0) {
            baseVal  * power(baseVal, exponentVal - 1)
        } else {
            1
        }
    }
}