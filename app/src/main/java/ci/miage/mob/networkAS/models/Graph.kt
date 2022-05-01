package ci.miage.mob.networkAS.models

import android.os.Build
import androidx.annotation.RequiresApi

class Graph(val nodes: MutableSet<Node> = HashSet<Node>(), val links : MutableSet<Link> = HashSet<Link>()) {

    fun addNode(node : Node) : Boolean {
        return nodes.add(node)
    }

    fun getTouchedNode(fingerPositionX: Float, fingerPositionY: Float) : Node? {
        var node : Node? = try {
            nodes.first { node -> node.isTouched(fingerPositionX, fingerPositionY) }
        } catch (error : NoSuchElementException) {
            null
        }
        if (node != null) {
            println("Touchéeeee")
        } else {
            println("Loupéeeee")
        }
        return node
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun removeNode(node : Node) : Boolean {
        links.removeIf { link -> link.start == node || link.end == node }
        return nodes.remove(node)
    }

}