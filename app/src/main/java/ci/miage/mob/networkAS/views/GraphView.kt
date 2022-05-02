package ci.miage.mob.networkAS.views

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import ci.miage.mob.networkAS.models.Graph
import ci.miage.mob.networkAS.models.Link
import ci.miage.mob.networkAS.models.Node
import ci.miage.mob.networkAS.models.enums.Menu
import ci.miage.mob.networkAS.models.enums.Mode

class GraphView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr)  {

    var touchedNode : Node? = null
    private val virtualNode = Node(0f, 0f, "", Color.TRANSPARENT, context)
    var virtualLink : Link = Link(virtualNode, virtualNode, "", Color.YELLOW, context)

    var mode: Mode = Mode.ADD_NODE
    lateinit var menu: Menu

    //private val node1 = Node(posX = 200f, posY = 500f, color = Color.BLUE, label = "node1",context = context)
    //private val node2 = Node(posX = 50f, posY = 200f, color = Color.RED, label = "node2", context = context)
    //private val linkNode1Node2 = Link(node1, node2, color = Color.GREEN, label = "link1", context = context)

    private var graph: Graph = Graph()

    override fun onDraw(canvas: Canvas?) {

        canvas!!.drawColor(Color.WHITE)

        //graph.addNode(node1);
        //graph.addNode(node2)
        //graph.addLink(linkNode1Node2)

        for (node in graph.nodes) {
            node.draw(canvas)
        }

        for (link in graph.links) {
            link.draw(canvas)
        }

    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            return when (event.actionMasked) {
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    onTouchEventFinish(event)
                }

                else -> {
                    super.dispatchTouchEvent(event)
                }
            }
        }

        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private fun onTouchEventFinish(event: MotionEvent): Boolean {
        when (mode) {

            Mode.ADD_LINK -> {
                val linkEndNode = graph.getTouchedNode(event.x, event.y)
                graph.removeLink(virtualLink)
                if (linkEndNode != null && touchedNode != null && linkEndNode != touchedNode && !graph.hasLinkBetween(touchedNode as Node, linkEndNode)) {
                    graph.addLink(Link(touchedNode as Node, linkEndNode, "Connexion", Color.YELLOW, context))
                }
                invalidate()
            }

        }

        return true
    }

    private val listener =  object : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(event: MotionEvent): Boolean {
            touchedNode = graph.getTouchedNode(event.x, event.y)
            return true
        }

        override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {

            when (mode) {

                Mode.ADD_NODE -> {
                    if (touchedNode != null && p1 != null) {
                        touchedNode!!.moveTo(p1.x, p1.y)
                        invalidate()
                    }
                }

                Mode.ADD_LINK -> {
                    if (touchedNode != null && p1 != null) {
                        virtualLink.start = touchedNode as Node
                        virtualNode.moveTo(p1.x, p1.y)
                        graph.addLink(virtualLink)
                        invalidate()
                    }
                }

                Mode.EDIT -> {

                }

            }

            return true
        }

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onLongPress(e: MotionEvent?) {

            when (mode) {

                Mode.ADD_NODE -> {
                    e?.let { graph.addNode(Node(posX = e.x, posY = e.y, color = Color.GREEN, label = "Objet", context = context)) }
                    invalidate()
                }

                Mode.ADD_LINK -> {

                }

                Mode.EDIT -> {
                    if (touchedNode != null) {
                        if (graph.removeNode(touchedNode as Node)) {
                            invalidate()
                        }
                    }
                }

            }

        }

    }

    private val gestureDetector: GestureDetector = GestureDetector(context, listener)

}