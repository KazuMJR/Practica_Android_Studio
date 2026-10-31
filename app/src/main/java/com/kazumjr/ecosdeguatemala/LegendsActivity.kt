package com.kazumjr.ecosdeguatemala

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.kazumjr.ecosdeguatemala.data.LegendRepository
import com.kazumjr.ecosdeguatemala.ui.Palette
import com.kazumjr.ecosdeguatemala.ui.card
import com.kazumjr.ecosdeguatemala.ui.dp
import com.kazumjr.ecosdeguatemala.ui.open
import com.kazumjr.ecosdeguatemala.ui.rounded
import com.kazumjr.ecosdeguatemala.ui.setScreen
import com.kazumjr.ecosdeguatemala.ui.text
import com.kazumjr.ecosdeguatemala.ui.vertical

class LegendsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val content = vertical().apply {
            setPadding(dp(20), dp(22), dp(20), dp(28))
            addView(text("EXPLORA", 12f, Palette.TERRACOTTA, true))
            addView(text("Tres relatos,\ntres caminos", 30f, Palette.FOREST, true).apply {
                setPadding(0, dp(7), 0, dp(8))
            })
            addView(text("Elige una leyenda y adéntrate en su historia.", 15f, Palette.MUTED).apply {
                setPadding(0, 0, 0, dp(22))
            })

            LegendRepository.legends.forEach { legend ->
                val row = LinearLayout(this@LegendsActivity).apply {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_VERTICAL

                    val badge = TextView(this@LegendsActivity).apply {
                        text = legend.symbol
                        gravity = Gravity.CENTER
                        textSize = 25f
                        setTextColor(Color.WHITE)
                        typeface = Typeface.DEFAULT_BOLD
                        background = rounded(legend.accent, 18)
                    }
                    addView(badge, LinearLayout.LayoutParams(dp(68), dp(88)))

                    val copy = vertical().apply {
                        addView(text(legend.title, 20f, Palette.INK, true))
                        addView(text(legend.subtitle, 13f, Palette.MUTED).apply {
                            maxLines = 2
                            setPadding(0, dp(4), 0, dp(8))
                        })
                        addView(text("${legend.place}  •  ${legend.time}", 11f, legend.accent, true))
                    }
                    addView(copy, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f).apply {
                        marginStart = dp(16)
                    })
                    addView(text("›", 30f, legend.accent, true).apply {
                        gravity = Gravity.CENTER
                    }, LinearLayout.LayoutParams(dp(28), LinearLayout.LayoutParams.MATCH_PARENT))
                }

                addView(card(row, 14).apply {
                    isClickable = true
                    isFocusable = true
                    setOnClickListener {
                        open(DetailActivity::class.java) {
                            putExtra(DetailActivity.EXTRA_LEGEND_ID, legend.id)
                        }
                    }
                }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                    bottomMargin = dp(14)
                })
            }

            val note = text("Consejo de viajero  ·  Estas leyendas forman parte de la tradición oral y pueden tener distintas versiones según la región.", 13f, Palette.FOREST).apply {
                setLineSpacing(0f, 1.15f)
            }
            addView(card(note, 16).apply {
                background = rounded(Palette.SAGE, 18)
            }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                topMargin = dp(6)
            })
        }

        setScreen(content, selected = 1)
    }
}
