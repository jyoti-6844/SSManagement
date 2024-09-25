package ardents.workmanagementsystem.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.databinding.ActivityCreateWorkBinding

class CreateWorkActivity : AppCompatActivity() {
    lateinit var binding:ActivityCreateWorkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCreateWorkBinding.inflate(layoutInflater)
      //  enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.work.taskDaily.setOnClickListener {
            startActivity(Intent(applicationContext,DailyTaskActivity::class.java))
        }
    }
}