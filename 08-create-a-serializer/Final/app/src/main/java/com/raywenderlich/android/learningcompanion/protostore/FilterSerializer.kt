package com.raywenderlich.android.learningcompanion.protostore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.raywenderlich.android.learningcompanion.data.FilterOption
import java.io.InputStream
import java.io.OutputStream

class FilterSerializer : Serializer<FilterOption>{

    override val defaultValue: FilterOption = FilterOption.getDefaultInstance()

    override fun readFrom(input: InputStream): FilterOption {
        try {
            return FilterOption.parseFrom(input)
        } catch (e: InvalidProtocolBufferException){
            throw CorruptionException("Cannot read proto.")
        }
    }

    override fun writeTo(t: FilterOption, output: OutputStream) {
        t.writeTo(output)
    }

}