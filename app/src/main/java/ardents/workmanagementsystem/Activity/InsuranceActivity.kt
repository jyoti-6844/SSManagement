package ardents.workmanagementsystem.Activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.workmanagementsystem.Adapter.InsuranceAdapter
import ardents.workmanagementsystem.Model.InsuranceModelItem
import ardents.workmanagementsystem.Model.InsuranceRequest
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.ViewModel.InsuranceViewModel
import ardents.workmanagementsystem.databinding.ActivityInsuranceBinding
import ardents.workmanagementsystem.utils.Helper
import ardents.workmanagementsystem.utils.NetworkResult
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class InsuranceActivity : AppCompatActivity() {
    lateinit var binding:ActivityInsuranceBinding
    lateinit var viewModel: InsuranceViewModel
    private val calendar = Calendar.getInstance()
    lateinit var adapter:InsuranceAdapter
    var insuranceList:List<InsuranceModelItem> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        binding=ActivityInsuranceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel=ViewModelProvider(this).get(InsuranceViewModel::class.java)
        adapter= InsuranceAdapter(applicationContext,insuranceList)
        binding.insuranceRecycler.adapter=adapter

        binding.title.txtHeader.text="Insurance"

        binding.title.backBtn.setOnClickListener {
            finish()
        }
        binding.cardFromdate.setOnClickListener {
            showDatePicker(binding.edtFromDate)
        }
        binding.cardTodate.setOnClickListener {
            showDatePicker(binding.edtToDate)
        }
        binding.btnShow.setOnClickListener {
            val fromDate=binding.edtFromDate.text.toString().trim()
            val toDate=binding.edtToDate.text.toString().trim()
            if (!Helper.validateEditText(binding.edtFromDate)
                || !Helper.validateEditText(binding.edtToDate)){
                return@setOnClickListener
            }else{
                viewModel.insuranceData(InsuranceRequest(fromDate,toDate))
                insuranceData()
            }
            binding.edtToDate.text=null
            binding.edtFromDate.text=null
        }



    }
    private fun showDatePicker(setDate: EditText) {
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
                setDate.text = Editable.Factory.getInstance().newEditable(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the DatePicker dialog
        datePickerDialog.show()
    }

    fun insuranceData(){
        viewModel.insuranceData.observe(this, Observer {
            Helper.dismissProgressDialog()
            when(it){
                is NetworkResult.Success->{
                    //if (it.data.get().response == "Success")
                    insuranceList=it.data!!
                    adapter.updateList(insuranceList)
                }
                is NetworkResult.Error ->{
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading->{
                    Helper.showProgressDialog(this)
                }
            }

        })
    }
}