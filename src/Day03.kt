fun main() {
    val symbols = "*/=+$#@&-"

    fun part1(input: List<String>): Int {
        var sum = 0
        var nextNum = ""
        var adjacent = ""

        fun checkNum() {
            if (nextNum.isNotEmpty() && adjacent.any { it in symbols }) {
                sum += nextNum.toInt()
            }
            nextNum = ""
            adjacent = ""
        }

        for (y in input.indices) {
            for (x in 0..<input.first().length) {
                if (input[y][x].isDigit()) {
                    // add current digit to the last number sequence
                    nextNum += input[y][x]
                    // add upper and lower adjacent
                    if (y > 0) adjacent += input[y - 1][x]
                    if (y < input.size - 1) adjacent += input[y + 1][x]
                    // check if this digit either first or last
                    val isFirstDigit = nextNum.length == 1 && x > 0
                    val isLastDigit = x < input[y].length - 1 && !input[y][x + 1].isDigit()
                    // add adjacent for the left margin
                    if (isFirstDigit) {
                        adjacent += input[y][x - 1]
                        if (y > 0) adjacent += input[y - 1][x - 1]
                        if (y < input.size - 1) adjacent += input[y + 1][x - 1]
                    }
                    // add adjacent for the right margin
                    if (isLastDigit) {
                        adjacent += input[y][x + 1]
                        if (y > 0) adjacent += input[y - 1][x + 1]
                        if (y < input.size - 1) adjacent += input[y + 1][x + 1]
                    }
                } else checkNum()
            }
            checkNum()
        }
        return sum
    }

    fun part2(input: Array<CharArray>): Int {
        return 0
    }

    val testInput = "467..114..\n" +
            "...*......\n" +
            "..35..633.\n" +
            "......#...\n" +
            "617*......\n" +
            ".....+.58.\n" +
            "..592.....\n" +
            "......755.\n" +
            "...$.*....\n" +
            ".664.598.."

    println(part1(testInput.split("\n")) == 4361)

    val input = readInput("day03")
    part1(input).println()
}
