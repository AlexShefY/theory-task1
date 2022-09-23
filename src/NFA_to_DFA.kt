import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

fun NFAtoDFA(fileName : String) {

    val n : Int
    val m : Int
    val start : Set<Int>
    val finish : Set<Int>

    val lines = Files.readAllLines(Paths.get(fileName)).map {
        it.split(" ")
    }

    n = lines[0][0].toInt()
    m = lines[1][0].toInt()
    start = lines[2].map {it.toInt()}.toSet()
    finish = lines[3].map {it.toInt()}.toSet()
    val g : MutableList<MutableMap<Int, List<Int>>> = MutableList(n) { mutableMapOf() }
    lines.subList(4, lines.size).forEach {
        g[it[0].toInt()][it[1].toInt()] = g[it[0].toInt()].getOrDefault(it[1].toInt(), listOf()) + it[2].toInt()
    }

    var index = 0
    val que : Queue<Set<Int>> = LinkedList()
    que.add(start)
    val map : MutableMap<Set<Int>, Int> = mutableMapOf()
    val new_graph : MutableMap<Int, MutableList<Pair<Int, Int>>> = mutableMapOf()
    map[start] = index++
    var finishDFA : MutableList<Int> = mutableListOf()
    while (!que.isEmpty()) {
        val curSet = que.remove()
        if (!curSet.intersect(finish).isEmpty()) {
            finishDFA.add(map[curSet]!!)
        }
        repeat(m) {
            var nextSet = setOf<Int>()
            curSet.forEach { vertex ->
                nextSet = nextSet.plus(g[vertex].getOrDefault(it, listOf()))
            }
            if (!map.containsKey(nextSet)) {
                que.add(nextSet)
                map[nextSet] = index++
            }
            new_graph[map[curSet]!!] = (new_graph.getOrDefault(map[curSet]!!, mutableListOf()) + Pair(it, map[nextSet]!!)).toMutableList()
        }
    }
    File(fileName).printWriter().use { out ->
        out.println(index)
        out.println(m)
        out.println(map[start])
        out.println(finishDFA.joinToString(" "))
        new_graph.keys.forEach { from ->
            new_graph.getOrDefault(from, mutableListOf()).forEach { (symb, to) ->
                out.println("$from $symb $to")
            }
        }
    }
}

fun main() {

    val fileName = "file1.txt"

    try {
        NFAtoDFA(fileName)
    } catch (e: IOException) {
        e.printStackTrace()
    }

}