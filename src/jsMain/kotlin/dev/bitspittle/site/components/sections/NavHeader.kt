package dev.bitspittle.site.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.icons.fa.FaTwitter
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.ComponentVariant
import com.varabyte.kobweb.silk.components.style.base
import com.varabyte.kobweb.silk.components.style.link
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.style.visited
import com.varabyte.kobweb.silk.theme.shapes.Circle
import com.varabyte.kobweb.silk.theme.shapes.clip
import com.varabyte.kobweb.silk.theme.toSilkPalette
import dev.bitspittle.site.SitePalettes
import dev.bitspittle.site.components.widgets.button.ColorModeButton
import dev.bitspittle.site.components.widgets.button.IconButton
import org.jetbrains.compose.web.css.*

val NavHeaderStyle = ComponentStyle.base("bs-nav-header") {
    Modifier
        .fillMaxWidth()
        .padding(left = 1.cssRem, right = 1.cssRem, top = 1.cssRem, bottom = 3.cssRem)
        .fontSize(1.25.cssRem)
}

val NavLinkStyle = ComponentStyle("bs-nav-link") {
    val linkColor = colorMode.toSilkPalette().color

    base { Modifier.margin(topBottom = 0.px, leftRight = 15.px) }

    link { Modifier.color(linkColor) }
    visited { Modifier.color(linkColor) }
}

val LogoVariant = NavLinkStyle.addVariant("logo") {
    val logoColor = SitePalettes[colorMode].brand

    link { Modifier.color(logoColor) }
    visited { Modifier.color(logoColor) }
}

val NavButtonStyle = ComponentStyle.base("bs-nav-button") {
    Modifier.margin(0.px, 10.px)
}

@Composable
private fun NavLink(path: String, text: String, linkVariant: ComponentVariant? = null) {
    Link(
        path,
        text,
        NavLinkStyle.toModifier(linkVariant),
        UndecoratedLinkVariant,
    )
}

@Composable
fun NavHeader() {
    val ctx = rememberPageContext()
    Row(NavHeaderStyle.toModifier()) {
        NavLink("/", "\$bs", LogoVariant)
        NavLink("/blog/", "blog")
        Spacer()
        IconButton(
            onClick = { ctx.router.navigateTo("https://twitter.com/bitspittle") },
            NavButtonStyle.toModifier()
        ) {
            FaTwitter()
        }
        ColorModeButton(NavButtonStyle.toModifier())
    }
}