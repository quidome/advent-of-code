package me.quido.year2023.gaming

import me.quido.util.Solver
import me.quido.util.nonBlank

data class Card(
    val id: Int,
    var amount: Int,
    val winningNumbers: List<Int>,
    val cardNumbers: List<Int>,
) {
    fun matchingNumbers() = winningNumbers.intersect(cardNumbers.toSet())
    fun wins() = matchingNumbers().size
    fun increaseAmount() { amount++ }
}

class ScratchCards : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        val cardStack = input.nonBlank()
            .map {readCard(it)}

        val scratchCardPoints = cardStack
            .map { it.matchingNumbers() }
            .map { power(2, it.size - 1) }

        println(cardStack.mapIndexed{ index, it ->
            for (i in index + 1 ..  index + it.wins().coerceAtMost(cardStack.lastIndex)) {
                cardStack[i].increaseAmount()
            }
            it.amount}
        )

        return scratchCardPoints.sum() to 30
    }

    private fun readCard(card: String): Card {
        val cardRegex = """^Card\s(\d+):\s+(.*)\s+\|\s+(.*)$""".toRegex()
        val (cardId, winningNumbers, ourNumbers) = cardRegex.find(card)!!.destructured

        return Card(
            cardId.toInt(),
            1,
            splitStringToInts(winningNumbers),
            splitStringToInts(ourNumbers)
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