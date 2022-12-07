fun main() {
    fun List<String>.parseLayout() = ((last().length + 2) / 4).let { size ->
        dropLast(1).map { line ->
            ("$line ").chunked(4).let { items ->
                List(size) {
                    items.getOrNull(it)?.ifBlank { null }?.get(1)
                }
            }
        }
    }

    fun List<String>.layoutColumns() = parseLayout().let { layout ->
        List(layout.last().size) { n ->
            layout.map { it.getOrNull(n) }.asReversed().filterNotNull()
        }
    }

    fun List<List<Char>>.performMove(amount: Int, from: Int, to: Int) = mapIndexed { i, items ->
        when (i) {
            from-1 -> items.dropLast(amount)
            to-1 -> items + get(from-1).takeLast(amount).asReversed()
            else -> items
        }
    }

    fun List<List<Char>>.performGroupMove(amount: Int, from: Int, to: Int) = mapIndexed { i, items ->
        when (i) {
            from-1 -> items.dropLast(amount)
            to-1 -> items + get(from-1).takeLast(amount)
            else -> items
        }
    }

    fun List<String>.runMoves(useChunked: Boolean) = let { lines ->
        lines.takeWhile { it.isNotBlank() }.layoutColumns() to lines.takeLastWhile { it.isNotBlank() }
    }.let { (columns, directions) ->
        directions.map { s ->
            s.split(" ").let { chunks ->
                listOf(chunks[1], chunks[3], chunks[5]).map {
                    it.toInt()
                }
            }
        }.fold(columns) { acc, (amount, from, to) ->
            if (useChunked) acc.performGroupMove(amount, from, to)
            else acc.performMove(amount, from, to)
        }.map {
            it.last()
        }.joinToString("")
    }

    fun part1(input: List<String>) = input.runMoves(false)

    fun part2(input: List<String>) = input.runMoves(true)

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")

    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
