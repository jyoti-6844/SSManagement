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
import ardents.workmanagementsystem.Model.RegistrationRequest
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.ViewModel.RegistrationViewModel
import ardents.workmanagementsystem.databinding.ActivityRegisterBinding
import ardents.workmanagementsystem.utils.Helper
import ardents.workmanagementsystem.utils.NetworkResult

class RegisterActivity : AppCompatActivity() {
    lateinit var binding:ActivityRegisterBinding
    lateinit var viewModel:RegistrationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  enableEdgeToEdge()
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel=ViewModelProvider(this).get(RegistrationViewModel::class.java)
        binding.btnRegister.setOnClickListener {
            val name=binding.edtUsername.text.toString().trim()
            val phone=binding.edtPhone.text.toString().trim()
            val mail=binding.edtMail.text.toString().trim()
            val password=binding.edtPasswd.text.toString().trim()
            if (!Helper.validateEditText(binding.edtUsername)||
                !Helper.validateEditText(binding.edtPhone)||
                !Helper.validateEditText(binding.edtPasswd)||
                !Helper.isValidMail(binding.edtMail))
            {
                return@setOnClickListener
            }else{
                viewModel.registration(RegistrationRequest("jckjfd",mail,phone,name,password))
            }
            binding.edtUsername.text=null
            binding.edtMail.text=null
            binding.edtPasswd.text=null
            binding.edtPhone.text=null
        }
        viewModel.registrationData.observe(this, Observer {
            Helper.dismissProgressDialog()
            when(it){
                is NetworkResult.Success ->{
                    if (it.data?.response == "Success"){
                        Toast.makeText(this,"Register Successfully",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext,MainActivity::class.java))
                        finish()
                    }
                }
                is NetworkResult.Error ->{
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading ->{
                    Helper.showProgressDialog(this)
                }else ->{
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
                }
            }

        })
        binding.backBtn.setOnClickListener {
           finish()
        }
        binding.txtLogin.setOnClickListener {
            startActivity(Intent(applicationContext,LoginActivity::class.java))
            finish()
        }
    }
}