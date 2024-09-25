package ardents.workmanagementsystem.Activity

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ardents.workmanagementsystem.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor()
    }
    private fun setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = getColor(R.color.appcolor)
        }
    }

}