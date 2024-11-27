package me.quido.util

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StringUtilKtTest {
    @Test
    fun `with a string containing numbers and separators, a list of long ints is returned`() {
        val input = "  0   1 2 3  "
        val expected: List<Long> = listOf(0, 1, 2, 3)

        val output = input.toLongList(" ")

        assertEquals(expected, output)
    }
}
