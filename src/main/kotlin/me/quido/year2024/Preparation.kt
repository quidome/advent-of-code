package me.quido.year2024

import me.quido.util.Solver

class Preparation : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> = firstLine(input) to lastLine(input)

    private fun firstLine(input: List<String>) = input.first()

    private fun lastLine(input: List<String>) = input.last()
}
