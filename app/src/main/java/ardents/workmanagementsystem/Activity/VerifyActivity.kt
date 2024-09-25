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
import ardents.workmanagementsystem.MainActivity
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.ViewModel.ExpenseViewModel
import ardents.workmanagementsystem.databinding.ActivityVerifyBinding
import ardents.workmanagementsystem.databinding.ExpenseverifylayBinding
import ardents.workmanagementsystem.utils.Helper
import ardents.workmanagementsystem.utils.NetworkResult

class VerifyActivity : AppCompatActivity() {
    lateinit var binding:ActivityVerifyBinding
    lateinit var viewModel: ExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        binding=ActivityVerifyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val id=intent.getStringExtra("Expense_Id")
      //  Toast.makeText(this,id,Toast.LENGTH_SHORT).show()
        viewModel=ViewModelProvider(this).get(ExpenseViewModel::class.java)

        binding.title.txtHeader.text="Verify"

        binding.title.backBtn.setOnClickListener {
            finish()
        }
       binding.txtApprove.setOnClickListener {
           val status=binding.txtApprove.text.toString()
           val remark=binding.edtRemark.text.toString()
           viewModel.verifydata(id!!.toInt(),status,remark)
           verifyData()
           startActivity(Intent(this,MainActivity::class.java))
           finish()
       }
        binding.txtReject.setOnClickListener {
            val status=binding.txtReject.text.toString()
            val remark=binding.edtRemark.text.toString()
            viewModel.verifydata(id!!.toInt(),status,remark)
            verifyData()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }


    }
    fun verifyData(){
        viewModel.verifyData.observe(this, Observer {
            Helper.dismissProgressDialog()
            when(it){
                is NetworkResult.Success->{
                    Toast.makeText(this,it.data?.response, Toast.LENGTH_SHORT).show()
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