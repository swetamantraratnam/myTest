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

package net.skyscanner.backpack.button.internal

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import net.skyscanner.backpack.R
import net.skyscanner.backpack.util.colorStateList
import net.skyscanner.backpack.util.rippleDrawable
import net.skyscanner.backpack.util.stateListDrawable

internal class ButtonStyle(
  private val context: Context,
  @ColorInt private val bgColor: Int,
  @ColorInt private val bgPressedColor: Int,
  @ColorInt private val bgDisabledColor: Int,
  @ColorInt private val contentColor: Int,
  @ColorInt private val contentPressedColor: Int,
  @ColorInt private val contentDisabledColor: Int,
  @ColorInt private val rippleColor: Int,
) {

  fun getContentColor(): ColorStateList =
    colorStateList(
      color = contentColor,
      pressedColor = contentPressedColor,
      disabledColor = contentDisabledColor,
    )

  fun getButtonBackground(enabled: Boolean): Drawable {

    val radius = context.resources.getDimension(R.dimen.bpkBorderRadiusSm)

    fun roundRectDrawable(
      @ColorInt color: Int,
    ): Drawable = GradientDrawable().apply {
      setColor(color)
      cornerRadius = radius
    }

    if (enabled) {
      return rippleDrawable(
        context = context,
        mask = roundRectDrawable(Color.WHITE),
        content = stateListDrawable(
          pressed = roundRectDrawable(bgPressedColor),
          drawable = roundRectDrawable(bgColor),
        ) {
          val strokeAnimation = context.resources.getInteger(R.integer.bpkAnimationDurationSm)
          setEnterFadeDuration(strokeAnimation)
          setExitFadeDuration(strokeAnimation)
        },
        rippleColor = rippleColor,
      )
    } else {
      return roundRectDrawable(bgDisabledColor)
    }
  }

  companion object {

    private fun fromTypedArray(
      context: Context,
      typedArray: TypedArray?,
      @ColorInt defaultBgColor: Int,
      @ColorInt defaultBgPressedColor: Int,
      @ColorInt defaultBgDisabledColor: Int,
      @ColorInt defaultContentColor: Int,
      @ColorInt defaultContentPressedColor: Int,
      @ColorInt defaultContentDisabledColor: Int,
      @ColorInt rippleColor: Int,
    ): ButtonStyle {
      var bgColor = defaultBgColor
      var contentColor = defaultContentColor

      typedArray?.let {
        bgColor = it.getColor(R.styleable.BpkButton_buttonBackgroundColor, bgColor)
        contentColor = it.getColor(R.styleable.BpkButton_buttonTextColor, contentColor)
      }

      return ButtonStyle(
        context = context,
        bgColor = bgColor,
        bgPressedColor = defaultBgPressedColor,
        bgDisabledColor = defaultBgDisabledColor,
        contentColor = contentColor,
        contentPressedColor = defaultContentPressedColor,
        contentDisabledColor = defaultContentDisabledColor,
        rippleColor = rippleColor,
      )
    }

    fun fromTheme(
      context: Context,
      @AttrRes style: Int,
      @ColorRes bgColorRes: Int,
      @ColorRes bgPressedColorRes: Int = bgColorRes,
      @ColorRes bgDisabledColorRes: Int = R.color.__buttonDisabledBackground,
      @ColorRes rippleColorRes: Int = R.color.__buttonDefaultRipple,
      @ColorRes contentColorRes: Int,
      @ColorRes contentPressedColorRes: Int = contentColorRes,
      @ColorRes contentDisabledColorRes: Int = R.color.__buttonDisabledText,
    ): ButtonStyle {

      var typedArray: TypedArray? = null
      try {

        val tv = TypedValue()
        if (context.theme.resolveAttribute(style, tv, true)) {
          typedArray = context.obtainStyledAttributes(tv.resourceId, R.styleable.BpkButton)
        }

        return fromTypedArray(
          context = context,
          typedArray = typedArray,
          defaultBgColor = context.getColor(bgColorRes),
          defaultBgPressedColor = context.getColor(bgPressedColorRes),
          defaultBgDisabledColor = context.getColor(bgDisabledColorRes),
          defaultContentColor = context.getColor(contentColorRes),
          defaultContentPressedColor = context.getColor(contentPressedColorRes),
          defaultContentDisabledColor = context.getColor(contentDisabledColorRes),
          rippleColor = context.getColor(rippleColorRes),
        )
      } finally {
        typedArray?.recycle()
      }
    }

    fun fromAttributes(
      context: Context,
      typedArray: TypedArray?,
      fallback: ButtonStyle,
    ): ButtonStyle = fromTypedArray(
      context = context,
      typedArray = typedArray,
      defaultBgColor = fallback.bgColor,
      defaultBgPressedColor = fallback.bgPressedColor,
      defaultBgDisabledColor = fallback.bgDisabledColor,
      defaultContentColor = fallback.contentColor,
      defaultContentPressedColor = fallback.contentPressedColor,
      defaultContentDisabledColor = fallback.contentDisabledColor,
      rippleColor = fallback.rippleColor,
    )
  }
}
