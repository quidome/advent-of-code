package me.quido.year2023.engineering

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EngineReaderTest {
    private val engineReader = EngineReader()

    @Test
    fun `test engine part identifier`() {
        val input =
            """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
            """.trimIndent().lines()
        val expected = 4361 to 467835

        val output = engineReader.solve(input)

        assertEquals(expected, output)
    }
}
