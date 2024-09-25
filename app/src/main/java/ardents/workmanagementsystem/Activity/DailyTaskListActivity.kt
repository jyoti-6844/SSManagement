package ardents.workmanagementsystem.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.workmanagementsystem.Adapter.ShowTaskAdapter
import ardents.workmanagementsystem.Model.ShowTaskModelItem
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.ViewModel.TaskViewModel
import ardents.workmanagementsystem.databinding.ActivityDailyTaskListBinding
import ardents.workmanagementsystem.utils.Helper
import ardents.workmanagementsystem.utils.NetworkResult

class DailyTaskListActivity : AppCompatActivity() {
    lateinit var binding: ActivityDailyTaskListBinding
    lateinit var viewModel: TaskViewModel
    lateinit var adapter:ShowTaskAdapter
    var tasklist:List<ShowTaskModelItem> = emptyList()
    lateinit var taskType:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  enableEdgeToEdge()
        binding=ActivityDailyTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel=ViewModelProvider(this).get(TaskViewModel::class.java)
        adapter=ShowTaskAdapter(applicationContext,tasklist)
        binding.showTaskRecycler.adapter=adapter
        taskType=intent.getStringExtra("TaskType")!!

        binding.title.txtHeader.text=taskType



        binding.title.backBtn.setOnClickListener {
            finish()
        }

        viewModel.showTaskData()
        showTaskData()



    }
    fun showTaskData(){
        viewModel.showTaskData.observe(this, Observer {
            Helper.dismissProgressDialog()
            when(it){
                is NetworkResult.Success->{
                    val filteredTaskList=it.data?.filter {
                        it.Task_Name == taskType
                    }
                    if (!filteredTaskList.isNullOrEmpty()){
                        tasklist=filteredTaskList
                        adapter.updateList(tasklist)
                    }else {
                        Toast.makeText(this, "No tasks found with the specified type", Toast.LENGTH_SHORT).show()
                    }

                }
                is NetworkResult.Error->{
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading->{
                    Helper.showProgressDialog(this)
                }

            }
        })
    }
}