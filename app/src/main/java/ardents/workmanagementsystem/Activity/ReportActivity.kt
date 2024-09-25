package ardents.workmanagementsystem.Activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ThemedSpinnerAdapter.Helper
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.workmanagementsystem.Model.TaskReportRequest
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.ViewModel.TaskViewModel
import ardents.workmanagementsystem.databinding.ActivityReportBinding
import ardents.workmanagementsystem.utils.NetworkResult
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ReportActivity : AppCompatActivity() {
    lateinit var binding:ActivityReportBinding
    private val calendar = Calendar.getInstance()
    lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        binding=ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel=ViewModelProvider(this).get(TaskViewModel::class.java)

        binding.title.txtHeader.text="Reports"

        binding.title.backBtn.setOnClickListener {
            finish()
        }

        binding.btnShow.setOnClickListener {
            val fromDate=binding.edtFromDate.text.toString().trim()
            val toDate=binding.edtTodate.text.toString().trim()
            val task=binding.autoCompleteTxtView.text.toString().trim()
            if (!ardents.workmanagementsystem.utils.Helper.validateEditText(binding.edtTodate)
                || !ardents.workmanagementsystem.utils.Helper.validateEditText(binding.edtFromDate)
                || !ardents.workmanagementsystem.utils.Helper.validateEditText(binding.autoCompleteTxtView)){
                return@setOnClickListener
            }else{
                val intent=Intent(applicationContext,ReportStatusActivity::class.java)
                intent.putExtra("FromDate",fromDate)
                intent.putExtra("ToDate",toDate)
                intent.putExtra("Task",task)
                startActivity(intent)
            }

        }

        val task=resources.getStringArray(R.array.task)
        val taskAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,task)
        binding.autoCompleteTxtView.setAdapter(taskAdapter)

        binding.imgeFromDate.setOnClickListener {
            showDatePicker(binding.edtFromDate)
        }
        binding.imgeToDate.setOnClickListener {
            showDatePicker(binding.edtTodate)
        }


        binding.btnShow
    }
    private fun showDatePicker(setDate:EditText) {
        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            this, {DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Create a new Calendar instance to hold the selected date
                val selectedDate = Calendar.getInstance()
                // Set the selected date using the values received from the DatePicker dialog
                selectedDate.set(year, monthOfYear, dayOfMonth)
                // Create a SimpleDateFormat to format the date as "dd/MM/yyyy"
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                // Format the selected date into a string
                val formattedDate = dateFormat.format(selectedDate.time)
                // Update the TextView to display the selected date with the "Selected Date: " prefix
               setDate.text =Editable.Factory.getInstance().newEditable(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the DatePicker dialog
        datePickerDialog.show()
    }

}