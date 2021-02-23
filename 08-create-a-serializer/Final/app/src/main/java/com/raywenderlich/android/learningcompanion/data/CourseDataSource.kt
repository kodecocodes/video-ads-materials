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

package com.raywenderlich.android.learningcompanion.data

import com.raywenderlich.android.learningcompanion.data.model.Course
import com.raywenderlich.android.learningcompanion.data.model.CourseLevel
import kotlinx.coroutines.flow.flowOf

fun getCourseList() = flowOf(
  listOf(
    Course(
      name = "Kotlin Fundamentals",
      description = "Learn the fundamentals of the new language developed by JetBrains",
      level = CourseLevel.BEGINNER,
      completed = true
    ),
    Course(
      name = "Swift Fundamentals",
      description = "Learn the fundamentals of a modern language for iOS development",
      level = CourseLevel.BEGINNER,
      completed = false
    ),
    Course(
      name = "Advanced Android",
      description = "Learn about some of the more advanced topics like dependency injection and app architecture",
      level = CourseLevel.ADVANCED,
      completed = false
    ),
    Course(
      name = "Jetpack Compose",
      description = "Learn how to build beautiful UIs with the new and modern declarative toolkit.",
      level = CourseLevel.BEGINNER,
      completed = false
    ),
    Course(
      name = "Hilt Dependency Injection",
      description = "Get to know the newest library for managing dependencies",
      level = CourseLevel.ADVANCED,
      completed = true
    ),
    Course(
      name = "Android Architecture Components",
      description = "Build your apps easier and faster with these new tools provided by the Google team",
      level = CourseLevel.BEGINNER,
      completed = false
    )
  )
)