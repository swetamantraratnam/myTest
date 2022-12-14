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

package net.skyscanner.backpack.compose.switch

import androidx.test.ext.junit.runners.AndroidJUnit4
import net.skyscanner.backpack.BpkSnapshotTest
import net.skyscanner.backpack.BpkTestVariant
import net.skyscanner.backpack.demo.compose.CustomContentSwitchExample
import net.skyscanner.backpack.demo.compose.DefaultCheckedSwitchExample
import net.skyscanner.backpack.demo.compose.DefaultUncheckedSwitchExample
import net.skyscanner.backpack.demo.compose.DisabledCheckedSwitchExample
import net.skyscanner.backpack.demo.compose.DisabledUncheckedSwitchExample
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BpkSwitchTest : BpkSnapshotTest() {

  @Before
  fun setup() {
    setDimensions(height = 50, width = 200)
  }

  @Test
  fun defaultUnchecked() = composed {
    DefaultUncheckedSwitchExample()
  }

  @Test
  fun defaultChecked() {
    assumeVariant(BpkTestVariant.Default, BpkTestVariant.DarkMode)
    composed {
      DefaultCheckedSwitchExample()
    }
  }

  @Test
  fun disabledUnchecked() {
    assumeVariant(BpkTestVariant.Default, BpkTestVariant.DarkMode)
    composed {
      DisabledUncheckedSwitchExample()
    }
  }

  @Test
  fun disabledChecked() {
    assumeVariant(BpkTestVariant.Default, BpkTestVariant.DarkMode)
    composed {
      DisabledCheckedSwitchExample()
    }
  }

  @Test
  fun customContent() {
    assumeVariant(BpkTestVariant.Default)
    composed {
      CustomContentSwitchExample()
    }
  }
}
