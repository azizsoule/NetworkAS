package ci.miage.mob.networkAS.models

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import ci.miage.mob.networkAS.views.ViewItem

class Link(var start : Node, var end : Node, label : String, color : Int, context: Context?) : ViewItem(label, color,
    context
) {

    override fun onDraw(canvas: Canvas?) {
        val paint: Paint = Paint()
        val path : Path = Path()

        path.moveTo(start.posX, start.posY)
        path.lineTo(end.posX, end.posY)
        path.close()

        val pathLength : Float = PathMeasure(path, true).length

        paint.color = color
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 2f
        canvas?.drawPath(path, paint)

        paint.textSize = 24f
        paint.strokeWidth = 0f
        canvas?.drawText(label, (start.posX + end.posX)/2, (start.posY + end.posY)/2, paint)
    }

    fun isBetween(node1 : Node, node2 : Node) : Boolean {
        return (this.start == node1 && this.end == node2) || (this.start == node2 && this.end == node1)
    }

}