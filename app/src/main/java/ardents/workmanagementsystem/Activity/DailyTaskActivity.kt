package ardents.workmanagementsystem.Activity

import android.app.AlertDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.workmanagementsystem.Model.SubmitTaskRequest
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.ViewModel.TaskViewModel
import ardents.workmanagementsystem.databinding.ActivityDailyTaskBinding
import ardents.workmanagementsystem.utils.Helper
import ardents.workmanagementsystem.utils.NetworkResult

class DailyTaskActivity : AppCompatActivity() {
    lateinit var binding:ActivityDailyTaskBinding
    lateinit var viewModel:TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  enableEdgeToEdge()
        binding=ActivityDailyTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel=ViewModelProvider(this).get(TaskViewModel::class.java)

        binding.title.txtHeader.text="Create SS Task"

        binding.title.backBtn.setOnClickListener {
            finish()
        }

        val task=resources.getStringArray(R.array.task)
        val taskAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,task)
        binding.autoCompleteTxtView.setAdapter(taskAdapter)
        binding.autoAssigbTo.setAdapter(ArrayAdapter(this,android.R.layout.simple_list_item_1,resources.getStringArray(R.array.names)))
        binding.autoAssigbBy.setAdapter(ArrayAdapter(this,android.R.layout.simple_list_item_1,resources.getStringArray(R.array.names)))
        binding.autoCheckbBy.setAdapter(ArrayAdapter(this,android.R.layout.simple_list_item_1,resources.getStringArray(R.array.names)))

        binding.btnSubmit.setOnClickListener {
            val task=binding.autoCompleteTxtView.text.toString()
            val task_dec=binding.taskDesc.text.toString()
            val assign_to=binding.autoAssigbTo.text.toString()
            val assign_by=binding.autoAssigbBy.text.toString()
            val duration=binding.taskDuration.text.toString()
            if (!Helper.validateEditText(binding.taskDesc)
                || !Helper.validateEditText(binding.autoAssigbBy)
                || !Helper.validateEditText(binding.autoAssigbBy)
                || !Helper.validateEditText(binding.taskDuration)
                || !Helper.validateEditText(binding.autoCompleteTxtView)){
                return@setOnClickListener
            }else{
                viewModel.taskData(SubmitTaskRequest(assign_by,assign_to,task_dec,duration,task))
                submitTask()
            }
            binding.taskDesc.text=null
            binding.taskDuration.text=null
            binding.autoCompleteTxtView.text=null
            binding.autoAssigbTo.text=null
            binding.autoAssigbBy.text=null
            binding.taskRemarks.text=null
            binding.autoCheckbBy.text=null

        }

//        binding.autoCompleteTxtView.setOnItemClickListener { adapterView, view, i, l ->
//            val selectedTask=adapterView.getItemAtPosition(i).toString()
//            if (selectedTask == "Other"){
//                showOtherTaskDialog()
//            }
//        }
    }

    private fun showOtherTaskDialog() {
        // Create an alert dialog builder
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Other Task")

        // Set up the input
        val input = EditText(this)
        input.hint = "Enter task"
        builder.setView(input)

        // Set up the buttons
        builder.setPositiveButton("OK") { dialog, which ->
            val otherTask = input.text.toString()
            if (otherTask.isNotBlank()) {
                //Toast.makeText(this, "Entered: $otherTask", Toast.LENGTH_SHORT).show()
                // Set the text of the AutoCompleteTextView to the entered task
                binding.autoCompleteTxtView.setText(otherTask)
            } else {
                Toast.makeText(this, "Task cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }

        builder.show()
    }

    fun submitTask(){

        viewModel.submitTaskData.observe(this, Observer {
            Helper.dismissProgressDialog()
            when(it){
                is NetworkResult.Success ->{
                    if (it.data?.response=="Success"){
                        Toast.makeText(this,"Task Submit Successful",Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
                is NetworkResult.Error ->{
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading->{
                    Helper.showProgressDialog(this)
                }
            }
        })
    }
}