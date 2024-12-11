package me.quido.util

fun String.toLongList(separator: String = " "): List<Long> =
    this
        .split(separator)
        .filter { it.isNotEmpty() }
        .map { it.toLong() }

fun String.toLongList(separator: Regex): List<Long> =
    this
        .split(separator)
        .map { it.toLong() }

fun String.toIntList(separator: String = " "): List<Int> =
    this
        .split(separator)
        .filter { it.isNotEmpty() }
        .map { it.toInt() }

fun String.toIntList(separator: Regex): List<Int> =
    this
        .split(separator)
        .map { it.toInt() }

fun String.toPairOfInt(separator: Regex): Pair<Int, Int> =
    this
        .split(separator)
        .let { Pair(it[0].toInt(), it[1].toInt()) }

fun String.toPairOfInt(separator: String = " "): Pair<Int, Int> =
    this
        .split(separator)
        .let { Pair(it[0].toInt(), it[1].toInt()) }
