package me.quido.year2023.gaming

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CubeCalculatorTest {
    private val cubeCalculator = CubeCalculator()

    @Test
    fun `Test that we can calculate cubes`() {
        // Arrange
        val input = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green 
        """.trimIndent().lines()
        val expectedOutput = 8 to 2286

        // Act
        val output = cubeCalculator.solve(input)

        // Assert
        assertEquals(expectedOutput, output )
    }
}
