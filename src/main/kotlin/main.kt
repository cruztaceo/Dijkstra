import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import models.JsonGraph
import java.io.File

fun main() {
    val inputFileName = "src/main/resources/Grafo_ponderado.json"
    val json = readFileAsLinesUsingBufferedReader(inputFileName)
    val jsonGraph = Json.decodeFromString<JsonGraph>(json)

    println(jsonGraph.toString())
}

/**
 * Read file
 *
 * @param fileName Read file
 * @return List of strings read
 */
fun readFileAsLinesUsingBufferedReader(fileName: String): String = File(fileName).bufferedReader().use { it.readText() }
