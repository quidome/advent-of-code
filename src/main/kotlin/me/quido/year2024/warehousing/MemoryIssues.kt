package me.quido.year2024.warehousing

import me.quido.util.Solver

class MemoryIssues : Solver() {
    private val mulRegex = """mul\((\d+),(\d+)\)""".toRegex()
    private val operationsRegex = """(mul\(\d+,\d+\)|do\(\)|don't\(\))""".toRegex()

    override fun solve(input: List<String>): Pair<Any, Any> {
        val restoredOperations = input.joinToString("").restoreCorruptedMemory()

        val simpleOperationsOutput = calculateMultiplications(restoredOperations, true)
        val conditionalOperationsOutput = calculateMultiplications(restoredOperations, false)

        return simpleOperationsOutput to conditionalOperationsOutput
    }

    private fun String.restoreCorruptedMemory(): List<String> = operationsRegex.findAll(this).map { it.groupValues[1] }.toList()

    private fun calculateMultiplications(operations: List<String>, ignoreConditionals: Boolean = true): Int {
        var total = 0
        var enableMultiplication = true

        operations.forEach { fullOperation ->
            val operation = fullOperation.substringBefore("(")

            when (operation) {
                "do" -> enableMultiplication = true
                "don't" -> enableMultiplication = ignoreConditionals
            }

            if (enableMultiplication && operation == "mul") {
                val numbers = extractMulNumbers(fullOperation)
                total += (numbers.first * numbers.second)
            }
        }

        return total
    }

    private fun extractMulNumbers(operation: String): Pair<Int, Int> {
        val matchResult = mulRegex.matchEntire(operation)

        if (matchResult != null) {
            val (num1, num2) = matchResult.destructured
            return Pair(num1.toInt(), num2.toInt())
        } else {
            return Pair(0, 0)
        }
    }
}
