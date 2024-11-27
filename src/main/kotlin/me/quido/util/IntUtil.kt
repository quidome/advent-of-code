package me.quido.util

fun Pair<Long, Long>.toLongRange() = LongRange(this.first, this.second)
