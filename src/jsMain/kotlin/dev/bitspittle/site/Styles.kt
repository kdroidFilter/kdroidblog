package dev.bitspittle.site

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.InitSilk
import com.varabyte.kobweb.silk.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.SilkPalette
import com.varabyte.kobweb.silk.theme.colors.SilkPalettes
import com.varabyte.kobweb.silk.theme.registerBaseStyle
import kotlinx.browser.localStorage
import org.jetbrains.compose.web.css.*

const val COLOR_MODE_KEY = "bitspittledev:app:colorMode"

@InitSilk
fun initSilk(ctx: InitSilkContext) {
    ctx.apply {
        config.apply {
            initialColorMode = localStorage.getItem(COLOR_MODE_KEY)?.let { ColorMode.valueOf(it) } ?: ColorMode.DARK

            registerBaseStyle("body") { Modifier.fontFamily("Ubuntu").lineHeight(1.5) }
            registerBaseStyle("code") { Modifier.fontFamily("Ubuntu Mono") }

            registerBaseStyle("p") { Modifier.margin(bottom = 1.5.cssRem) }
            registerBaseStyle("h1") { Modifier.fontSize(2.5.cssRem) }
            registerBaseStyle("h2") { Modifier.fontSize(2.cssRem) }
            registerBaseStyle("h3") { Modifier.fontSize(1.5.cssRem) }
        }

        theme.palettes = SilkPalettes(
            light = ctx.theme.palettes.light.copy(
                color = Colors.CornflowerBlue.darkened(),
                background = Colors.WhiteSmoke,
                link = SilkPalette.Link(
                    default = Colors.DarkTurquoise,
                    visited = Colors.MediumOrchid,
                ),
                button = ctx.theme.palettes.light.button.copy(default = Colors.WhiteSmoke)
            ),
            dark = ctx.theme.palettes.dark.copy(
                color = Colors.CornflowerBlue,
                link = SilkPalette.Link(
                    default = Colors.Cyan,
                    visited = Colors.Thistle,
                )
            )
        )
    }
}


class SitePalette(
    val brand: Color
)

object SitePalettes {
    private val sitePalettes = mapOf(
        ColorMode.LIGHT to SitePalette(brand = Color.rgb(0x009900)),
        ColorMode.DARK to SitePalette(brand = Color.rgb(0x04f904)),
    )

    operator fun get(colorMode: ColorMode) = sitePalettes.getValue(colorMode)
}