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

package com.raywenderlich.android.learningcompanion.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.raywenderlich.android.learningcompanion.data.FilterOption
import com.raywenderlich.android.learningcompanion.data.getCourseList
import com.raywenderlich.android.learningcompanion.data.model.Course
import com.raywenderlich.android.learningcompanion.data.model.CourseLevel
import com.raywenderlich.android.learningcompanion.prefsstore.PrefsStore
import com.raywenderlich.android.learningcompanion.protostore.ProtoStore
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class CoursesViewModel @ViewModelInject constructor(
  private val prefsStore: PrefsStore,
  private val protoStore: ProtoStore
) : ViewModel() {

  private val courseUiModelFlow = combine(getCourseList(), protoStore.filtersFlow) {
    courses: List<Course>, filterOption: FilterOption ->
    return@combine CourseUiModel(
            courses = filterCourses(courses, filterOption),
            filter = filterOption.filter
    )
  }

  private fun filterCourses(courses: List<Course>, filterOption: FilterOption): List<Course> {
    return when (filterOption.filter) {
      FilterOption.Filter.BEGINNER -> courses.filter { it.level == CourseLevel.BEGINNER }
      FilterOption.Filter.NONE -> courses
      FilterOption.Filter.ADVANCED -> courses.filter { it.level == CourseLevel.ADVANCED }
      FilterOption.Filter.COMPLETED -> courses.filter { it.completed }
      FilterOption.Filter.BEGINNER_ADVANCED -> courses.filter {
        it.level == CourseLevel.BEGINNER || it.level == CourseLevel.ADVANCED }
      FilterOption.Filter.BEGINNER_COMPLETED -> courses.filter {
        it.level == CourseLevel.BEGINNER || it.completed }
      FilterOption.Filter.ADVANCED_COMPLETED -> courses.filter {
        it.level == CourseLevel.ADVANCED || it.completed }
      FilterOption.Filter.ALL -> courses
      // There shouldn't be any other value for filtering
      else -> throw UnsupportedOperationException("$filterOption doesn't exist.")
    }
  }



  val darkThemeEnabled = prefsStore.isNightMode().asLiveData()

  val courseUiModel = courseUiModelFlow.asLiveData()

  val courses = getCourseList().asLiveData()

  fun enableBeginnerFilter(enable: Boolean) {
    viewModelScope.launch {
      protoStore.enableBeginnerFilter(enable)
    }
  }

  fun enableAdvancedFilter(enable: Boolean) {
    viewModelScope.launch {
      protoStore.enableAdvancedFilter(enable)
    }
  }

  fun enableCompletedFilter(enable: Boolean) {
    viewModelScope.launch {
      protoStore.enableCompletedFilter(enable)
    }
  }

  fun toggleNightMode() {
    viewModelScope.launch {
      prefsStore.toogleNightMode()
    }
  }
}

data class CourseUiModel(val courses: List<Course>, val filter: FilterOption.Filter)