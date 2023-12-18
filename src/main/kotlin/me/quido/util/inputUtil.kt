package me.quido.util

fun getInputAsText(year: Int, day: Int): String? =
    object {}.javaClass.getResource("/input/$year/day${day}.txt")?.readText()?.trimEnd()

fun Any.readFile(fileName: String, year: Int) =
    javaClass.getResource("/input/$year/$fileName")?.readText()?.lines()

fun List<String>.nonBlank() = filter { it.isNotBlank() }

fun List<String>.chunkInput(): List<List<String>> {
    val chunks = mutableListOf<List<String>>()

    var lastIndex = 0
    this.forEachIndexed { index, row ->
        if (row.isEmpty() || index == this.lastIndex) {
            val chunk = this.slice(lastIndex until index)
            chunks.add(chunk)
            lastIndex = index + 1
        }
    }
    return chunks
}
