package me.quido.util

fun getInputAsText(year: Int, day: Int): String? =
    object {}.javaClass.getResource("/input/year$year/day${day}.txt")?.readText()?.trimEnd()


fun Any.readFile(fileName: String, year: Int) =
    javaClass.getResource("/input/$year/$fileName")?.readText()?.lines()

fun List<String>.nonBlank() = filter { it.isNotBlank() }
