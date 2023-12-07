package me.quido.year2023.engineering

import me.quido.util.Solver

class Gondola : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        /*
        Part 1:
        For each row of digits we need to check if it touches a symbol, even diagonally.
        We should return the sum of digits that don't touch any symbols.
        */
        return 4361 to 0
    }
}
