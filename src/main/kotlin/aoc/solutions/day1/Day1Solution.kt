package aoc.solutions.day1

import aoc.Solution

class Day1Solution : Solution(1) {

    override fun solvePartOne(input: String): String {
        var sum = 0
        val lastIndex = input.length - 1

        (0..lastIndex).forEach { i ->
            val followingIndex = (i + 1) % input.length

            val firstNumber = input[i].toString().toInt()
            val secondNumber = input[followingIndex].toString().toInt()

            if (firstNumber == secondNumber) {
                sum += firstNumber
            }
        }

        return sum.toString()
    }

    override fun solvePartTwo(input: String): String {
        var sum = 0
        val lastIndex = input.length - 1
        val halfListSize = input.length / 2

        (0..lastIndex).forEach { i ->
            val followingIndex = (i + halfListSize) % input.length

            val firstNumber = input[i].toString().toInt()
            val secondNumber = input[followingIndex].toString().toInt()

            if (firstNumber == secondNumber) {
                sum += firstNumber
            }
        }

        return sum.toString()
    }
}