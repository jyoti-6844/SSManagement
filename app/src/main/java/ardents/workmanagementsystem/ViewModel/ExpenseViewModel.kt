package ardents.workmanagementsystem.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.workmanagementsystem.Model.ExpenseModel
import ardents.workmanagementsystem.Model.ExpenseRequest
import ardents.workmanagementsystem.Model.ExpenseVerifyModelItem
import ardents.workmanagementsystem.Model.InsuranceModelItem
import ardents.workmanagementsystem.Model.InsuranceRequest
import ardents.workmanagementsystem.Model.VerifyModel
import ardents.workmanagementsystem.Repository.ExpenseRepo
import ardents.workmanagementsystem.Repository.InsuranceRepo
import ardents.workmanagementsystem.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseViewModel: ViewModel() {

    val expenseRepo= ExpenseRepo()
    val expenseData: LiveData<NetworkResult<List<ExpenseVerifyModelItem>>>
        get() = expenseRepo.expenseData

    val submitExpenseData: LiveData<NetworkResult<ExpenseModel>>
        get() = expenseRepo.submitExpenseData

    val verifyData: LiveData<NetworkResult<VerifyModel>>
        get() = expenseRepo.verifyData

    fun expenseData(){
        viewModelScope.launch(Dispatchers.IO) {
           expenseRepo.getExpenseData()
        }
    }


    fun submitExpenseData(data:ExpenseRequest){
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepo.getSubmitExpenseData(data)
        }
    }

    fun verifydata(id:Int,approval:String,remark:String){
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepo.getVerifyData(id, approval, remark)
        }
    }
}