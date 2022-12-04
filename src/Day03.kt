private const val ITEM_TYPES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

fun main() {
    fun part1(input: List<String>) = input.map { items ->
        items.length.let {
            items.slice(0 until it / 2) to items.slice(it / 2 until it)
        }
    }.map { (item1, item2) ->
        item1.find { item2.contains(it) }!!
    }.sumOf {
        ITEM_TYPES.indexOf(it) + 1
    }

    fun part2(input: List<String>) = input.chunked(3).map { (b1, b2, b3) ->
        b1.first { b2.contains(it) && b3.contains(it) }
    }.sumOf {
        ITEM_TYPES.indexOf(it) + 1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
