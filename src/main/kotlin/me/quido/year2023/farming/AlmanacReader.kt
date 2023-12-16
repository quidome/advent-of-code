package me.quido.year2023.farming

import me.quido.util.Solver
import me.quido.util.chunkInput

class AlmanacReader : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        val inputChunks = input.chunkInput()
        val seeds :List<Long> = inputChunks.first().first().substring(7).split(" ").map{it.toLong()}

        return seeds to 0
    }
}
