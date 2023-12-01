package me.quido.year2022.day3

class Rucksack(contents: String) {
    private val compartmentA: Set<Char>
    private val compartmentB: Set<Char>

    init {
        val ruckSackSeparator = contents.length / 2
        compartmentA = contents.substring(0, ruckSackSeparator).toSet()
        compartmentB = contents.substring(ruckSackSeparator, contents.length).toSet()
    }

    fun fixPriority(): Int =
        wronglyPlacedItems().sumOf { item -> itemPriority(item) }

    private fun wronglyPlacedItems(): Set<Char> =
        compartmentA.intersect(compartmentB)


    private fun itemPriority(item: Char): Int =
        when (item.isUpperCase()) {
            true -> item.code - 38
            false -> item.code - 96
        }
}