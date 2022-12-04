fun main() {
    fun List<String>.readWithNulls() = scan<_, Int?>(null) { acc, s ->
        if (s.isBlank()) null
        else (acc ?: 0) + s.toInt()
    }.let { it + null }

    fun part1(input: List<String>) = input.readWithNulls().filterNotNull().max()

    fun part2(input: List<String>) = input.readWithNulls().let { l ->
        l.indices.filter { l[it] == null }.map { it - 1 } to l
    }.let { (i, l) ->
        l.filterIndexed { index, _ -> i.contains(index) }.filterNotNull()
    }.sortedDescending().subList(0, 3).sum()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
