package com.kazumjr.ecosdeguatemala.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import android.view.View

class HeroIllustrationView(context: Context) : View(context) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val w = width.toFloat()
        val h = height.toFloat()

        paint.shader = LinearGradient(0f, 0f, 0f, h, Color.rgb(36, 59, 53), Color.rgb(91, 91, 67), Shader.TileMode.CLAMP)
        canvas.drawRect(0f, 0f, w, h, paint)
        paint.shader = null

        paint.color = Color.rgb(244, 190, 92)
        canvas.drawCircle(w * .78f, h * .23f, h * .105f, paint)
        paint.color = Color.argb(80, 255, 228, 170)
        canvas.drawCircle(w * .78f, h * .23f, h * .15f, paint)

        paint.color = Color.rgb(255, 244, 214)
        listOf(.10f to .20f, .22f to .12f, .38f to .25f, .57f to .14f, .90f to .31f).forEach { (x, y) ->
            canvas.drawCircle(w * x, h * y, 2.2f, paint)
        }

        val farMountains = Path().apply {
            moveTo(0f, h * .65f)
            lineTo(w * .19f, h * .36f)
            lineTo(w * .31f, h * .57f)
            lineTo(w * .53f, h * .27f)
            lineTo(w * .77f, h * .62f)
            lineTo(w, h * .42f)
            lineTo(w, h)
            lineTo(0f, h)
            close()
        }
        paint.color = Color.rgb(72, 89, 73)
        canvas.drawPath(farMountains, paint)

        val nearMountains = Path().apply {
            moveTo(0f, h * .72f)
            lineTo(w * .28f, h * .47f)
            lineTo(w * .46f, h * .67f)
            lineTo(w * .67f, h * .46f)
            lineTo(w, h * .70f)
            lineTo(w, h)
            lineTo(0f, h)
            close()
        }
        paint.color = Color.rgb(38, 62, 52)
        canvas.drawPath(nearMountains, paint)

        paint.color = Color.rgb(237, 216, 169)
        canvas.drawRect(w * .16f, h * .67f, w * .30f, h * .83f, paint)
        paint.color = Color.rgb(183, 93, 69)
        val roof = Path().apply {
            moveTo(w * .13f, h * .68f)
            lineTo(w * .23f, h * .57f)
            lineTo(w * .33f, h * .68f)
            close()
        }
        canvas.drawPath(roof, paint)
        paint.color = Color.rgb(233, 162, 59)
        canvas.drawRect(w * .21f, h * .72f, w * .25f, h * .83f, paint)

        paint.color = Color.rgb(26, 47, 41)
        for (x in listOf(.05f, .38f, .86f, .94f)) {
            val tree = Path().apply {
                moveTo(w * x, h * .56f)
                lineTo(w * (x - .055f), h * .82f)
                lineTo(w * (x + .055f), h * .82f)
                close()
            }
            canvas.drawPath(tree, paint)
        }

        paint.color = Color.rgb(226, 188, 126)
        val road = Path().apply {
            moveTo(w * .23f, h * .83f)
            lineTo(w * .60f, h)
            lineTo(w * .36f, h)
            lineTo(w * .23f, h * .83f)
            close()
        }
        canvas.drawPath(road, paint)
    }
}
