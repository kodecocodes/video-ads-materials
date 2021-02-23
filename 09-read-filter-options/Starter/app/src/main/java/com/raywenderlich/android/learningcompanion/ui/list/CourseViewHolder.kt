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

package com.raywenderlich.android.learningcompanion.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.android.learningcompanion.R
import com.raywenderlich.android.learningcompanion.data.model.Course
import com.raywenderlich.android.learningcompanion.data.model.CourseLevel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.course_list_item.view.*

class CourseViewHolder(
  override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

  fun bind(course: Course) = with(containerView) {
    courseName.text = course.name
    courseDescription.text = course.description
    val completedResource = if (course.completed) {
      ContextCompat.getDrawable(context, R.drawable.icn_checkmark_filled)
    } else {
      ContextCompat.getDrawable(context, R.drawable.icn_checkmark_outlined)
    }
    courseCompleted.setImageDrawable(completedResource)
    when (course.level) {
      CourseLevel.BEGINNER -> levelIndicator.setImageResource(R.drawable.shape_level_beginner)
      CourseLevel.ADVANCED -> levelIndicator.setImageResource(R.drawable.shape_level_advanced)
    }
  }

  companion object {
    fun create(parent: ViewGroup): CourseViewHolder {
      val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.course_list_item, parent, false)
      return CourseViewHolder(view)
    }
  }
}