package models

import kotlinx.serialization.Serializable

@Serializable
data class JsonGraph(val nodes: IntArray, val edges: Array<Int>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JsonGraph

        if (!nodes.contentEquals(other.nodes)) return false
        if (!edges.contentEquals(other.edges)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nodes.contentHashCode()
        result = 31 * result + edges.contentHashCode()
        return result
    }

}
