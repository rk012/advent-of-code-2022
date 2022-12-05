fun main() {
    fun List<String>.getNums() = map { s ->
        s.split('-', ',').map { it.toInt() }
    }

    fun part1(input: List<String>) = input.getNums().fold(0) { acc, (s0, e0, s1, e1) ->
        when {
            s0 <= s1 && e0 >= e1 -> acc + 1
            s0 >= s1 && e0 <= e1 -> acc + 1
            else -> acc
        }
    }

    fun part2(input: List<String>) = input.getNums().fold(0) { acc, (s0, e0, s1, e1) ->
        if (e0 >= s1 && s0 <= e1) acc + 1
        else acc
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
