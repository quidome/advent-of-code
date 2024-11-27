package me.quido.year2024

import kotlin.test.Test

class PreparationTest {
    @Test
    fun `test example preparation`() {
        val preparation = Preparation()
        val input =
            """
            foo
            bar
            """.trimIndent().lines()
        val expected = "foo" to "bar"

        val output = preparation.solve(input)

        kotlin.test.assertEquals(expected, output)
    }
}
