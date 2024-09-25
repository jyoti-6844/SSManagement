package ardents.workmanagementsystem.utils

import android.app.ProgressDialog
import android.content.Context
import android.util.Patterns
import android.view.View
import android.widget.EditText

object Helper {
     var progressDialog:ProgressDialog?=null
    fun showProgressDialog(context: Context){
        progressDialog = ProgressDialog(context)
        progressDialog?.setMessage("Loading....")
        progressDialog?.setCancelable(false)
        progressDialog?.show()
        //return progressDialog
    }


    fun dismissProgressDialog() {
        progressDialog?.let {
            if (it.isShowing){
                progressDialog?.dismiss()
            }
        }
        progressDialog=null
    }

    fun validateEditText(editText: EditText): Boolean {
        val validateText = editText.text.toString()
        editText.visibility = View.VISIBLE
        return if (validateText.isEmpty()) {
            editText.error = "This field is required"
            false
        } else {
            editText.error = null
            true
        }
    }




    fun isValidMail(editText: EditText):Boolean{
        val validateText=editText.text.toString().trim()
        editText.visibility = View.VISIBLE
        return if (validateText.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(validateText).matches()){
           editText.error="Not a valid mail"
            false
        }
        else{
            editText.error=null
            true
        }


    }

}