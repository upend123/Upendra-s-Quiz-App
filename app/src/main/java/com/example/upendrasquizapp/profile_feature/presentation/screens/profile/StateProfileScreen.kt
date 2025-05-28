package com.example.upendrasquizapp.profile_feature.presentation.screens.profile
import com.google.protobuf.ByteString

data class StateProfileScreen(
    var firstName : String = "",
    var lastName : String = "",
    var quote : String ="",
    var email : String = "",
    var phone : String = "",
    var bannerImage: ByteArray? = null,
    var profileImage : ByteArray? = null
)