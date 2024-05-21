package com.clipevery.ui.clip

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.clipevery.dao.clip.ClipType
import com.clipevery.ui.base.file
import com.clipevery.ui.base.html
import com.clipevery.ui.base.image
import com.clipevery.ui.base.link
import com.clipevery.ui.base.question
import com.clipevery.ui.base.text

@Composable
fun ClipTypeIconBaseView(clipType: Int): Painter {
    return when (clipType) {
        ClipType.TEXT -> {
            text()
        }
        ClipType.URL -> {
            link()
        }
        ClipType.HTML -> {
            html()
        }
        ClipType.IMAGE -> {
            image()
        }
        ClipType.FILE -> {
            file()
        }
        else -> {
            question()
        }
    }
}