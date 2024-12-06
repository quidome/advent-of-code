package me.quido.year2022.day2

import me.quido.util.getInputAsText

fun main() {
    val input = getInputAsText(2022, 2) ?: error("Input file does not exist")
    val solver = Day2(input)

    println(solver.part1())
    println(solver.part2())
}

class Day2(private val input: String) {
    fun part1(): Int {
        var myScore = 0
        val turns = turnsTranslatedToScores()

        for (turn in turns) {
            myScore += turn[1]
            myScore += pointsForDuel(turn)
        }
        return myScore
    }

    fun part2(): Int {
        var myScore = 0
        val turns = turnsTranslatedToScores()

        for (turn in turns) {
            val myResponse = selectResponse(turn)
            myScore += myResponse
            myScore += pointsForDuel(intArrayOf(turn[0], myResponse))
        }
        return myScore
    }

    private fun turnsTranslatedToScores(): List<IntArray> {
        val turns =
            input
                .split("\n")
                .map { it.split(" ") }
                .map { turnsListToInts(it) }
        return turns
    }

    private fun turnsListToInts(weapons: List<String>): IntArray {
        var returnValue = intArrayOf()

        val itr = weapons.listIterator()
        while (itr.hasNext()) {
            returnValue += turnToInt(itr.next())
        }
        return returnValue
    }

    private fun turnToInt(turn: String): Int =
        when (turn) {
            "A", "X" -> 1
            "B", "Y" -> 2
            "C", "Z" -> 3
            else -> 0
        }

    private fun selectResponse(turn: IntArray): Int {
        val theirTurn = turn[0]
        val requiredResponse = turn[1]

        when (requiredResponse) {
            1 -> return getWeakerItem(theirTurn)
            2 -> return theirTurn
            3 -> return getStrongerItem(theirTurn)
        }

        error("$requiredResponse is not a valid response")
    }

    private fun pointsForDuel(turns: IntArray): Int {
        if (turns[0] == turns[1]) {
            return 3
        } else if (weWin(turns[0], turns[1])) {
            return 6
        }

        return 0
    }

    private fun weWin(theirItem: Int, ourItem: Int): Boolean = ourItem == getStrongerItem(theirItem)

    private fun getStrongerItem(weapon: Int): Int = weapon % 3 + 1

    private fun getWeakerItem(weapon: Int): Int = (weapon - 2) % 3 + 1
}
