fun main() {
    val literals = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            "${line.first { it.isDigit() }}${line.last { it.isDigit() }}".toInt()
        }
    }

    fun checkCharIfDigit(line: String, idx: Int): Int? {
        if (line[idx].isDigit()) return line[idx].digitToInt()
        literals.forEach { (key, digit) ->
            if (line.substring(idx).startsWith(key)) return digit
        }
        return null
    }

    fun part2(input: List<String>): Int {

        return input.sumOf { line ->
            var firstDigit: Int? = null
            var lastDigit: Int? = null
            var idx = 0
            while (idx < line.length && (firstDigit == null || lastDigit == null)) {
                // forward look
                if (firstDigit == null) firstDigit = checkCharIfDigit(line, idx)
                // backward look
                if (lastDigit == null) lastDigit = checkCharIfDigit(line, line.length - idx - 1)
                idx++
            }
            firstDigit!! * 10 + lastDigit!!
        }
    }

    val testInput = "1abc2\npqr3stu8vwx\na1b2c3d4e5f\ntreb7uchet"
    assert(part1(testInput.split("\n")) == 142)
    val testInput2 = "two1nine\neightwothree\nabcone2threexyz\nxtwone3four\n4nineeightseven2\nzoneight234\n7pqrstsixteen"
    assert(part2(testInput2.split("\n")) == 281)

    val input = readInput("day01")
    part1(input).println()
    part2(input).println()
}
