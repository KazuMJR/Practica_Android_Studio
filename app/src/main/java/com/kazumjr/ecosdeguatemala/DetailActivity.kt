package com.kazumjr.ecosdeguatemala

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.kazumjr.ecosdeguatemala.data.LegendRepository
import com.kazumjr.ecosdeguatemala.ui.Palette
import com.kazumjr.ecosdeguatemala.ui.card
import com.kazumjr.ecosdeguatemala.ui.dp
import com.kazumjr.ecosdeguatemala.ui.open
import com.kazumjr.ecosdeguatemala.ui.primaryButton
import com.kazumjr.ecosdeguatemala.ui.rounded
import com.kazumjr.ecosdeguatemala.ui.secondaryButton
import com.kazumjr.ecosdeguatemala.ui.setScreen
import com.kazumjr.ecosdeguatemala.ui.text
import com.kazumjr.ecosdeguatemala.ui.vertical

class DetailActivity : Activity() {
    companion object {
        const val EXTRA_LEGEND_ID = "legend_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra(EXTRA_LEGEND_ID, 0)
        val legend = LegendRepository.byId(id)

        val content = vertical().apply {
            setPadding(dp(20), dp(20), dp(20), dp(30))

            val top = LinearLayout(this@DetailActivity).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
                addView(secondaryButton("‹  Volver") { finish() }, LinearLayout.LayoutParams(dp(112), dp(46)))
                addView(text("RELATO ${legend.id + 1} DE ${LegendRepository.legends.size}", 11f, Palette.MUTED, true).apply {
                    gravity = Gravity.END or Gravity.CENTER_VERTICAL
                }, LinearLayout.LayoutParams(0, dp(46), 1f))
            }
            addView(top)

            val symbol = TextView(this@DetailActivity).apply {
                text = legend.symbol
                gravity = Gravity.CENTER
                textSize = 46f
                setTextColor(Color.WHITE)
                background = rounded(legend.accent, 28)
            }
            addView(symbol, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dp(150)).apply {
                topMargin = dp(20)
                bottomMargin = dp(20)
            })

            addView(text(legend.title, 32f, Palette.FOREST, true))
            addView(text(legend.subtitle, 17f, legend.accent, true).apply {
                setPadding(0, dp(6), 0, dp(10))
            })
            addView(text("${legend.place}  •  Lectura de ${legend.time}", 13f, Palette.MUTED).apply {
                setPadding(0, 0, 0, dp(20))
            })

            addView(text(legend.story, 16f, Palette.INK).apply {
                setLineSpacing(dp(5).toFloat(), 1f)
            })

            val curiosity = vertical().apply {
                addView(text("¿SABÍAS QUE…?", 11f, Palette.TERRACOTTA, true))
                addView(text(legend.curiosity, 14f, Palette.FOREST).apply {
                    setLineSpacing(0f, 1.15f)
                    setPadding(0, dp(8), 0, 0)
                })
            }
            addView(card(curiosity, 17).apply {
                background = rounded(Palette.SAGE, 18)
            }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                topMargin = dp(22)
                bottomMargin = dp(18)
            })

            addView(primaryButton("☆  Guardar como favorita") {
                Toast.makeText(this@DetailActivity, "${legend.title} guardada en favoritas", Toast.LENGTH_SHORT).show()
            }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dp(54)))

            addView(secondaryButton("Ver todas las leyendas") {
                open(LegendsActivity::class.java)
            }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dp(52)).apply {
                topMargin = dp(10)
            })
        }

        setScreen(content, selected = 2)
    }
}
