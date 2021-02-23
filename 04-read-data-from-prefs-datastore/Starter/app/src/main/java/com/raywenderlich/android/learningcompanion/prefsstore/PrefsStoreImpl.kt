package com.raywenderlich.android.learningcompanion.prefsstore

import android.content.Context
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.createDataStore
import com.raywenderlich.android.learningcompanion.di.PREFS_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val STORE_NAME = "learning_data_store"

class PrefsStoreImpl @Inject constructor(
        @ApplicationContext context: Context): PrefsStore{

            private val dataStore = context.createDataStore(
                    name = STORE_NAME,
                    migrations = listOf(SharedPreferencesMigration(context, PREFS_NAME))
            )
}