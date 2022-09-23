import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Scanner

fun check(fileName : String, s : String) : Boolean {

    val n : Int
    val m : Int
    var start : List<Int>
    val finish : List<Int>

    val lines = Files.readAllLines(Paths.get(fileName)).map {
        it.split(" ")
    }

    n = lines[0][0].toInt()
    m = lines[1][0].toInt()
    start = lines[2].map {it.toInt()}
    finish = lines[3].map {it.toInt()}
    val g : MutableList<MutableMap<Int, List<Int>>> = MutableList(n) { mutableMapOf() }
    lines.subList(4, lines.size).forEach {
        g[it[0].toInt()][it[1].toInt()] = g[it[0].toInt()].getOrDefault(it[1].toInt(), listOf()) + it[2].toInt()
    }

    s.forEach { c ->
        start = start.map { vertex ->
            g[vertex].getOrDefault(c.digitToInt(), listOf())
        }.flatten()
    }

    return finish.toSet().intersect(start.toSet()).isNotEmpty()
}

fun main() {

    val fileName = "file.txt"

    val s : String = readLine()!!

    try {
        println(check(fileName, s))
    } catch (e: IOException) {
        e.printStackTrace()
    }

}