    package com.example.upendrasquizapp.profile_feature.data.repository

    import android.content.Context
    import androidx.datastore.core.DataStore
    import androidx.datastore.dataStore
    import com.example.upendrasquizapp.UserProto
    import com.example.upendrasquizapp.profile_feature.`object`.UserProtoSerializer
    import com.google.protobuf.ByteString
    import kotlinx.coroutines.flow.Flow

    private const val DATA_STORE_FILE_NAME = "user_proto.pb"

    private val Context.dataStore  : DataStore<UserProto> by dataStore(fileName = DATA_STORE_FILE_NAME, serializer = UserProtoSerializer)


    class UserPrefsProtoRepository(private val context: Context) {

        suspend fun updateUserProfile(newUserProto: UserProto) = context.dataStore.updateData {
                             newUserProto
        }

        suspend fun updateName(newName : String){
            context.dataStore.updateData {
                it.toBuilder().setFirstName(newName).build()
            }
        }
        suspend fun updateEmail(newEmail : String){
            context.dataStore.updateData {
                it.toBuilder().setEmail(newEmail).build()
            }
        }
        suspend fun updateNumber(newNumber : String){
            context.dataStore.updateData {
                it.toBuilder().setNumber(newNumber).build()
            }
        }
        suspend fun updateQuote(newQuote : String){
            context.dataStore.updateData {
                it.toBuilder().setQuote(newQuote).build()
            }
        }
        suspend fun updateProfilePhoto(newProfilePhoto : ByteArray){
            context.dataStore.updateData {
                it.toBuilder().setProfilePhoto(ByteString.copyFrom(newProfilePhoto)).build()
            }
        }
        suspend fun updateBannerPhoto(newBannerPhoto : ByteArray){
            context.dataStore.updateData {
                it.toBuilder().setBannerPhoto(ByteString.copyFrom(newBannerPhoto)).build()
            }
        }
        suspend fun updateSecondName(newSecondName : String){
            context.dataStore.updateData {
                it.toBuilder().setSecondName(newSecondName).build()
            }
        }
        suspend fun clearAllData(){
            context.dataStore.updateData {
                it.toBuilder().clear().build()
            }
        }
     val profileFlow: Flow<UserProto> = context.dataStore.data

    }