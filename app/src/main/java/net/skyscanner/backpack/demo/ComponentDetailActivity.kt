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

package net.skyscanner.backpack.demo

import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import net.skyscanner.backpack.demo.data.ComponentRegistry
import net.skyscanner.backpack.util.unsafeLazy

/**
 * An activity representing a single Component detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [MainActivity].
 */
class ComponentDetailActivity : BpkBaseActivity() {

  private val detailToolbar by unsafeLazy { findViewById<Toolbar>(R.id.detail_toolbar) }
  private val componentDetailContainer by unsafeLazy { findViewById<View>(R.id.component_detail_container) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_component_detail)
    setSupportActionBar(detailToolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    // savedInstanceState is non-null when there is fragment state
    // saved from previous configurations of this activity
    // (e.g. when rotating the screen from portrait to landscape).
    // In this case, the fragment will automatically be re-added
    // to its container so we don't need to manually add it.
    // For more information, see the Fragments API guide at:
    //
    // http://developer.android.com/guide/components/fragments.html
    //
    if (savedInstanceState == null) {
      // Create the detail fragment and add it to the activity
      // using a fragment transaction.
      val itemId = intent.getStringExtra(ComponentDetailFragment.ARG_ITEM_ID)
        ?: error("ComponentDetailActivity intent must have ${ComponentDetailFragment.ARG_ITEM_ID} set")
      detailToolbar.title = itemId

      val automationMode = intent.getBooleanExtra(ComponentDetailFragment.AUTOMATION_MODE, false)

      val createFragment = ComponentRegistry.getStoryCreator(itemId)
      val fragment = createFragment.createStory()

      val arguments = fragment.arguments ?: Bundle()
      arguments.putString(ComponentDetailFragment.ARG_ITEM_ID, itemId)
      arguments.putBoolean(ComponentDetailFragment.AUTOMATION_MODE, automationMode)

      fragment.arguments = arguments
      supportFragmentManager.beginTransaction()
        .add(R.id.component_detail_container, fragment)
        .commit()
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> this.onBackPressed()
    }
    return super.onOptionsItemSelected(item)
  }

  fun toggleToolbar() {
    detailToolbar.visibility = if (detailToolbar.visibility == View.VISIBLE) {
      View.GONE
    } else {
      View.VISIBLE
    }
  }

  /*
   Hide/Un-hide toolbar: Shift + T
   toggle layout direction: Shift + D
   toggle markers: Shift + H
  */
  override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
    if (keyCode == KeyEvent.KEYCODE_T && event?.isShiftPressed == true) {
      toggleToolbar()
    }
    if (keyCode == KeyEvent.KEYCODE_D && event?.isShiftPressed == true) {
      if (componentDetailContainer.layoutDirection == View.LAYOUT_DIRECTION_LTR) {
        componentDetailContainer.layoutDirection = View.LAYOUT_DIRECTION_RTL
      } else {
        componentDetailContainer.layoutDirection = View.LAYOUT_DIRECTION_LTR
      }
    }
    return super.onKeyUp(keyCode, event)
  }
}
