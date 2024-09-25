package ardents.workmanagementsystem.Activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.databinding.ActivityProfileBinding
import ardents.workmanagementsystem.utils.SharedPrefManager
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class ProfileActivity : AppCompatActivity() {
    lateinit var binding:ActivityProfileBinding
    private val PICK_IMAGE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProfileBinding.inflate(layoutInflater)
       // enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadProfileImage()

        binding.title.txtHeader.text="Profile"
        binding.title.backBtn.setOnClickListener { finish() }
        binding.txtUserMail.text=SharedPrefManager.getInstance(this).getLoginResponse()?.Login_Email
        binding.txtUserName.text=SharedPrefManager.getInstance(this).getLoginResponse()?.Person_Name
        binding.txtUserPhone.text=SharedPrefManager.getInstance(this).getLoginResponse()?.Person_Mobile
        binding.imgUser.setOnClickListener {
            selectImageFromGallery()
        }
    }



    fun selectImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            val imageUri = data.data
            if (imageUri != null) {
                binding.imgUser.setImageURI(imageUri)
                // Save the image to internal storage
                saveImageToInternalStorage(imageUri)
            }
        }
    }
    fun saveImageToInternalStorage(imageUri: Uri): String? {
        return try {
            // Get the bitmap from the selected image URI
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)

            // Create or access internal storage directory
            val directory = getDir("profile_images", Context.MODE_PRIVATE)
            val imageFile = File(directory, "profile_photo.png")

            // Save the image as PNG
            val fos = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.close()

            // Return the absolute path of the saved image
            imageFile.absolutePath
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
    fun loadProfileImage() {
        val directory = getDir("profile_images", Context.MODE_PRIVATE)
        val imageFile = File(directory, "profile_photo.png")

        if (imageFile.exists()) {
            // Load the image as Bitmap and set it to the ImageView
            val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
            binding.imgUser.setImageBitmap(bitmap)
        } else {
            // Set a default image if no profile image is found
            binding.imgUser.setImageResource(R.drawable.user)
        }
    }



}