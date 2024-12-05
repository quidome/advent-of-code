package me.quido.year2024.gaming

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WordSearchTest {
    @Test
    fun `find XMAS in input`() {
        val inputSimple =
            """
            XAAA
            AMAA
            SAAA
            AAAS
            """.trimIndent().lines()
        val input =
            """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
            """.trimIndent().lines()
        val expected = 18 to false
        val wordSearch = WordSearch()

        val output = wordSearch.solve(input)

        assertEquals(expected, output)
    }
}
