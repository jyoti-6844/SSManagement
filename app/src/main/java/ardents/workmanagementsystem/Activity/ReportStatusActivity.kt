package ardents.workmanagementsystem.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.workmanagementsystem.Adapter.ReportStatusAdapter
import ardents.workmanagementsystem.Model.TaskReportModelItem
import ardents.workmanagementsystem.Model.TaskReportRequest
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.ViewModel.TaskViewModel
import ardents.workmanagementsystem.databinding.ActivityReportStatusBinding
import ardents.workmanagementsystem.utils.Helper
import ardents.workmanagementsystem.utils.NetworkResult

class ReportStatusActivity : AppCompatActivity() {
    lateinit var binding:ActivityReportStatusBinding
    lateinit var viewModel: TaskViewModel
    lateinit var adapter:ReportStatusAdapter
    var taskReportList:List<TaskReportModelItem> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        binding=ActivityReportStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
       binding.title.backBtn.setOnClickListener {
           finish()
       }
        binding.title.txtHeader.text="Reports Status"
        val fromDate=intent.getStringExtra("FromDate").toString()
        val toDate=intent.getStringExtra("ToDate").toString()
        val task=intent.getStringExtra("Task").toString()
        viewModel= ViewModelProvider(this).get(TaskViewModel::class.java)
        adapter=ReportStatusAdapter(this,taskReportList)
        binding.taskReportRecycler.adapter=adapter
        viewModel.taskReportData(TaskReportRequest(fromDate,toDate,task))
        taskReportData()
    }

    fun taskReportData(){
        viewModel.taskReportData.observe(this, Observer {
            Helper.dismissProgressDialog()
            when(it){
                is NetworkResult.Success ->{
                    val data=it.data?.filter {
                        it.response =="Success"
                    }
                    if (!data.isNullOrEmpty()){
                        taskReportList=data
                        adapter.updateList(taskReportList)
                    }else {
                        Toast.makeText(this, "No data found ", Toast.LENGTH_SHORT).show()
                    }

                }
                is NetworkResult.Error->{
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading->{
                    Helper.showProgressDialog(this)
                }
            }
        })
    }
}