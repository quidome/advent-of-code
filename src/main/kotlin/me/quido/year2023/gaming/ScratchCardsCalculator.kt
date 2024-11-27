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

    fun increaseAmount(increase: Int = 1) {
        amount += increase
    }
}

class ScratchCardsCalculator : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        val cardStack =
            input
                .nonBlank()
                .map { readCard(it) }

        val scratchCardPoints =
            cardStack
                .map { it.matchingNumbers() }
                .map { power(2, it.size - 1) }
                .sum()

        val finalCardStackSize =
            cardStack
                .mapIndexed { index, card ->
                    val cardRange = index + 1..(index + card.wins()).coerceAtMost(cardStack.lastIndex)
                    for (i in cardRange) {
                        cardStack[i].increaseAmount(card.amount)
                    }
                    card.amount
                }.sum()

        return scratchCardPoints to finalCardStackSize
    }

    private fun readCard(card: String): Card {
        val cardRegex = """^Card\s+(\d+):(.*)\|(.*)$""".toRegex()
        val (cardId, winningNumbers, ourNumbers) = cardRegex.find(card)!!.destructured

        return Card(
            cardId.toInt(),
            1,
            splitStringToInts(winningNumbers),
            splitStringToInts(ourNumbers),
        )
    }

    private fun splitStringToInts(winning: String) =
        winning
            .trim()
            .split(' ')
            .filter { it.isNotEmpty() }
            .map { it.toInt() }

    private fun power(
        baseVal: Int,
        exponentVal: Int,
    ): Int =
        if (exponentVal < 0) {
            0
        } else if (exponentVal != 0) {
            baseVal * power(baseVal, exponentVal - 1)
        } else {
            1
        }
}
