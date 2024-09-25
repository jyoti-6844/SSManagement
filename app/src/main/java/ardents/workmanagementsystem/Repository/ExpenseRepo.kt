package ardents.workmanagementsystem.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.workmanagementsystem.Model.ExpenseModel
import ardents.workmanagementsystem.Model.ExpenseRequest
import ardents.workmanagementsystem.Model.ExpenseVerifyModelItem
import ardents.workmanagementsystem.Model.InsuranceModelItem
import ardents.workmanagementsystem.Model.InsuranceRequest
import ardents.workmanagementsystem.Model.VerifyModel
import ardents.workmanagementsystem.Netwok.RetrofitClient
import ardents.workmanagementsystem.utils.NetworkResult

class ExpenseRepo {
    val _expenseData= MutableLiveData<NetworkResult<List<ExpenseVerifyModelItem>>>()
    val expenseData: LiveData<NetworkResult<List<ExpenseVerifyModelItem>>>
        get() = _expenseData

    val _submitExpenseData= MutableLiveData<NetworkResult<ExpenseModel>>()
    val submitExpenseData: LiveData<NetworkResult<ExpenseModel>>
        get() = _submitExpenseData

    val _verifyData= MutableLiveData<NetworkResult<VerifyModel>>()
    val verifyData: LiveData<NetworkResult<VerifyModel>>
        get() = _verifyData

    suspend fun getExpenseData(){
        try {
            _expenseData.postValue(NetworkResult.Loading())
            val response= RetrofitClient.apiServices.expenseData()
            if (response.isSuccessful && response.body()!=null){
                _expenseData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _expenseData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _expenseData.postValue(NetworkResult.Error(e.message))
        }
    }



    suspend fun getSubmitExpenseData(data:ExpenseRequest){
        try {
            _submitExpenseData.postValue(NetworkResult.Loading())
            val response= RetrofitClient.apiServices.registerExpense(data)
            if (response.isSuccessful && response.body()!=null){
                _submitExpenseData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _submitExpenseData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _submitExpenseData
                .postValue(NetworkResult.Error(e.message))
        }
    }


    suspend fun getVerifyData(id:Int,approval:String,remark:String){
        try {
            _verifyData.postValue(NetworkResult.Loading())
            val response= RetrofitClient.apiServices.verify(id,approval,remark)
            if (response.isSuccessful && response.body()!=null){
                _verifyData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _verifyData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _verifyData.postValue(NetworkResult.Error(e.message))
        }
    }
}