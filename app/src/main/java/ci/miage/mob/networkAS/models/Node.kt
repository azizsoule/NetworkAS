package ci.miage.mob.networkAS.models

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.GestureDetector
import android.view.MotionEvent
import ci.miage.mob.networkAS.views.ViewItem

class Node(var posX: Float, var  posY: Float, label: String, color: Int, context: Context?) : ViewItem(label = label, color = color,
    context = context) {

    companion object {
        const val RADIUS = 20f
    }

    private val paint: Paint = Paint()

    fun moveTo(x: Float, y: Float) {
        this.posX = x
        this.posY = y
    }

    fun isTouched(fingerPositionX: Float, fingerPositionY: Float) : Boolean {
        return fingerPositionX >= (posX - RADIUS) && fingerPositionX <= (posX + RADIUS) && fingerPositionY >= (posY - RADIUS) && fingerPositionY <= (posY + RADIUS)
    }

    override fun onDraw(canvas: Canvas?) {
        paint.color = this.color
        canvas?.drawCircle(posX, posY, RADIUS, paint)

        paint.textSize = 24f
        canvas?.drawText(label, posX - (RADIUS + 5), posY - (RADIUS + 5), paint)
    }

}