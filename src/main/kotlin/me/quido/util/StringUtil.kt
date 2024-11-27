package me.quido.util

fun String.toLongList(separator: String = " "): List<Long> =
    this
        .split(separator)
        .filter { it.isNotEmpty() }
        .map { it.toLong() }
