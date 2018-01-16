package aoc.solutions.day2

import aoc.Solution

class Day2Solution : Solution(2) {
	override fun solvePartOne(input: String): String {
		return splitStringLines(input)
				.map(::getRowNumbers)
				.map(::calculateHashForRow)
				.sum()
				.toString()
	}

	override fun solvePartTwo(input: String): String {
		return splitStringLines(input)
				.map(::getRowNumbers)
				.map(::getDivisibleRowValue)
				.sum()
				.toString()
	}
}

/**
 * Iterate through all values in the row and return the division result of the compatible values.
 */
private fun getDivisibleRowValue(rowNumbers: List<Int>): Int {
	return iterateRowCombinations(rowNumbers).map { rowIteration ->
		val matchResult = findDivisibleRowValue(rowIteration.first, rowIteration.second)
		if (matchResult != null) {
			rowIteration.first / matchResult
		} else {
			0
		}
	}
	.first { it != 0 }
}

/**
 * Find the value in the list that the base value can be divided by without having a remainder (if any).
 */
private fun findDivisibleRowValue(base: Int, list: List<Int>): Int? {
	return list.find { pairListItem -> (base % pairListItem) == 0	}
}

private fun splitStringLines(input: String): List<String> {
	return input.split("\n")
}

/**
 * Basic input parsing for a row.
 */
private fun getRowNumbers(input: String): List<Int> {
	return input.split("\t").map(String::toInt)
}

/**
 * Part 1 hash calculation for a row.
 */
private fun calculateHashForRow(numbers: List<Int>): Int {
	var lowest = numbers.first()
	var highest = numbers.first()

	numbers.forEach { number ->
		if (number < lowest) {
			lowest = number
		} else if (number > highest) {
			highest = number
		}
	}

	return highest - lowest
}

/**
 * Iterate through possible row combinations for part 2.
 */
private fun iterateRowCombinations(row: List<Int>): Iterable<Pair<Int, List<Int>>> {
	return (0..row.lastIndex).map { index ->
		// row that filters out the current indexed item
		val newRow = row.filterIndexed { filterIndex, _ -> filterIndex != index }
		Pair(row[index], newRow)
	}
}