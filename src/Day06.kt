fun main() {
    fun String.getSequences(size: Int) = runningFold("") { acc, c ->
        (acc + c).takeLast(size)
    }.drop(size)

    tailrec fun hasSameChars(c: Char?, s: String): Boolean = when {
        s.isEmpty() -> false
        c?.let { s.contains(it) } == true -> true
        else -> hasSameChars(s.first(), s.drop(1))
    }

    fun part1(input: List<String>) = input[0].getSequences(4).indexOfFirst { !hasSameChars(null, it) } + 4

    fun part2(input: List<String>) = input[0].getSequences(14).indexOfFirst { !hasSameChars(null, it) } + 14

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")

    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
