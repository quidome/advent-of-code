package me.quido.year2023.farming

import me.quido.util.Solver
import me.quido.util.chunkInput

class AlmanacReader : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        val chunkedInput = input.chunkInput()
        val seeds :List<Long> = chunkedInput.first().first().substring(7).split(" ").map{it.toLong()}

        val blah = chunkedInput.filter { it.size > 1}
            .map { chunk -> // each chunk
                chunk.slice( 1 .. chunk.lastIndex).map {
                    it.split(" ").map { it.toLong()}
                }
            }

        println("seeds: ${seeds}\nblah: $blah")

        return seeds to 0
    }
}
