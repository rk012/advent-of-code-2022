private const val ROCK = 0
private const val PAPER = 1
private const val SCISSORS = 2

fun main() {
    fun Char.asItem() = when (this) {
        in "AX" -> ROCK
        in "BY" -> PAPER
        in "CZ" -> SCISSORS
        else -> fail()
    }

    fun itemBeats(item: Int) = when (item) {
        ROCK -> SCISSORS
        PAPER -> ROCK
        SCISSORS -> PAPER
        else -> fail()
    }

    fun itemLoses(item: Int) = when (item) {
        ROCK -> PAPER
        PAPER -> SCISSORS
        SCISSORS -> ROCK
        else -> fail()
    }

    fun itemScore(item: Int) = when (item) {
        ROCK -> 1
        PAPER -> 2
        SCISSORS -> 3
        else -> fail()
    }

    fun List<Pair<Int, Int>>.getScore() = fold(0) { acc, (opp, play) ->
        acc + itemScore(play) + when (opp) {
            itemBeats(play) -> 6
            play -> 3
            else -> 0
        }
    }

    fun part1(input: List<String>) = input.map {
        it[0].asItem() to it[2].asItem()
    }.getScore()

    fun part2(input: List<String>) = input.map {
        it[0].asItem() to it[2]
    }.map { (opp, result) ->
        opp to when (result) {
            'X' -> itemBeats(opp)
            'Y' -> opp
            'Z' -> itemLoses(opp)
            else -> fail()
        }
    }.getScore()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
