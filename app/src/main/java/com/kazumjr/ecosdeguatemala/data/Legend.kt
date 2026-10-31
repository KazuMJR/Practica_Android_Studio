package com.kazumjr.ecosdeguatemala.data

data class Legend(
    val id: Int,
    val title: String,
    val subtitle: String,
    val place: String,
    val time: String,
    val accent: String,
    val symbol: String,
    val summary: String,
    val story: String,
    val curiosity: String
)

object LegendRepository {
    val legends = listOf(
        Legend(
            id = 0,
            title = "La Tatuana",
            subtitle = "La mujer que escapó dibujando un barco",
            place = "Antigua Guatemala",
            time = "4 min",
            accent = "#B75D45",
            symbol = "T",
            summary = "Una leyenda de libertad, misterio y un barco trazado con carbón.",
            story = "Cuenta la tradición que una mujer de belleza extraordinaria fue acusada de brujería y encerrada en una celda. La noche antes de su condena pidió un trozo de carbón y dibujó un pequeño barco sobre la pared. Cuando los guardias regresaron al amanecer, la celda estaba vacía: únicamente quedaba el dibujo, navegando hacia un horizonte imposible. Desde entonces, La Tatuana representa el ingenio y la libertad frente a un destino impuesto.",
            curiosity = "Miguel Ángel Asturias retomó esta leyenda en sus relatos, mezclando la tradición oral con una mirada literaria propia."
        ),
        Legend(
            id = 1,
            title = "El Sombrerón",
            subtitle = "Serenatas que hechizan bajo la luna",
            place = "Ciudad de Guatemala",
            time = "3 min",
            accent = "#486B65",
            symbol = "S",
            summary = "Un pequeño personaje de gran sombrero que encanta con su guitarra.",
            story = "En las noches tranquilas aparece un hombre pequeño, vestido de negro y cubierto por un sombrero enorme. Toca la guitarra frente a la ventana de jóvenes de cabello largo y les deja pequeñas trenzas imposibles de deshacer. Quien escucha su música pierde el apetito y sueña con la melodía noche tras noche. Las familias, preocupadas, intentan alejarlo cortando el cabello de la joven o mudándose lejos de su camino.",
            curiosity = "Según distintas versiones, también trenza las crines de los caballos y llega acompañado por cuatro mulas."
        ),
        Legend(
            id = 2,
            title = "El Cadejo",
            subtitle = "Dos guardianes para el camino nocturno",
            place = "Altiplano guatemalteco",
            time = "5 min",
            accent = "#9A7A45",
            symbol = "C",
            summary = "Un cadejo blanco protege; uno negro confunde a quien camina de noche.",
            story = "Los viajeros cuentan que, al regresar tarde por caminos solitarios, pueden escuchar pasos detrás de ellos. El Cadejo Blanco aparece como guardián silencioso y acompaña a la persona hasta que llega a salvo. El Cadejo Negro, en cambio, busca desorientarla y conducirla lejos de casa. Ambos tienen forma de perro grande, ojos encendidos y cadenas que suenan sin que nadie pueda verlas.",
            curiosity = "La historia cambia en cada región de Centroamérica, pero casi siempre conserva la dualidad entre protección y peligro."
        )
    )

    fun byId(id: Int): Legend = legends.getOrElse(id) { legends.first() }
}
