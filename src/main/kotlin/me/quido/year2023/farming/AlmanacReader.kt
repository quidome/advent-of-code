package me.quido.year2023.farming

import me.quido.util.Solver
import me.quido.util.chunkInput
import me.quido.util.toLongList
import kotlin.math.max
import kotlin.math.min

data class MappingGroup(
    val mappings: List<Mapping>
) {
    companion object {
        fun fromInputChunk(chunkedInput: List<String>): MappingGroup =
            MappingGroup(chunkedInput.slice(1..chunkedInput.lastIndex).map { Mapping.fromString(it) })
    }

    fun getDestination(value: Long): Long {
        val matchingMappings = mappings.filter { value in it.sourceRange }
        return if (matchingMappings.isNotEmpty()) matchingMappings.first().getMapping(value) else value
    }

    fun matchingMaps(range: LongRange): List<LongRange> {
        val output: MutableList<Pair<LongRange, Long>> = mutableListOf()
        var nextRangeFirst = range.first
        mappings
            .sortedBy { it.source }
            .forEach { mapping ->
                val intersect = boundaries(range, mapping.sourceRange)
                if (intersect != null) {
                    if (intersect.first > nextRangeFirst) {
                        output.add(Pair(nextRangeFirst until intersect.first, 0))
                    }
                    output.add(Pair(intersect, mapping.offset))
                    nextRangeFirst = intersect.last + 1
                }
            }
        if (nextRangeFirst <= range.last) {
            output.add(Pair(nextRangeFirst..range.last, 0))
        }
        return output.map { (range, offset) ->
            range.first + offset..range.last + offset
        }
    }

    private fun boundaries(A: LongRange, B: LongRange): LongRange? {
        val low = max(A.first, B.first)
        val high = min(A.last, B.last)
        if (high < low) return null
        return low..high
    }
}

data class Mapping(
    val destination: Long,
    val source: Long,
    val size: Long,
) {
    val sourceRange = source until source + size
    val offset = destination - source

    companion object {
        fun fromString(mappingString: String): Mapping = fromLongList(mappingString.toLongList())

        private fun fromLongList(mapListLong: List<Long>): Mapping =
            Mapping(mapListLong[0], mapListLong[1], mapListLong[2])
    }

    fun getMapping(value: Long): Long = if (value in this.source..this.source + this.size) offset + value else value
}


class AlmanacReader : Solver() {
    override fun solve(input: List<String>): Pair<Any, Any> {
        val chunkedInput = input.chunkInput()
        val mappingGroups = chunkedInput.slice(1..chunkedInput.lastIndex).map { MappingGroup.fromInputChunk(it) }

        val seeds = chunkedInput.readSeedNumbers().sortedBy { it }
        val seedRanges = chunkedInput.readSeedNumberRanges()

        return seeds
            .locationsForSeeds(mappingGroups)
            .min() to
                seedRanges.locationsForSeedRanges(mappingGroups).first().min()
    }


    private fun List<List<String>>.readSeedNumbers() =
        this.first().first().substring(7).toLongList()

    private fun List<List<String>>.readSeedNumberRanges(): List<LongRange> =
        this.readSeedNumbers().withIndex()
            .groupBy { it.index / 2 }
            .map { it -> it.value.map { it.value } }
            .map { it.first()..it.first() + it.last() }

    private fun List<Long>.locationsForSeeds(mappingGroups: List<MappingGroup>) =
        this.map { seed ->
            var value = seed
            mappingGroups.forEach { group ->
                value = group.getDestination(value)
            }
            value
        }

    private fun List<LongRange>.locationsForSeedRanges(mappingGroups: List<MappingGroup>): List<LongRange> =
        this.flatMap { listOf(it).traverseRanges(mappingGroups) }.sortedBy { it.first }

    private fun List<LongRange>.traverseRanges(mappingGroups: List<MappingGroup>): List<LongRange> {
        var ranges = this
        mappingGroups.forEach { group ->
            ranges = findRangesInMap(ranges, group)
        }
        return ranges.sortedBy { it.first}
    }

    private fun findRangesInMap(ranges: List<LongRange>, mappingGroup: MappingGroup): List<LongRange> =
        ranges.flatMap { mappingGroup.matchingMaps(it) }
}
