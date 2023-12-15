package me.quido.year2023.engineering

import me.quido.util.nonBlank
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TrebuchetCalibratorTest {
    private val trebuchetCalibrator = TrebuchetCalibrator()

    @Test
    fun `verify calibration result`() {
        // Arrange
        val input = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent().lines()

        // Act
        val output = trebuchetCalibrator.solve(input)

        // Assert
        assertEquals(142 to 142, output)
    }

    @Test
    fun `verify next calibration result`() {
        // Arrange
        val input = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
        """.trimIndent().lines()

        // Act
        val output = trebuchetCalibrator.solve(input.nonBlank())

        // Assert
        assertEquals(209 to 281, output)
    }
}
