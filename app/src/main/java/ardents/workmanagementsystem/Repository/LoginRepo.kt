package ardents.workmanagementsystem.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.workmanagementsystem.Model.LoginModel
import ardents.workmanagementsystem.Model.LoginRequest
import ardents.workmanagementsystem.Netwok.RetrofitClient
import ardents.workmanagementsystem.utils.NetworkResult

class LoginRepo {

    val _loginData=MutableLiveData<NetworkResult<LoginModel>>()
    val loginData:LiveData<NetworkResult<LoginModel>>
        get() = _loginData


    suspend fun getLogin(body:LoginRequest){
        try {
            _loginData.postValue(NetworkResult.Loading())
            val response=RetrofitClient.apiServices.login(body)
            if (response.isSuccessful && response.body()!=null){
                _loginData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _loginData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _loginData.postValue(NetworkResult.Error(e.message))
        }
    }
}