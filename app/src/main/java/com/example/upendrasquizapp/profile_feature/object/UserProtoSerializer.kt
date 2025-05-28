package com.example.upendrasquizapp.profile_feature.`object`

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.upendrasquizapp.UserProto
import java.io.InputStream
import java.io.OutputStream

object UserProtoSerializer : Serializer<UserProto> {
    override val defaultValue: UserProto
        get() = UserProto.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserProto {
        try {
            return UserProto.parseFrom(input)
        } catch (exception: Exception) {
            throw CorruptionException("Cannot read proto", exception)
        }

    }

    override suspend fun writeTo(
        t: UserProto,
        output: OutputStream
    ) = t.writeTo(output)

}