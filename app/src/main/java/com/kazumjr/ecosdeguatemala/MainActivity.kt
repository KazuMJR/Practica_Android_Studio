package com.kazumjr.ecosdeguatemala

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import com.kazumjr.ecosdeguatemala.ui.HeroIllustrationView
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

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val content = vertical().apply {
            setPadding(dp(20), dp(20), dp(20), dp(28))

            addView(text("ECOS DE GUATEMALA", 12f, Palette.TERRACOTTA, true))
            addView(text("Historias que aún\nsusurran los caminos", 31f, Palette.FOREST, true).apply {
                setPadding(0, dp(8), 0, dp(16))
            })

            addView(HeroIllustrationView(this@MainActivity).apply {
                background = rounded(Palette.FOREST, 24)
                clipToOutline = true
            }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dp(230)))

            addView(text("Descubre relatos nacidos entre volcanes, calles antiguas y noches de luna.", 16f, Palette.MUTED).apply {
                setLineSpacing(0f, 1.15f)
                setPadding(0, dp(18), 0, dp(16))
            })

            addView(primaryButton("Explorar las leyendas  →") {
                open(LegendsActivity::class.java)
            }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dp(54)))

            addView(text("LEYENDA DESTACADA", 11f, Palette.MUTED, true).apply {
                setPadding(0, dp(28), 0, dp(10))
            })

            val featured = vertical().apply {
                addView(text("La Tatuana", 22f, Palette.INK, true))
                addView(text("Antigua Guatemala  •  4 min", 13f, Palette.TERRACOTTA, true).apply {
                    setPadding(0, dp(6), 0, dp(12))
                })
                addView(text("Una historia de libertad, misterio y un barco dibujado con carbón.", 15f, Palette.MUTED).apply {
                    setLineSpacing(0f, 1.15f)
                })
                addView(secondaryButton("Leer la historia") {
                    open(DetailActivity::class.java) { putExtra(DetailActivity.EXTRA_LEGEND_ID, 0) }
                }.apply {
                    gravity = Gravity.CENTER
                }, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dp(50)).apply {
                    topMargin = dp(16)
                })
            }
            addView(card(featured))
        }

        setScreen(content, selected = 0)
    }
}
