package ardents.workmanagementsystem.Activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.databinding.ActivityDateBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class dateActivity : AppCompatActivity() {
    lateinit var binding:ActivityDateBinding
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_CAMERA_PERMISSION = 2
    private var photoURI: Uri? = null
    private lateinit var currentPhotoPath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  enableEdgeToEdge()
        binding=ActivityDateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//        binding.img.setOnClickListener {
//            if(checkAndRequestPermissions()){
//                dispatchTakePictureIntent()
//            }
        }

    }


//    private fun checkAndRequestPermissions(): Boolean {
//        val permissions = arrayOf(
//            android.Manifest.permission.CAMERA,
//            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            android.Manifest.permission.READ_EXTERNAL_STORAGE
//        )
//
//        val listPermissionsNeeded = mutableListOf<String>()
//        for (permission in permissions) {
//            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
//                listPermissionsNeeded.add(permission)
//            }
//        }
//
//        if (listPermissionsNeeded.isNotEmpty()) {
//            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), REQUEST_CAMERA_PERMISSION)
//            return false
//        }
//        return true
//    }
//
//    private fun dispatchTakePictureIntent() {
//        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        if (takePictureIntent.resolveActivity(packageManager) != null) {
//            var photoFile: File? = null
//            try {
//                photoFile = createImageFile()
//            } catch (ex: IOException) {
//                ex.printStackTrace()
//            }
//            if (photoFile != null) {
//                photoURI = FileProvider.getUriForFile(this, "ardents.workmanagementsystem.fileprovider", photoFile)
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//            }
//        }
//    }
//
//    @Throws(IOException::class)
//    private fun createImageFile(): File {
//        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
//        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        return File.createTempFile(
//            "JPEG_${timeStamp}_",
//            ".jpg",
//            storageDir
//        ).apply {
//            currentPhotoPath = absolutePath
//        }
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            REQUEST_CAMERA_PERMISSION -> {
//                if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
//                    dispatchTakePictureIntent()
//                } else {
//                    Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
//            val bitmap = BitmapFactory.decodeFile(currentPhotoPath)
//            binding.img.setImageBitmap(bitmap)
//            val base64String = encodeImageToBase64(bitmap)
//            // Do something with the base64String, e.g., send it to a server
//        }
//    }
//
//    private fun encodeImageToBase64(bitmap: Bitmap): String {
//        val byteArrayOutputStream = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
//        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
//        return Base64.encodeToString(byteArray, Base64.DEFAULT)
//    }
