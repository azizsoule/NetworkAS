package ci.miage.mob.networkAS.models

import android.os.Build
import androidx.annotation.RequiresApi

class Graph(val nodes: MutableSet<Node> = mutableSetOf<Node>(), val links : MutableSet<Link> = mutableSetOf<Link>()) {

    fun addNode(node : Node) : Boolean {
        return nodes.add(node)
    }

    fun addLink(link: Link) : Boolean {
        return links.add(link)
    }

    fun removeLink(link: Link) : Boolean {
        return links.remove(link)
    }

    fun hasLinkBetween(node1 : Node, node2 : Node): Boolean {
        val link : Link? = try {
            links.first { link ->  link.isBetween(node1, node2)}
        } catch (error : NoSuchElementException) {
            null
        }
        return link != null
    }

    fun getTouchedNode(fingerPositionX: Float, fingerPositionY: Float) : Node? {
        val node : Node? = try {
            nodes.first { node -> node.isTouched(fingerPositionX, fingerPositionY) }
        } catch (error : NoSuchElementException) {
            null
        }

        return node
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun removeNode(node : Node) : Boolean {
        links.removeIf { link -> link.start == node || link.end == node }
        return nodes.remove(node)
    }

}