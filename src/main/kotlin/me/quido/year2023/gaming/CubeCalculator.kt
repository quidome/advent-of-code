package me.quido.year2023.gaming

import me.quido.util.Solver
import me.quido.util.nonBlank


class CubeCalculator : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        /*
        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        needs to be read.

        On the line there is a separation between Game id and the game info
        In the game info there are turns, separated by ;
        Within turns, there are key/value pairs, representing the amount of cubes per color

        We need to:
        - capture the game id
        - determine if the amount of cubes exceeds a threshold

        For the second part, I need to extract the minimum amount of cubes I need per color, to play the game.
        So basically finding the max value for each color, each game.
        Then I need to multiply these max values.

        I actually don't need real structure for these, just the highest number per color per line
        */
        val gamesList = input.nonBlank()

        return gamesList.sumOf { possibleGames(it) } to
                gamesList.sumOf { minimalRequiredCubes(it) }
    }

    private fun possibleGames(line: String): Int {
        val cubeThresholds = mapOf("red" to 12, "green" to 13, "blue" to 14)
        val (gameId, turns) = line.substring(5).split(":")

        val colorMax = maxPerColor(turns)

        for (color in colorMax.keys) {
            if (colorMax[color]!! > cubeThresholds[color]!!) {
                return 0
            }
        }
        return gameId.toInt()
    }

    private fun minimalRequiredCubes(line: String): Int =
        maxPerColor(line).values.reduce { acc, colorMax -> acc * colorMax }

    private fun maxPerColor(line:String): Map<String, Int> {
        val colorMax = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
        val cubesRegex = """(?<amount>\d+)\s(?<color>red|green|blue)""".toRegex()
        var cubesResult = cubesRegex.find(line)
        while (cubesResult != null) {
            val color = cubesResult.groups["color"]?.value.toString()
            val amount = cubesResult.groups["amount"]?.value?.toInt()

            if (amount != null && amount > colorMax[color]!!) {
                colorMax[color] = amount
            }
            cubesResult = cubesResult.next()
        }

        return colorMax
    }
}
