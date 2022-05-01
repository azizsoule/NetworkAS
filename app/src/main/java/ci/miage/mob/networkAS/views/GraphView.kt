package ci.miage.mob.networkAS.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import ci.miage.mob.networkAS.models.Graph
import ci.miage.mob.networkAS.models.Link
import ci.miage.mob.networkAS.models.Node

class GraphView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr)  {

    private val node1 = Node(x = 200f, y = 500f, color = Color.BLUE, label = "node1")
    private val node2 = Node(x = 50f, y = 200f, color = Color.RED, label = "node2")

    private var graph: Graph = Graph(
        nodes = mutableSetOf(node1, node2),
        links = mutableSetOf(Link(start = node1, end = node2, color = Color.GREEN, label = "Link"))
    )

    private fun drawNode(node: Node, canvas: Canvas) {
        val paint: Paint = Paint()

        paint.color = node.color
        canvas.drawCircle(node.x, node.y, Node.RADIUS, paint)

        paint.textSize = 24f
        canvas.drawText(node.label, node.x - (Node.RADIUS + 5), node.y - (Node.RADIUS + 5), paint)
    }

    private fun drawLink(link: Link, canvas: Canvas) {
        val paint: Paint = Paint()
        val path: Path = Path()

        path.moveTo(link.start.x, link.start.y)
        path.lineTo(link.end.x, link.end.y)
        path.close()
        //val pathLength : Float = PathMeasure(path, true).length
        paint.color = link.color
        paint.strokeWidth = 2f
        canvas.drawPath(path, paint)
        //canvas.drawText(link.label, link.start.x + (pathLength/2), link.start.y + (pathLength/2), paint)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        for (node in graph.nodes) {
            drawNode(node, canvas as Canvas);
        }

        for (link in graph.links) {
            drawLink(link, canvas as Canvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private val listener =  object : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            node1.x = e.x
            node1.y = e.y
            invalidate()
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            return true
        }

    }

    private val gestureDetector: GestureDetector = GestureDetector(context, listener)

}