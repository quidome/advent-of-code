package me.quido.year2024.warehousing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MemoryIssuesTest {
    @Test
    fun `return multiply results without conditionals taken into account`() {
        val input =
            """
            xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
            """.trimIndent().lines()

        val memoryIssues = MemoryIssues()
        val expected = 161

        val output = memoryIssues.solve(input).first

        assertEquals(expected, output)
    }

    @Test
    fun `return multiply results wit conditionals taken into account`() {
        val input =
            """
            xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
            """.trimIndent().lines()

        val memoryIssues = MemoryIssues()
        val expected = 48

        val output = memoryIssues.solve(input).second

        assertEquals(expected, output)
    }
}
