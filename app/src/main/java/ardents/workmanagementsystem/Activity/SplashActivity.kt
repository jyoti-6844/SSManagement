package ardents.workmanagementsystem.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ardents.workmanagementsystem.MainActivity
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.utils.SharedPrefManager

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        Handler(Looper.getMainLooper()).postDelayed(
            {
                val loginResponse = SharedPrefManager.getInstance(this).getLoginResponse()?.response.toString()
                Log.d("jyotiActivity", "Login Response: $loginResponse")

                if (!loginResponse.isNullOrEmpty() && loginResponse == "Success"){
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                }else{
                    startActivity(Intent(applicationContext, LoginActivity::class.java))
                    finish()
                }

            }, 3000)

    }
}