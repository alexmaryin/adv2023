import kotlin.math.max

fun main() {

    data class CubeSet(val red: Int = 0, val green: Int = 0, val blue: Int = 0)
    data class GameSet(val index: Int, val cubeSet: CubeSet)

    fun unpackString(line: String): GameSet {
        val (idxStr, setStr) = line.split(": ", limit = 2)
        val index = idxStr.substringAfter("Game ").toInt()
        var red = 0
        var green = 0
        var blue = 0
        setStr.split("; ").forEach {
            it.split(", ").map { cell ->
                val (count, color) = cell.split(" ", limit = 2)
                when (color) {
                    "red" -> red = max(red, count.toInt())
                    "green" -> green = max(green, count.toInt())
                    "blue" -> blue = max(blue, count.toInt())
                }
            }
        }
        return GameSet(index, CubeSet(red, green, blue))
    }

    fun part1(input: List<String>): Int {
        return input.map(::unpackString)
            .filter { gameSet ->
                gameSet.cubeSet.run {
                    red <= 12 && green <= 13 && blue <= 14
                }
            }
            .sumOf { it.index }
    }

    fun part2(input: List<String>): Int {
        return input.map(::unpackString).sumOf { gameSet ->
            gameSet.cubeSet.run {
                red * green * blue
            }
        }
    }

    val testInput = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
    println(part1(testInput.split("\n")) == 8)
    println(part2(testInput.split("\n")) == 2286)

    val input = readInput("day02")
    part1(input).println()
    part2(input).println()
}
