package de.chrisward.theyworkforyou.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleView : View {
    var circleColor = Color.WHITE
        set(color) {
            field = color
            invalidate()
        }
    private var paint: Paint? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        paint = Paint()
        paint!!.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width
        val height = height

        val paddingLeft = paddingLeft
        val paddingRight = paddingRight
        val paddingTop = paddingTop
        val paddingBottom = paddingBottom

        val contentWidth = width - (paddingLeft + paddingRight)
        val contentHeight = height - (paddingTop + paddingBottom)

        val radius = Math.min(contentWidth, contentHeight) / 2
        val cx = paddingLeft + contentWidth / 2
        val cy = paddingTop + contentHeight / 2

        paint!!.color = this.circleColor
        canvas.drawCircle(cx.toFloat(), cy.toFloat(), radius.toFloat(), paint!!)
    }
}
