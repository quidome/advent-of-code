package me.quido.year2023.gaming

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BoatRaceCalculatorTest {
    private val boatRaceCalculator = BoatRaceCalculator()

    @Test
    fun `when given race data, calculate possible race outcomes`() {
        val input =
            """
            Time:      7  15   30
            Distance:  9  40  200
            """.trimIndent().lines()
        val expected = 288 to 71503

        val output = boatRaceCalculator.solve(input)

        assertEquals(expected, output)
    }
}
