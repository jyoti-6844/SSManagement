package ardents.workmanagementsystem.Activity

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Base64
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.workmanagementsystem.Model.ExpenseRequest
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.ViewModel.ExpenseViewModel
import ardents.workmanagementsystem.databinding.ActivityExpenseBinding
import ardents.workmanagementsystem.utils.Helper
import ardents.workmanagementsystem.utils.NetworkResult
import java.io.ByteArrayOutputStream
import java.io.InputStream
import kotlin.math.exp

class ExpenseActivity : AppCompatActivity() {
    private val PICK_FILE_REQUEST_CODE = 1
    lateinit var binding:ActivityExpenseBinding
    private var selectedImagePath: String? = null
    private var selectedImageBase64: String? = null
    lateinit var viewModel: ExpenseViewModel
    lateinit var  fileName:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  enableEdgeToEdge()
        binding=ActivityExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel=ViewModelProvider(this).get(ExpenseViewModel::class.java)

        binding.title.txtHeader.text="Expense"

        binding.title.backBtn.setOnClickListener {
            finish()
        }

        binding.imgChooseFile.setOnClickListener {
            pickFile()

        }
       // Log.d("jyotidata","path========"+selectedImagePath.toString())
        //Log.d("jyotidata","base64===="+selectedImageBase64.toString())


        val expense=resources.getStringArray(R.array.expense)
        val expenseAdapter=ArrayAdapter(this , android.R.layout.simple_list_item_1, expense)
        binding.autoCompleteTxtView.setAdapter(expenseAdapter)
        binding.autoApprovalBy.setAdapter(ArrayAdapter(this,android.R.layout.simple_list_item_1,resources.getStringArray(R.array.approvalBy)))


        binding.btnSave.setOnClickListener {
            val expenseType=binding.autoCompleteTxtView.text.toString()
            Log.d("jyotidata","expenseType===="+expenseType)
            val type2=binding.autoCompleteTxtView
            val amount=binding.edtAmount.text.toString().trim()
            val approveBy=binding.autoApprovalBy.text.toString().trim()
            val reporting=binding.edtAuthority.text.toString().trim()
            if (!Helper.validateEditText(binding.autoCompleteTxtView)
                || !Helper.validateEditText(binding.edtAmount)
                || !Helper.validateEditText(binding.autoApprovalBy)
                || !Helper.validateEditText(binding.edtAuthority)){
                return@setOnClickListener
            }else{
                viewModel.submitExpenseData(ExpenseRequest(approveBy,amount,fileName,selectedImageBase64.toString(),2,expenseType,reporting))
                submitExpenseData()
//                Log.d("jyotidata","base64===="+fileName)
//                Log.d("jyotidata","base64===="+selectedImageBase64.toString())
            }
            binding.edtAmount.text=null
            binding.autoApprovalBy.text=null
            binding.edtAuthority.text=null
            binding.uplaodBill.text=null
            binding.autoCompleteTxtView.text=null
        }



    }
    fun submitExpenseData(){
        viewModel.submitExpenseData.observe(this, Observer {
            Helper.dismissProgressDialog()
            when(it){
                is NetworkResult.Success->{
                    if (it.data?.response == "Success"){
                        Toast.makeText(this,"Expense Save Successfully",Toast.LENGTH_SHORT).show()
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






    private val filePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    handleFile(uri)
                }
            }
        }
    private fun pickFile(){
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*" // You can specify the type of files you want to pick
        }
        filePickerLauncher.launch(intent)
    }
    private fun handleFile(uri: Uri) {
        fileName = getFileName(uri)
        selectedImagePath = uri.toString()
        Log.d("jyotidata","path========"+selectedImagePath.toString())
        selectedImageBase64 = convertFileToBase64(uri)

       // Toast.makeText(this, "File selected: $fileName", Toast.LENGTH_SHORT).show()
        // You can also update your UI to show the selected file name
        binding.uplaodBill.setText(fileName)

    }
    private fun getFileName(uri: Uri): String {
        var name = ""
        contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            name = cursor.getString(nameIndex)
        }
        return name
    }
    private fun convertFileToBase64(uri: Uri): String? {
        return try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            val byteArrayOutputStream = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var bytesRead: Int
            while (inputStream?.read(buffer).also { bytesRead = it ?: -1 } != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead)
            }
            Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode==PICK_FILE_REQUEST_CODE && resultCode== Activity.RESULT_OK){
//            data?.data?.let {
//                val fileName = getFileName(it)
//                binding.uplaodBill.setText(fileName)
//            }
//        }
//    }
//
//    private fun getFileName(uri: Uri): String {
//        var result: String? = null
//        if (uri.scheme == "content") {
//            val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
//            cursor.use {
//                if (it != null && it.moveToFirst()) {
//                    val nameIndex= it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
//                    if (nameIndex >= 0){
//                        result= it.getString(nameIndex)
//                    }
//                    //result = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
//                }
//            }
//        }
//        if (result == null) {
//            result = uri.path
//            val cut = result?.lastIndexOf('/')
//            if (cut != null && cut != -1) {
//                result = result?.substring(cut + 1)
//            }
//        }
//        return result ?: "Unknown file"
//    }
}