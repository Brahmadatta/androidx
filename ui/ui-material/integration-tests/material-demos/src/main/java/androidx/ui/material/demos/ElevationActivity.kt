/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.ui.material.demos

import androidx.compose.Composable
import androidx.ui.core.Dp
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutExpanded
import androidx.ui.layout.LayoutExpandedWidth
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Card

class ElevationActivity : MaterialDemoActivity() {

    @Composable
    override fun materialContent() {
        Column {
            Container(LayoutPadding(20.dp) + LayoutExpandedWidth) {
                val text = getMessage(MaterialTheme.colors().isLight)
                Text(text)
            }
            VerticalScroller {
                Column(LayoutPadding(25.dp) + LayoutExpanded) {
                    elevations.forEach { elevation ->
                        ElevatedCard(elevation)
                    }
                }
            }
        }
    }
}

@Composable
private fun ElevatedCard(elevation: Dp) {
    val border = if (elevation == 0.dp) Border(Color.Gray, 1.dp) else null
    Card(
        LayoutPadding(left = 10.dp, right = 10.dp, top = 20.dp, bottom = 20.dp),
        shape = RoundedCornerShape(4.dp),
        border = border,
        elevation = elevation
    ) {
        Ripple(bounded = true) {
            Clickable({}) {
                Container(LayoutExpandedWidth, height = 150.dp) {
                    Text("$elevation", style = MaterialTheme.typography().h4)
                }
            }
        }
    }
}

private val elevations = listOf(
    0.dp,
    1.dp,
    2.dp,
    3.dp,
    4.dp,
    6.dp,
    8.dp,
    12.dp,
    16.dp,
    24.dp
)

private fun getMessage(isLight: Boolean) = (if (isLight) {
    "In a light theme elevation is represented by shadows"
} else {
    "In a dark theme elevation is represented by shadows and a translucent white overlay " +
            "applied to the surface"
}) + "\n\nnote: drawing a small border around 0.dp elevation to make it visible where the card " +
        "edges end"