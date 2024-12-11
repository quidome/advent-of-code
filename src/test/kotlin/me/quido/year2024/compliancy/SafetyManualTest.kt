package me.quido.year2024.compliancy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SafetyManualTest {
    @Test
    fun `print the proper safety manual`() {
        val input =
            """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
            """.trimIndent().lines()
        val expected = 143 to 123
        val safetyManual = SafetyManual()

        val output = safetyManual.solve(input)

        assertEquals(expected, output)
    }
}
