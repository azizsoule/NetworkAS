package ci.miage.mob.networkAS.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import ci.miage.mob.networkAS.models.Graph
import ci.miage.mob.networkAS.models.Link
import ci.miage.mob.networkAS.models.Node

class GraphView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var graph: Graph = Graph()

    private fun drawNode(node: Node, canvas: Canvas) {
        val paint: Paint = Paint()
        paint.color = node.color
        canvas.drawCircle(node.x, node.y, 100f, paint)
    }

    private fun drawLink(link: Link, canvas: Canvas) {
        val paint: Paint = Paint()
        paint.color = link.color
        paint.strokeWidth = 2f
        canvas.drawLine(link.start.x, link.start.y, link.end.x, link.end.y, paint)
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

}