package models

import kotlinx.serialization.Serializable

@Serializable
data class JsonGraph(val nodes: Set<Int>, val edges: Array<IntArray>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JsonGraph

        if (nodes != other.nodes) return false
        if (!edges.contentDeepEquals(other.edges)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nodes.hashCode()
        result = 31 * result + edges.contentDeepHashCode()
        return result
    }

}
