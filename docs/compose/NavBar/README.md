# Navigation Bar

## Installation

Backpack Compose is available
through [Maven Central](https://search.maven.org/artifact/net.skyscanner.backpack/backpack-compose). Check the
main [Readme](https://github.com/skyscanner/backpack-android#installation) for a complete installation guide.

## Usage

Example of a navigation bar with back navigation icon

```Kotlin
import net.skyscanner.backpack.compose.navigationbar.BpkTopNavBar
import net.skyscanner.backpack.compose.navigationbar.NavIcon

BpkTopNavBar(
  title = stringResource(R.string.navigation_bar_title),
  navIcon = NavIcon.Back(contentDescription = stringResource(R.string.navigation_back)) { /** onClick **/ },
)
```

Example of a navigation bar with close navigation icon

```Kotlin
import net.skyscanner.backpack.compose.navigationbar.BpkTopNavBar
import net.skyscanner.backpack.compose.navigationbar.NavIcon

BpkTopNavBar(
  title = stringResource(R.string.navigation_bar_title),
  navIcon = NavIcon.Close(contentDescription = stringResource(R.string.navigation_close)) { /** onClick **/ },
)
```

Example of a navigation bar with no navigation icon

```Kotlin
import net.skyscanner.backpack.compose.navigationbar.BpkTopNavBar
import net.skyscanner.backpack.compose.navigationbar.NavIcon

BpkTopNavBar(
  title = stringResource(R.string.navigation_bar_title),
  navIcon = NavIcon.None,
)
```

Example of a navigation bar with icon actions

```Kotlin
import net.skyscanner.backpack.compose.icon.BpkIcon
import net.skyscanner.backpack.compose.navigationbar.BpkTopNavBar
import net.skyscanner.backpack.compose.navigationbar.NavIcon
import net.skyscanner.backpack.compose.navigationbar.IconAction

BpkTopNavBar(
  title = stringResource(R.string.navigation_bar_title),
  navIcon = NavIcon.Back(contentDescription = stringResource(R.string.navigation_back)) { /** onClick **/ },
  actions = listOf(
    IconAction(icon = BpkIcon.AccountIdCard,
      contentDescription = stringResource(R.string.navigation_id_card)) { /** onClick **/ },
    IconAction(icon = BpkIcon.Accessibility,
      contentDescription = stringResource(R.string.navigation_accessibility)) { /** onClick **/ },
    IconAction(icon = BpkIcon.Account, contentDescription = stringResource(R.string.navigation_account)) { /** onClick **/ },
  ),
)
```

Example of a navigation bar with text action

```Kotlin
import net.skyscanner.backpack.compose.navigationbar.BpkTopNavBar
import net.skyscanner.backpack.compose.navigationbar.NavIcon
import net.skyscanner.backpack.compose.navigationbar.TextAction

BpkTopNavBar(
  title = stringResource(R.string.navigation_bar_title),
  navIcon = NavIcon.Back(contentDescription = stringResource(R.string.navigation_back)) { /** onClick **/ },
  action = TextAction(text = stringResource(R.string.navigation_text_action)) { /** onClick **/ },
)
```
