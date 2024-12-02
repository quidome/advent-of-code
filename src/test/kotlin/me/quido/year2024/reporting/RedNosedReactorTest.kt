package me.quido.year2024.reporting

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RedNosedReactorTest {
    private val input =
        """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9    
        """.trimIndent().lines()

    @Test
    fun solver() {
        val redNosedReactor = RedNosedReactor()
        val expected = 2 to 4

        val output = redNosedReactor.solve(input)

        assertEquals(expected, output)
    }
}
