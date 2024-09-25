package ardents.workmanagementsystem.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.workmanagementsystem.Model.RegistrationModel
import ardents.workmanagementsystem.Model.RegistrationRequest
import ardents.workmanagementsystem.Netwok.RetrofitClient
import ardents.workmanagementsystem.utils.NetworkResult

class RegistrationRepo {

    val _registrationData=MutableLiveData<NetworkResult<RegistrationModel>>()
    val registrationData:LiveData<NetworkResult<RegistrationModel>>
        get() = _registrationData


    suspend fun getRegistration(body:RegistrationRequest){
        try {
            _registrationData.postValue(NetworkResult.Loading())
            val response=RetrofitClient.apiServices.registration(body)
            if (response.isSuccessful && response.body()!=null){
                _registrationData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _registrationData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _registrationData.postValue(NetworkResult.Error(e.message))
        }
    }
}