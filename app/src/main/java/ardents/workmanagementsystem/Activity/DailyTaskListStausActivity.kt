package ardents.workmanagementsystem.Activity

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.workmanagementsystem.Model.UpdateTaskRequest
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.ViewModel.TaskViewModel
import ardents.workmanagementsystem.databinding.ActivityDailyTaskListStausBinding
import ardents.workmanagementsystem.utils.Helper
import ardents.workmanagementsystem.utils.NetworkResult

class DailyTaskListStausActivity : AppCompatActivity() {
    lateinit var binding: ActivityDailyTaskListStausBinding
    lateinit var viewModel: TaskViewModel
    lateinit var taskId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyTaskListStausBinding.inflate(layoutInflater)
        // enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        taskId = intent.getStringExtra("TaskId").toString()

        val workStatus = resources.getStringArray(R.array.work_status)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, workStatus)
        binding.autoCompleteTxtView.setAdapter(arrayAdapter)

        binding.title.txtHeader.text="Update Work Status"

        binding.title.backBtn.setOnClickListener {
            finish()
        }


        binding.btnSave.setOnClickListener {
            val status = binding.autoCompleteTxtView.text.toString()
            val remark = binding.edtRemark.text.toString().trim()
            val checkBy = binding.edtCheckBy.text.toString().trim()
            Log.d("TaskData", "Status: $status, Remark: $remark, CheckBy: $checkBy, TaskId: $taskId")
            if (!Helper.validateEditText(binding.autoCompleteTxtView)
                || !Helper.validateEditText(binding.edtRemark)
                || !Helper.validateEditText(binding.edtRemark)
            ) {
                return@setOnClickListener
            } else {
                viewModel.updateTaskData(UpdateTaskRequest(checkBy, remark, taskId, status))
                updateTaskData()
            }
            binding.edtCheckBy.text=null
            binding.edtRemark.text=null

        }

        // binding.txtInputLayout.boxStrokeColor=android.R.color.transparent
    }


    fun updateTaskData() {
        viewModel.updateTaskData.observe(this, Observer {
            Helper.dismissProgressDialog()
            when (it) {
                is NetworkResult.Success -> {
                    if (it.data?.response == "Task Successfully Updated") {
                        Toast.makeText(this, it.data.response, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
                    }

                }

                is NetworkResult.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading -> {
                    Helper.showProgressDialog(this)
                }
            }
        })
    }
}