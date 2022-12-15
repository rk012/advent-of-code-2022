fun main() {
    data class File(
        val size: Int,
        val name: String,
        val dirPath: String
    )

    data class Filesystem(
        val dirs: Set<String>,
        val files: Set<File>
    )

    fun List<String>.parseFileSystem() = drop(1).fold(
        Filesystem(emptySet(), emptySet()) to ""
    ) { (fs, dir), s ->
        when {
            s.startsWith("$ cd") -> s.split(' ')[2].let { dirName ->
                when (dirName) {
                    "/" -> ""
                    ".." -> dir.split('/').dropLast(1).joinToString("/")
                    else -> "$dir/$dirName"
                }.let { fs to it }
            }
            s.startsWith("$ ls") -> fs to dir
            else -> s.split(' ').let { (type, fname) ->
                if (type == "dir") fs.copy(dirs = fs.dirs + "$dir/$fname") to dir
                else fs.copy(files = fs.files + File(type.toInt(), fname, dir)) to dir
            }
        }
    }.first

    fun Filesystem.getDirSizes() = let { (dirs, files) ->
        (dirs + "").map { dir ->
            files.filter { it.dirPath.startsWith(dir) }.sumOf { it.size }
        }
    }

    fun part1(input: List<String>) = input.parseFileSystem().getDirSizes().filter { it <= 100_000 }.sum()

    fun part2(input: List<String>) = input.parseFileSystem().getDirSizes().let { sizes ->
        (30_000_000 - 70_000_000 + sizes.max()).let { minSize ->
            sizes.filter { it >= minSize }.min()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")

    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
