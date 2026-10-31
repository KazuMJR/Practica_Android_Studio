package com.kazumjr.ecosdeguatemala.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.WindowInsets
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.kazumjr.ecosdeguatemala.DetailActivity
import com.kazumjr.ecosdeguatemala.LegendsActivity
import com.kazumjr.ecosdeguatemala.MainActivity

object Palette {
    const val FOREST = "#243B35"
    const val CREAM = "#F6F1E8"
    const val PAPER = "#FFFCF7"
    const val INK = "#24302C"
    const val MUTED = "#68736E"
    const val OCHRE = "#E9A23B"
    const val TERRACOTTA = "#B75D45"
    const val SAGE = "#DCE4D7"
    const val LINE = "#E7DED0"
}

fun Activity.dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()

fun Activity.text(
    value: String,
    size: Float,
    color: String = Palette.INK,
    bold: Boolean = false
): TextView = TextView(this).apply {
    text = value
    textSize = size
    setTextColor(Color.parseColor(color))
    typeface = Typeface.create("sans", if (bold) Typeface.BOLD else Typeface.NORMAL)
    includeFontPadding = false
}

fun Activity.rounded(color: String, radius: Int = 18, strokeColor: String? = null): GradientDrawable =
    GradientDrawable().apply {
        setColor(Color.parseColor(color))
        cornerRadius = dp(radius).toFloat()
        strokeColor?.let { setStroke(dp(1), Color.parseColor(it)) }
    }

fun Activity.primaryButton(label: String, action: () -> Unit): Button = Button(this).apply {
    text = label
    textSize = 15f
    setTextColor(Color.WHITE)
    typeface = Typeface.DEFAULT_BOLD
    setAllCaps(false)
    background = rounded(Palette.TERRACOTTA, 16)
    stateListAnimator = null
    setPadding(dp(18), dp(8), dp(18), dp(8))
    setOnClickListener { action() }
}

fun Activity.secondaryButton(label: String, action: () -> Unit): Button = Button(this).apply {
    text = label
    textSize = 14f
    setTextColor(Color.parseColor(Palette.FOREST))
    typeface = Typeface.DEFAULT_BOLD
    setAllCaps(false)
    background = rounded(Palette.PAPER, 16, Palette.LINE)
    stateListAnimator = null
    setPadding(dp(16), dp(7), dp(16), dp(7))
    setOnClickListener { action() }
}

fun Activity.vertical(spacing: Int = 0): LinearLayout = LinearLayout(this).apply {
    orientation = LinearLayout.VERTICAL
    if (spacing > 0) showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
}

fun View.margin(left: Int, top: Int, right: Int, bottom: Int): View {
    layoutParams = LinearLayout.LayoutParams(
        layoutParams?.width ?: LinearLayout.LayoutParams.MATCH_PARENT,
        layoutParams?.height ?: LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply { setMargins(left, top, right, bottom) }
    return this
}

fun Activity.card(content: View, padding: Int = 18): FrameLayout = FrameLayout(this).apply {
    background = rounded(Palette.PAPER, 20, Palette.LINE)
    elevation = dp(2).toFloat()
    setPadding(dp(padding), dp(padding), dp(padding), dp(padding))
    addView(content)
}

fun Activity.open(activityClass: Class<out Activity>, extras: (Intent.() -> Unit)? = null) {
    if (this::class.java == activityClass && extras == null) return
    startActivity(Intent(this, activityClass).apply { extras?.invoke(this) })
}

fun Activity.setScreen(content: View, selected: Int) {
    window.statusBarColor = Color.parseColor(Palette.FOREST)
    window.navigationBarColor = Color.parseColor(Palette.FOREST)

    val root = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        setBackgroundColor(Color.parseColor(Palette.CREAM))
    }

    val scroll = ScrollView(this).apply {
        isFillViewport = true
        clipToPadding = false
        addView(content)
    }
    root.addView(scroll, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f))
    root.addView(createNavigation(selected))

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.setDecorFitsSystemWindows(false)
        root.setOnApplyWindowInsetsListener { view, insets ->
            val bars = insets.getInsets(WindowInsets.Type.systemBars())
            view.setPadding(0, bars.top, 0, bars.bottom)
            insets
        }
    }
    setContentView(root)
}

private fun Activity.createNavigation(selected: Int): LinearLayout {
    val items = listOf(
        Triple("⌂", "Inicio", MainActivity::class.java),
        Triple("◇", "Explorar", LegendsActivity::class.java),
        Triple("☆", "Detalle", DetailActivity::class.java)
    )
    return LinearLayout(this).apply {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER
        setPadding(dp(10), dp(7), dp(10), dp(7))
        setBackgroundColor(Color.parseColor(Palette.FOREST))
        items.forEachIndexed { index, item ->
            val label = TextView(this@createNavigation).apply {
                text = "${item.first}\n${item.second}"
                gravity = Gravity.CENTER
                textSize = 12f
                setTextColor(Color.parseColor(if (index == selected) Palette.OCHRE else "#DDE5DF"))
                typeface = Typeface.create("sans", if (index == selected) Typeface.BOLD else Typeface.NORMAL)
                setPadding(dp(8), dp(3), dp(8), dp(3))
                setOnClickListener {
                    if (index != selected) open(item.third)
                }
            }
            addView(label, LinearLayout.LayoutParams(0, dp(50), 1f))
        }
    }
}
