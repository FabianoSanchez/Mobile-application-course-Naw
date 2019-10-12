package com.example.userprofile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_profile.*

 const val GALLERY_REQUEST_CODE = 100
class CreateProfileActivity : AppCompatActivity() {

    private var profileImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)

        initViews()
    }

    private fun initViews(){
        pictureButton.setOnClickListener{onGalleryClick()}
        confirmButton.setOnClickListener{onConfirmClick()}
    }

    private fun onConfirmClick(){
        val profile = Profile(
            firstNameInput.text.toString(),
            lastNameInput.text.toString(),
            descriptionInput.text.toString(),
            profileImageUri
        )
        val profileActivityIntent = Intent(this@CreateProfileActivity, ProfileActivity::class.java)
        profileActivityIntent.putExtra(ProfileActivity.PROFILE_EXTRA,profile)
        startActivity(profileActivityIntent)


    }

    private fun onGalleryClick(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
           when(requestCode){
               GALLERY_REQUEST_CODE ->{
                   profileImageUri = data?.data
                   profileImage.setImageURI(profileImageUri)
               }
           }
        }
    }
}
