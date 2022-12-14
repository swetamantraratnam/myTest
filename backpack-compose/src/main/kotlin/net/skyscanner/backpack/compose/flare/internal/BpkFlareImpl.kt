/**
 * Backpack for Android - Skyscanner's Design System
 *
 * Copyright 2018 Skyscanner Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.skyscanner.backpack.compose.flare.internal

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import net.skyscanner.backpack.compose.flare.BpkFlarePointerDirection
import net.skyscanner.backpack.compose.flare.BpkFlareRadius
import net.skyscanner.backpack.compose.tokens.BpkDimension

internal class FlareShape(
  private val radius: BpkFlareRadius,
  private val pointerDirection: BpkFlarePointerDirection,
) : Shape {

  override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline =
    Outline.Generic(
      path = Path().apply {
        val flareHeight = with(density) { FlareHeight.toPx() }
        var scale = flareHeight / FlareVectorHeight
        if (pointerDirection == BpkFlarePointerDirection.Up) {
          scale *= -1
        }
        addRect(flareHeight, size, density, radius, pointerDirection)
        addFlare(flareHeight, scale, size, pointerDirection)
      }
    )
}

private fun Path.addRect(
  flareHeight: Float,
  size: Size,
  density: Density,
  radius: BpkFlareRadius,
  pointerDirection: BpkFlarePointerDirection,
) {
  val borderRect = when (pointerDirection) {
    BpkFlarePointerDirection.Up -> Rect(0f, flareHeight, size.width, size.height + RectOffset)
    BpkFlarePointerDirection.Down -> Rect(0f, -RectOffset, size.width, size.height - flareHeight)
  }
  when (radius) {
    BpkFlareRadius.None -> addRect(borderRect)
    BpkFlareRadius.Medium -> {
      val cornerRadius = with(density) { BpkDimension.BorderRadius.Md.toPx() }
      addRoundRect(RoundRect(borderRect, CornerRadius(cornerRadius, cornerRadius)))
    }
  }
}

private fun Path.addFlare(
  flareHeight: Float,
  scale: Float,
  size: Size,
  pointerDirection: BpkFlarePointerDirection,
) {
  val flareWidth = scale * FlareVectorWidth
  val flareX = (size.width - flareWidth) / 2
  val flareY = when (pointerDirection) {
    BpkFlarePointerDirection.Up -> flareHeight
    BpkFlarePointerDirection.Down -> size.height - flareHeight
  }
  moveTo(flareX + 4.329f * scale, flareY)
  cubicTo(
    flareX + 14.663905f * scale, flareY + 0.409248008f * scale,
    flareX + 24.743092f * scale, flareY + 3.33270635f * scale,
    flareX + 33.693f * scale, flareY + 8.517f * scale,
  )
  lineTo(flareX + 101.736f * scale, flareY + 47.858f * scale)
  cubicTo(
    flareX + 112.368f * scale, flareY + 54.043f * scale,
    flareX + 125.531f * scale, flareY + 54.043f * scale,
    flareX + 136.264f * scale, flareY + 47.858f * scale,
  )
  lineTo(flareX + 204.408f * scale, flareY + 8.518f * scale)
  cubicTo(
    flareX + 213.318f * scale, flareY + 3.345f * scale,
    flareX + 223.396f * scale, flareY + 0.303f * scale,
    flareX + 233.724f * scale, flareY + 0f * scale,
  )
  lineTo(flareX + 238f * scale, flareY + 0f * scale)
}

internal fun FlareRectShape(radius: BpkFlareRadius): Shape = when (radius) {
  BpkFlareRadius.None -> RectangleShape
  BpkFlareRadius.Medium -> RoundedCornerShape(BpkDimension.BorderRadius.Md)
}

internal fun FlareContentPadding(insetContent: Boolean = false) =
  when (insetContent) {
    true -> FlareHeight
    false -> 0.dp
  }

private const val FlareVectorHeight = 53
private const val FlareVectorWidth = 234
private val FlareHeight = 11.dp
private const val RectOffset = 10f // this offset exists to improve anti-aliasing on < sdk 30. remove when dropping support
