# Gradient

## Installation

Backpack Android is available through [Maven Central](https://search.maven.org/artifact/net.skyscanner.backpack/backpack-android). Check the main [Readme](../../../README.md#installation) for a complete installation guide.

## Usage

The Gradient drawable can only be used in Kotlin/Java:


```Kotlin
import net.skyscanner.backpack.gradient.BpkGradients

view.background = BpkGradients(
  context,
  GradientDrawable.Orientation.BL_TR,
  intArrayOf(
    context.getColor(R.color.bpkMonteverde),
    context.getColor(R.color.bpkSagano))
  )
```

The default direction is `GradientDrawable.Orientation.TL_BR` and the default colours are Backpack's primary colours.

### Primary gradient

For primary gradients use the `getPrimary` function:

```Kotlin
import net.skyscanner.backpack.gradient.BpkGradients

view.background = BpkGradients.getPrimary(context)
```

The gradient returned by this function can be themed through the following theme props:

- `bpkPrimaryGradientColorStart`
- `bpkPrimaryGradientColorEnd`
