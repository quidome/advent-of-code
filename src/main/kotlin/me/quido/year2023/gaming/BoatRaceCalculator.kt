package me.quido.year2023.gaming

import me.quido.util.Solver
import me.quido.util.nonBlank
import me.quido.util.toLongList

data class Race(val duration: Long, val winningDistance: Long) {
    companion object {
        fun fromPair(raceData: Pair<Long, Long>): Race = Race(duration = raceData.first, winningDistance = raceData.second)
    }

    fun winningDurations(): List<Long> = (0..duration).map { distanceTraveled(it) }.filter { it > winningDistance }

    private fun distanceTraveled(time: Long) = time * (duration - time)
}

class BoatRaceCalculator : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        val raceData = input.nonBlank().map { it.substringAfter(':').toLongList() }

        val races = listsToListOfPairs(raceData.first(), raceData.last()).map { Race.fromPair(it) }
        val winningDurationsAmount = races.map { it.winningDurations().size }

        val correctedRaceData =
            input.nonBlank().map {
                it
                    .substringAfter(':')
                    .filter {
                        it.isDigit()
                    }.toLong()
            }
        val race = Race.fromPair(correctedRaceData.first() to correctedRaceData.last())

        return winningDurationsAmount.reduce(Int::times) to race.winningDurations().size
    }
}

private fun listsToListOfPairs(keys: List<Long>, values: List<Long>): List<Pair<Long, Long>> {
    if (keys.size != values.size) return emptyList()

    val returnValue: MutableList<Pair<Long, Long>> = mutableListOf()
    for (i in 0..keys.lastIndex) {
        returnValue += keys[i] to values[i]
    }
    return returnValue
}
