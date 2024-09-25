package ardents.workmanagementsystem.Activity

import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.databinding.ActivityPdfInsuranceBinding
import com.bumptech.glide.Glide

class PdfInsuranceActivity : AppCompatActivity() {
    lateinit var binding:ActivityPdfInsuranceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        binding=ActivityPdfInsuranceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val imageUrl=intent.getStringExtra("url").toString()
        Log.d("imageuro",imageUrl)
        val baseUrl = "https://ssapi.marmelos.co.in/"
        val relativePath = imageUrl.replace("\\", "/")
        val url=baseUrl+relativePath

        Glide.with(this).load(url).into(binding.imageview)
    }
}