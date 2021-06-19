import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import models.JsonGraph
import java.io.File

fun main() {
    val inputFileName = "src/main/resources/Grafo_ponderado.json"
    val json = readFileAsLinesUsingBufferedReader(inputFileName)
    val jsonGraph = Json.decodeFromString<JsonGraph>(json)
    val source = 0
    val target = 14


    println(jsonGraph.toString())
    println()

    val pair = dijkstra(jsonGraph, source, target)
    println("distances -> ${pair.first}")
    println("result -> ${pair.second.contentToString()}")
    println()

    val sequence = shortestPath(source, target, pair.second)
    println("sequence -> ${sequence.contentToString()}")
    println("distance -> ${pair.first[14]}")
}

/**
 * Read file
 *
 * @param fileName Read file
 * @return List of strings read
 */
fun readFileAsLinesUsingBufferedReader(fileName: String): String = File(fileName).bufferedReader().use { it.readText() }

fun dijkstra(graph: JsonGraph, source: Int, target: Int): Pair<MutableMap<Int, Int>, Array<Int?>> {
    val Q = graph.nodes.map { it }.toMutableList()

    val dist = graph.nodes.associateWith { Int.MAX_VALUE }.toMutableMap()
    val prev = arrayOfNulls<Int?>(graph.nodes.size)

    dist[source] = 0

    while (Q.isNotEmpty()) {
        val u = dist.filter { Q.contains(it.key) }.minByOrNull { it.value }?.key!!

        Q.remove(u)

        if (u == target)
            return Pair(dist, prev)

        graph.edges.filter { edge -> edge[0] == u && Q.contains(edge[1]) }
            .forEach { neighbor ->
                val alt = dist[u]?.plus(neighbor[2])
                if (alt!! < dist[neighbor[1]]!!) {
                    dist[neighbor[1]] = alt
                    prev[neighbor[1]] = u
                }
            }

    }
    return Pair(dist, prev)
}

fun shortestPath(source: Int, target: Int, sequence: Array<Int?>): IntArray {
    val S = mutableListOf<Int>()
    var u: Int? = target
    if (sequence[u!!] != null || u == source) {
        while (u != null) {
            S.add(u)
            u = sequence[u]
        }
    }
    return S.reversed().toIntArray()
}