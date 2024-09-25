package ardents.workmanagementsystem.Activity

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ardents.workmanagementsystem.MainActivity
import ardents.workmanagementsystem.Model.LoginRequest
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.ViewModel.LoginViewModel
import ardents.workmanagementsystem.databinding.ActivityLoginBinding
import ardents.workmanagementsystem.utils.Helper
import ardents.workmanagementsystem.utils.NetworkResult
import ardents.workmanagementsystem.utils.SharedPrefManager

class LoginActivity :BaseActivity() {
    lateinit var  binding:ActivityLoginBinding
    lateinit var viewModel:LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel=ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            val mail=binding.edtMail.text.toString().trim()
            val password=binding.edtPass.text.toString().trim()
            if (!Helper.isValidMail(binding.edtMail)||
                !Helper.validateEditText(binding.edtPass)
                ){
                return@setOnClickListener
            }else{
                viewModel.loginData(LoginRequest(mail,password))
            }
            binding.edtPass.text=null
            binding.edtMail.text=null

        }
        viewModel.loginData.observe(this, Observer {
            Helper.dismissProgressDialog()
            when(it){
                is NetworkResult.Success ->{
                    if (it.data?.response == "Success"){
                        SharedPrefManager.getInstance(this).setLoginResponse(it.data)
                        SharedPrefManager.getInstance(this).setLoginMail(it.data.Login_Email)
                        Toast.makeText(this,"Login Successfull",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext,MainActivity::class.java))
                        finish()
                    }else{

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


        binding.txtRegister.setOnClickListener {
            startActivity(Intent(applicationContext,RegisterActivity::class.java))
        }

        binding.hideLoginPasswd.setOnClickListener {
            var cursorPosition:Int=binding.edtPass.selectionStart
            binding.edtPass.transformationMethod=HideReturnsTransformationMethod.getInstance()
            binding.hideLoginPasswd.visibility=View.GONE
            binding.showLoginPasswd.visibility=View.VISIBLE
            binding.edtPass.setSelection(cursorPosition)
        }

        binding.showLoginPasswd.setOnClickListener {
            var cusorPosition:Int=binding.edtPass.selectionStart
            binding.edtPass.transformationMethod=PasswordTransformationMethod.getInstance()
            binding.showLoginPasswd.visibility=View.GONE
            binding.hideLoginPasswd.visibility=View.VISIBLE
            binding.edtPass.setSelection(cusorPosition)
        }

    }

}