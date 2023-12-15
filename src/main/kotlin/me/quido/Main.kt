package me.quido

import me.quido.util.Solver
import me.quido.util.readFile
import me.quido.year2023.calibrating.Trebuchet
import me.quido.year2023.engineering.Gondola
import me.quido.year2023.farming.AlmanacReader
import me.quido.year2023.gaming.ColoredCubes
import me.quido.year2023.gaming.ScratchCards
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit.MILLIS

private val solvers2023: List<Solver> = listOf(
    Trebuchet(),
    ColoredCubes(),
    Gondola(),
    ScratchCards(),
    AlmanacReader(),
)


fun main() {
    2023.printSolutions(solvers2023)
}


private fun Int.printSolutions(solvers: List<Solver>) {
    println("Solutions for year $this\n")
    solvers.forEachIndexed { i, solver ->
        val day = i + 1
        val input = solver.readFile("day%02d.txt".format(day), this)
        val startTime = OffsetDateTime.now()
        val result = input?.let { solver.maybeSolve(it) }
        val duration = startTime.until(OffsetDateTime.now(), MILLIS)
        println("Day $day: $result in $duration milliseconds")
    }
    println("=======================\n")
}
