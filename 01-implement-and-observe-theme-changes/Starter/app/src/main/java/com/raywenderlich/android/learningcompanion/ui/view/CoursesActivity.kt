/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.learningcompanion.ui.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.android.learningcompanion.R
import com.raywenderlich.android.learningcompanion.presentation.CoursesViewModel
import com.raywenderlich.android.learningcompanion.ui.list.CoursesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_courses.*

@AndroidEntryPoint
class CoursesActivity : AppCompatActivity() {
  private val viewModel: CoursesViewModel by viewModels()
  private val adapter by lazy { CoursesAdapter() }
  private var nightModeActive = false

  override fun onCreate(savedInstanceState: Bundle?) {
    setTheme(R.style.AppTheme)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_courses)

    initCourseList()
    subscribeToData()
    observeFilterChanges()
  }

  private fun initCourseList() {
    courseList.layoutManager = LinearLayoutManager(this)
    courseList.itemAnimator = DefaultItemAnimator()
    courseList.adapter = adapter
  }

  private fun subscribeToData() {
    // Subscribe to get the data from the ViewModel
    viewModel.courses.observe(this) {
      adapter.setCourses(it)
    }
  }

  private fun observeFilterChanges() {
    filterBeginner.setOnCheckedChangeListener { _, isChecked ->
      viewModel.enableBeginnerFilter(isChecked)
    }

    filterAdvanced.setOnCheckedChangeListener { _, isChecked ->
      viewModel.enableAdvancedFilter(isChecked)
    }

    filterCompleted.setOnCheckedChangeListener { _, isChecked ->
      viewModel.enableCompletedFilter(isChecked)
    }
  }

//  private fun updateFilter(filter: FilterOption.Filter) {
//    filterBeginner.isChecked = filter == FilterOption.Filter.BEGINNER ||
//        filter == FilterOption.Filter.BEGINNER_ADVANCED ||
//        filter == FilterOption.Filter.BEGINNER_COMPLETED ||
//        filter == FilterOption.Filter.ALL
//
//    filterAdvanced.isChecked = filter == FilterOption.Filter.ADVANCED ||
//        filter == FilterOption.Filter.ADVANCED_COMPLETED ||
//        filter == FilterOption.Filter.BEGINNER_ADVANCED ||
//        filter == FilterOption.Filter.ALL
//
//    filterCompleted.isChecked = filter == FilterOption.Filter.COMPLETED ||
//        filter == FilterOption.Filter.BEGINNER_COMPLETED ||
//        filter == FilterOption.Filter.ADVANCED_COMPLETED ||
//        filter == FilterOption.Filter.ALL
//  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.overflow_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.dayNightMode) {
      viewModel.toggleNightMode()
    }
    return true
  }

  override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
    if (nightModeActive) {
      menu?.findItem(R.id.dayNightMode)?.setIcon(R.drawable.icn_night_mode)
    } else {
      menu?.findItem(R.id.dayNightMode)?.setIcon(R.drawable.icn_light_mode)
    }
    return true
  }
}
