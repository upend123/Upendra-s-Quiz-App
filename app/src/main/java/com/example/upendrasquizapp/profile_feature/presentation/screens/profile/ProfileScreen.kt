package com.example.upendrasquizapp.profile_feature.presentation.screens.profile

import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.upendrasquizapp.R
import com.example.upendrasquizapp.UserProto
import com.example.upendrasquizapp.quiz_feature.presentation.utils.compressTheImage
import com.google.protobuf.ByteString
import java.io.InputStream

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel<ProfileViewModel>(),
    onSaveProfile: (UserProto) -> Unit,
    onBackCall: () -> Unit
){

   var state = viewModel.userState.collectAsState()

    var name by remember { mutableStateOf(state.value.firstName) }
    var qoat by remember { mutableStateOf(state.value.quote) }
    var email by remember { mutableStateOf(state.value.email) }
    var number by remember { mutableStateOf(state.value.phone) }
    var lastName by remember { mutableStateOf(state.value.lastName) }
    var isBannerAdding by remember { mutableStateOf(true) }

    var byteArrayOfProfileImage by remember { mutableStateOf<ByteArray?>(null) }
    var byteArrayOfBannerImage by remember { mutableStateOf<ByteArray?>(null) }

     val MAX_BANNER_SIZE = 3 * 1024 * 1024   // 3,145,728 bytes (3 MB)
    val MAX_PROFILE_SIZE = 1* 1024 * 1024   // 1MB

    LaunchedEffect(state.value){
         name = state.value.firstName
        qoat = state.value.quote
        email = state.value.email
        lastName = state.value.lastName
        number = state.value.phone
        byteArrayOfBannerImage = state.value.bannerImage
        byteArrayOfProfileImage = state.value.profileImage
    }
val context = LocalContext.current
    val pickMedia = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
uri ->
         if(uri != null){
             val inputStream : InputStream? = uri.let {
                 context.contentResolver.openInputStream(it)
             }
             val byteArray = inputStream?.readBytes()
             if(byteArray != null) {
                 var compressedImage = compressTheImage(byteArray)

                 if (isBannerAdding) {
//                     if (!isValidBanner(byte)) {
//                         Toast.makeText(
//                             context,
//                             "Banner dimensions/ratio सही नहीं हैं",
//                             Toast.LENGTH_SHORT
//                         ).show()
//                     } else
//
                         if (compressedImage.size > MAX_BANNER_SIZE) {
                         Toast.makeText(
                             context,
                             "Banner file too large (>2 MB)",
                             Toast.LENGTH_SHORT
                         ).show()
                     } else {
                             Toast.makeText(
                                 context,
                                 "Banner is Set",
                                 Toast.LENGTH_SHORT
                             ).show()
                         byteArrayOfBannerImage = compressedImage
                     }
                 }

                 else{
                     // Profile में यही लॉजिक
//                     if (!isValidProfile(byte)) {
//                         Toast.makeText(
//                             context,
//                             "Profile image square (300–1024px) और 1:1 ratio में होनी चाहिए",
//                             Toast.LENGTH_SHORT
//                         ).show()
//                     } else
                         if (compressedImage.size > MAX_PROFILE_SIZE) {
                         Toast.makeText(
                             context,
                             "Profile file too large (>1 MB)",
                             Toast.LENGTH_SHORT
                         ).show()
                     } else {
                             Toast.makeText(
                                 context,
                                 "Profile file is set",
                                 Toast.LENGTH_SHORT
                             ).show()
                         byteArrayOfProfileImage =compressedImage
                     }
                 }

             }
             }
         }


    BackHandler {
        onBackCall()
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        ){
        Box(modifier = Modifier.fillMaxWidth()){

           Box(modifier = Modifier
               .fillMaxWidth()
               .aspectRatio(3f / 1f)
               .background(
                   colorResource(R.color.purple_700),
                   shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
               )){
               val image = decodeByteArray(byteArrayOfBannerImage ?: state.value.bannerImage)
               if( image != null){
                   Image(
                       bitmap = image,
                       contentDescription = "BannerImage",
                       contentScale = ContentScale.FillBounds,
                   )
               }
           }
            Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically){
                IconButton(
                    onClick = { onBackCall() },

                    ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "backarrowIcons",
                        tint = colorResource(R.color.white)
                    )
                }

                Text(
                    text = "Profile",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    color = colorResource(R.color.white)

                    )
              IconButton(onClick = { isBannerAdding = true
                  pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
              }) {
                  Icon(imageVector = Icons.Default.Edit, contentDescription = "editBanner", tint = Color.LightGray)
              }
            }


              Box(modifier = Modifier
                  .size(160.dp)
                  .align(Alignment.CenterHorizontally), contentAlignment = Alignment.BottomEnd){

                  val image = decodeByteArray(byteArrayOfProfileImage ?: state.value.profileImage)
                  if( image != null){
                      Image(
                          bitmap = image,
                          contentDescription = "profileImage",
                          contentScale = ContentScale.FillBounds,
                          modifier = Modifier.size(160.dp)
                              .clip(CircleShape)
                              .background(Color.Gray)
                              .border(2.dp, Color.Gray, CircleShape)
                      )
                  }
                  else{
                      Icon(
                          imageVector = Icons.Default.Person,
                          contentDescription = "person",
                          modifier = Modifier
                              .align(Alignment.Center)
                              .size(160.dp)
                              .clip(CircleShape)
                              .background(
                                  Color.LightGray
                              )
                              .border(2.dp, Color.Gray, CircleShape)
                              .padding(24.dp)

                      )
                  }
                              IconButton(onClick = {
                                  isBannerAdding = false
                                  pickMedia.launch(
                                      PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                  )

                              }, modifier = Modifier
                                  .clip(CircleShape)
                                  .background(colorResource(R.color.purple_700))
                                  .align(Alignment.BottomEnd)) {
                                  Icon(imageVector = Icons.Default.Add, contentDescription = "add", tint = colorResource(R.color.white),
                                    modifier = Modifier.align(Alignment.Center)
                                  )
                              }






              }
          }

       }
         Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .height(1.dp)
            .background(colorResource(R.color.black)))
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = name,
            label = {Text(text = "First Name", modifier = Modifier.background(Color.Unspecified))},

            onValueChange = {
           name = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            shape = RoundedCornerShape(15.dp),
            leadingIcon = { Icon(imageVector =  Icons.Default.Person, contentDescription = "name")},
            colors = TextFieldDefaults.colors(

            ),

            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            value = lastName,
            label = {Text("Last Name")},
            onValueChange = { lastName = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            shape = RoundedCornerShape(15.dp),
            leadingIcon ={ Icon(imageVector =  Icons.Default.Person, contentDescription = "name")},
            colors = TextFieldDefaults.colors(

            ),

            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        OutlinedTextField(
            value = qoat,
            onValueChange = {qoat = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            shape = RoundedCornerShape(15.dp),
            leadingIcon ={ Icon(imageVector =  Icons.Default.Info, contentDescription = "name")},
            colors = TextFieldDefaults.colors(

            ),
            label = {
                Text("Quote")
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )


            )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            shape = RoundedCornerShape(15.dp),
            leadingIcon ={ Icon(imageVector =  Icons.Default.MailOutline, contentDescription = "name")},
            colors = TextFieldDefaults.colors(
            ),
            label = {Text("Email")},
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )

            )
        OutlinedTextField(
            value = number,
            onValueChange = {number = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            shape = RoundedCornerShape(15.dp),
            leadingIcon ={ Icon(imageVector =  Icons.Default.Call, contentDescription = "name")},
            colors = TextFieldDefaults.colors(

            ),
            label = {Text("Number")}
            ,
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
            )
 Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
Toast.makeText(context,"Profile is Saved 😊", Toast.LENGTH_LONG).show()
                val userProto = UserProto.getDefaultInstance().toBuilder().setFirstName(name).setSecondName(lastName).setQuote(qoat).setEmail(email).setNumber(number).setProfilePhoto(
                    ByteString.copyFrom(byteArrayOfProfileImage ?: state.value.profileImage)).setBannerPhoto(
                    ByteString.copyFrom(byteArrayOfBannerImage ?: state.value.bannerImage)).build()
   viewModel.updateProfile(userProto = userProto)
            },
           modifier = Modifier
               .fillMaxWidth()
               .height(90.dp)
               .padding(16.dp),
           shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_700)),


       ){
            Text(
                text = "Save Profile"
            )

        }

    }

}

fun decodeByteArray(image : ByteArray?) : ImageBitmap?{
    return image?.let {
        BitmapFactory.decodeByteArray(it,0,it.size)
    }?.asImageBitmap()
}


