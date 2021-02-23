package com.raywenderlich.android.learningcompanion.protostore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import com.raywenderlich.android.learningcompanion.data.FilterOption
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProtoStoreImpl @Inject constructor(
        @ApplicationContext private val context: Context): ProtoStore{

    private val dataStore: DataStore<FilterOption> = context.createDataStore(
            fileName = "courses.db",
            serializer = FilterSerializer()
    )

    override val filtersFlow: Flow<FilterOption>
        get() = TODO("Not yet implemented")

    override suspend fun enableBeginnerFilter(enable: Boolean) {
        dataStore.updateData { currentFilters ->
            val currentFilter = currentFilters.filter
            val changedFilter = if (enable) {
                when (currentFilter) {
                    FilterOption.Filter.ADVANCED -> FilterOption.Filter.BEGINNER_ADVANCED
                    FilterOption.Filter.COMPLETED -> FilterOption.Filter.BEGINNER_COMPLETED
                    FilterOption.Filter.ADVANCED_COMPLETED -> FilterOption.Filter.ALL
                    else -> FilterOption.Filter.BEGINNER
                }
            } else {
                when (currentFilter) {
                    FilterOption.Filter.BEGINNER_ADVANCED -> FilterOption.Filter.ADVANCED
                    FilterOption.Filter.BEGINNER_COMPLETED -> FilterOption.Filter.COMPLETED
                    FilterOption.Filter.ALL -> FilterOption.Filter.ADVANCED_COMPLETED
                    else -> FilterOption.Filter.NONE
                }
            }
            currentFilters.toBuilder().setFilter(changedFilter).build()
        }

    }

    override suspend fun enableAdvancedFilter(enable: Boolean) {
        dataStore.updateData { currentFilters ->
            val currentFilter = currentFilters.filter
            val changedFilter = if (enable) {
                when (currentFilter) {
                    FilterOption.Filter.BEGINNER -> FilterOption.Filter.BEGINNER_ADVANCED
                    FilterOption.Filter.COMPLETED -> FilterOption.Filter.ADVANCED_COMPLETED
                    FilterOption.Filter.BEGINNER_COMPLETED -> FilterOption.Filter.ALL
                    else -> FilterOption.Filter.ADVANCED
                }
            } else {
                when (currentFilter) {
                    FilterOption.Filter.BEGINNER_ADVANCED -> FilterOption.Filter.BEGINNER
                    FilterOption.Filter.ADVANCED_COMPLETED -> FilterOption.Filter.COMPLETED
                    FilterOption.Filter.ALL -> FilterOption.Filter.BEGINNER_COMPLETED
                    else -> FilterOption.Filter.NONE
                }
            }
            currentFilters.toBuilder().setFilter(changedFilter).build()
        }

    }

    override suspend fun enableCompletedFilter(enable: Boolean) {
        dataStore.updateData { currentFilters ->
            val currentFilter = currentFilters.filter
            val changedFilter = if (enable) {
                when (currentFilter) {
                    FilterOption.Filter.BEGINNER -> FilterOption.Filter.BEGINNER_COMPLETED
                    FilterOption.Filter.ADVANCED -> FilterOption.Filter.ADVANCED_COMPLETED
                    FilterOption.Filter.BEGINNER_ADVANCED -> FilterOption.Filter.ALL
                    else -> FilterOption.Filter.COMPLETED
                }
            } else {
                when (currentFilter) {
                    FilterOption.Filter.BEGINNER_COMPLETED -> FilterOption.Filter.BEGINNER
                    FilterOption.Filter.ADVANCED_COMPLETED -> FilterOption.Filter.ADVANCED
                    FilterOption.Filter.ALL -> FilterOption.Filter.BEGINNER_ADVANCED
                    else -> FilterOption.Filter.NONE
                }
            }
            currentFilters.toBuilder().setFilter(changedFilter).build()
        }

    }
}












