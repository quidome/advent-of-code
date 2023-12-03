package me.quido.year2023.calibrating

import me.quido.util.nonBlank
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TrebuchetTest {
    val trebuchet = Trebuchet()

    @Test
    fun `verify calibration result`(){
        // Arrange
        val input = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent().lines()

        // Act
        val output = trebuchet.solve(input)

        // Assert
        assertEquals(142 to 142, output)
    }

    @Test
    fun `numbers as word are replaced by actual numbers`() {
        val input = """
            twone
        """.trimIndent().lines()

        val output = trebuchet.solve(input)

        assertEquals(0 to 21, output)
    }

    @Test
    fun `verify next calibration result`(){
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
        println(input.nonBlank())
        val output = trebuchet.solve(input.nonBlank())
        // Assert
        assertEquals(209 to 281, output)

    }
}