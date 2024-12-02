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
