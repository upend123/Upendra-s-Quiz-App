package com.example.upendrasquizapp.profile_feature.presentation.screens.profile

import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.upendrasquizapp.UserProto
import com.example.upendrasquizapp.profile_feature.data.repository.UserPrefsProtoRepository
import com.google.protobuf.ByteString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepo: UserPrefsProtoRepository
) : ViewModel() {

  private var _userState = MutableStateFlow(StateProfileScreen())
    var userState = _userState.asStateFlow()


    init {
        viewModelScope.launch {
            userRepo.profileFlow.collect { protoData ->
             _userState.value = StateProfileScreen(
                 firstName = protoData.firstName,
                 lastName = protoData.secondName,
                 quote = protoData.quote,
                 email = protoData.email,
                 phone = protoData.number,
                 bannerImage = protoData.bannerPhoto.toByteArray(),
                 profileImage = protoData.profilePhoto.toByteArray()
             )
            }
        }
    }


 //update profile
 fun updateProfile(userProto: UserProto){
     viewModelScope.launch{
         userRepo.updateUserProfile(newUserProto = userProto) }
 }


    // Update functions
    fun updateLastName(value: String) = viewModelScope.launch {
        userRepo.updateSecondName(value)
    }

    fun updateProfileImage(bytes: ByteArray) = viewModelScope.launch {
        userRepo.updateProfilePhoto(bytes)
    }

   private fun ByteString.toByteArray() : ByteArray = this.toByteArray()

    private fun ByteArray.toImageBitmapOrNull() : ImageBitmap?{
        return BitmapFactory.decodeByteArray(this,0,size)?.asImageBitmap()
    }

}
