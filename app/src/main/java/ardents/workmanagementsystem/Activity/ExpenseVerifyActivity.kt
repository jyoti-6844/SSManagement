package ardents.workmanagementsystem.Activity

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.workmanagementsystem.Adapter.ExpenseVerifyAdapter
import ardents.workmanagementsystem.Model.ExpenseVerifyModelItem
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.ViewModel.ExpenseViewModel
import ardents.workmanagementsystem.databinding.ActivityExpenseVerifyBinding
import ardents.workmanagementsystem.utils.Helper
import ardents.workmanagementsystem.utils.NetworkResult

class ExpenseVerifyActivity : AppCompatActivity() {
    lateinit var binding:ActivityExpenseVerifyBinding
    lateinit var viewModel:ExpenseViewModel
    lateinit var adapter:ExpenseVerifyAdapter
    var expenseList:List<ExpenseVerifyModelItem> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        binding=ActivityExpenseVerifyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel=ViewModelProvider(this).get(ExpenseViewModel::class.java)
        adapter= ExpenseVerifyAdapter(applicationContext,expenseList)
        binding.expenseRecycler.adapter=adapter

        binding.title.txtHeader.text="Verify Expense"

        binding.title.backBtn.setOnClickListener {
            finish()
        }

        viewModel.expenseData()
        expenseData()

//        binding.btnVerify.setOnClickListener {
//            showDialog()
//        }
    }

//    fun showDialog(){
//        val builder=AlertDialog.Builder(this)
//        val dialogBinding=VerifydialogLayBinding.inflate(layoutInflater)
////        val inflater=layoutInflater
////        val dialogLayout=inflater.inflate(R.layout.verifydialog_lay,null)
//        builder.setView(dialogBinding.root)
//        val alertDialog=builder.create()
//        alertDialog.setCancelable(false)
//        dialogBinding.txtReject.setOnClickListener {
//            alertDialog.dismiss()
//            binding.btnVerify.visibility= View.GONE
//            binding.txtReject.visibility=View.VISIBLE
//        }
//        dialogBinding.txtApprove.setOnClickListener {
//            alertDialog.dismiss()
//            binding.btnVerify.visibility=View.GONE
//            binding.txtApprove.visibility=View.VISIBLE
//        }
//
//        alertDialog.show()
//}

    fun expenseData(){
        viewModel.expenseData.observe(this, Observer {
            Helper.dismissProgressDialog()
            when(it){
                is NetworkResult.Success->{
                    expenseList=it.data!!
                    adapter.updateList(expenseList)
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