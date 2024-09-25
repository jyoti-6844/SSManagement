package ardents.workmanagementsystem.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.databinding.ActivityUpdateWorkBinding

class UpdateWorkActivity : AppCompatActivity() {
    lateinit var binding:ActivityUpdateWorkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateWorkBinding.inflate(layoutInflater)
        //enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.work.taskDaily.setOnClickListener {
            val intent=Intent(applicationContext,DailyTaskListActivity::class.java)
            intent.putExtra("TaskType","SS Work Task Daily")
            startActivity(intent)
        }
        binding.work.taskSpvs.setOnClickListener {
            val intent=Intent(applicationContext,DailyTaskListActivity::class.java)
            intent.putExtra("TaskType","MD SPVS Meeting")
            startActivity(intent)
        }
        binding.work.taskOther.setOnClickListener {
            val intent=Intent(applicationContext,DailyTaskListActivity::class.java)
            intent.putExtra("TaskType","Other")
            startActivity(intent)
        }
        binding.work.taskPersonal.setOnClickListener {
            val intent=Intent(applicationContext,DailyTaskListActivity::class.java)
            intent.putExtra("TaskType","SS Personal Meeting")
            startActivity(intent)
        }
        binding.work.taskCompleted.setOnClickListener {
            val intent=Intent(applicationContext,DailyTaskListActivity::class.java)
            intent.putExtra("TaskType","SS Task Complete (UPSRTC)")
            startActivity(intent)
        }
        binding.work.taskRmMeeting.setOnClickListener {
            val intent=Intent(applicationContext,DailyTaskListActivity::class.java)
            intent.putExtra("TaskType","RM Meeting")
            startActivity(intent)
        }
    }
}