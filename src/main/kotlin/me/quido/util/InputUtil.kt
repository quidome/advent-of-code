package me.quido.util

fun getInputAsText(year: Int, day: Int): String? =
    object {}
        .javaClass
        .getResource("/input/$year/day$day.txt")
        ?.readText()
        ?.trimEnd()

fun Any.readFile(fileName: String, year: Int) =
    javaClass
        .getResource("/input/$year/$fileName")
        ?.readText()
        ?.lines()
        ?.dropLastWhile { it.isEmpty() }

fun List<String>.nonBlank() = filter { it.isNotBlank() }

fun List<String>.chunkInput(): List<List<String>> =
    this.fold(mutableListOf(mutableListOf<String>())) { acc, str ->
        if (str.isNotEmpty()) {
            acc.last().add(str)
        } else {
            acc.add(mutableListOf())
        }
        acc
    }
