package ci.miage.mob.networkAS.models

class Node(var x: Float, var  y: Float, label: String, color: Int) : ViewItem(label = label, color = color) {

    companion object {
        val RADIUS = 20f
    }

    fun moveTo(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    fun isTouched(fingerPositionX: Float, fingerPositionY: Float) : Boolean {
        return fingerPositionX >= (x - RADIUS) && fingerPositionX <= (x + RADIUS) && fingerPositionY >= (y - RADIUS) && fingerPositionY <= (y + RADIUS)
    }

}