package br.com.androidmaster.customview.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import androidx.core.content.withStyledAttributes
import br.com.androidmaster.customview.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


private const val RADIUS_OFFSET_LABEL = 60
private const val RADIUS_OFFSET_INDICATOR = -50

private enum class FanSpeed(val label: Int) {
    OFF(R.string.fan_off),
    LOW(R.string.fan_low),
    MEDIUM(R.string.fan_medium),
    HIGH(R.string.fan_high);

    fun next() = when (this){
        OFF -> LOW
        LOW -> MEDIUM
        MEDIUM -> HIGH
        HIGH -> OFF
    }
}

class DialView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // Radius of the circle.
    private var radius = 0.0f

    // The active selection.
    private var fanSpeed = FanSpeed.OFF

    // position variable which will be used to draw label and indicator circle position
    private val pointPosition: PointF = PointF(0.0f, 0.0f)

    // Colors for speeds
    private var fanSpeedLowColor = 0;
    private var fanSpeedMediumColor = 0;
    private var fanSpeedHighColor = 0;

    private var initialized = false


    // Gesture detector
    private val mListener = object : GestureDetector.SimpleOnGestureListener(){
        override fun onDown(e: MotionEvent?): Boolean {
            Log.d("AGR", "x: ${e?.x}\ny: ${e?.y}")
            Log.d("AGR", "height: $height\nwidth: $width")
            Log.d("AGR", "radius: $radius")
            return true
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            Log.i("AGR", "onScroll: ")
            val radiusIndicator = radius + RADIUS_OFFSET_INDICATOR
            // x = center + radius * cos(event.x)
            // y = center + radius * sin(event.x)
            val centerX = (width / 2).toFloat()
            val centerY = (height / 2).toFloat()

            val startX = centerX - radius

            val topX = centerX

            val endX = centerX + radius

            val myX =  (e2!!.x/160 + pointPosition.x).toFloat()

            pointPosition.apply {
                x = centerX + radiusIndicator * cos(myX)
                y = centerY + radiusIndicator * sin(myX)
            }
            if(myX in startX..endX){
                invalidate()
            }
            return super.onScroll(e1, e2, distanceX, distanceY)
        }
    }

    private val detector: GestureDetector = GestureDetector(context, mListener)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create("sans-serif", Typeface.BOLD)
    }


    init {
        isClickable = true
        context.withStyledAttributes(attrs,R.styleable.DialView){
            fanSpeedLowColor = getColor(R.styleable.DialView_fanColor1, 0)
            fanSpeedMediumColor = getColor(R.styleable.DialView_fanColor2, 0)
            fanSpeedHighColor = getColor(R.styleable.DialView_fanColor3, 0)
        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return detector.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        if(super.performClick()) return true

        fanSpeed = fanSpeed.next()
        contentDescription = resources.getString(fanSpeed.label)

        invalidate()
        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radius = (min(w, h) / 2.0 * 0.7).toFloat()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Set dial background color to green if selection not off.
        paint.color = when(fanSpeed){
            FanSpeed.OFF -> Color.GRAY
            FanSpeed.LOW -> fanSpeedLowColor
            FanSpeed.MEDIUM -> fanSpeedMediumColor
            FanSpeed.HIGH -> fanSpeedHighColor
        }
        // Draw the dial.
        val centerX = (width / 2).toFloat()
        val centerY = (height / 2).toFloat()
        canvas.drawCircle(centerX, centerY, radius, paint)


        // DRAW TEST
        paint.color = Color.RED
        canvas.drawCircle(centerX - radius, centerY, 20.0f,paint)
        canvas.drawCircle(centerX + radius, centerY, 20.0f,paint)
        canvas.drawCircle(centerX, centerY - radius, 20.0f,paint)
        canvas.drawCircle(centerX, centerY, 20.0f,paint)

        // Draw the indicator
        val indicatorRadius = radius + RADIUS_OFFSET_INDICATOR
        if(!initialized){
            pointPosition.computeXYForSpeed(fanSpeed, indicatorRadius)
        }
        paint.color = Color.BLACK

        canvas.drawCircle(pointPosition.x, pointPosition.y, radius / 15, paint)

        // Draw the texts
        val labelRadius = radius + RADIUS_OFFSET_LABEL
        for(i in FanSpeed.values()){
            pointPosition.computeXYForSpeed(i, labelRadius)
            val label = resources.getString(i.label)
            canvas.drawText(label, pointPosition.x, pointPosition.y, paint)
        }
    }

    private fun PointF.computeXYForSpeed(pos: FanSpeed, radius: Float){
        val startAngle = PI
        val angle = startAngle + pos.ordinal * (PI / 4)
        x = (radius * Math.cos(angle)).toFloat() + width / 2
        y = (radius * Math.sin(angle)).toFloat() + height / 2

        initialized=true
    }
}
