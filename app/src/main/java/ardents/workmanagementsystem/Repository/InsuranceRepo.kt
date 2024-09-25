package ardents.workmanagementsystem.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.workmanagementsystem.Model.InsuranceModelItem
import ardents.workmanagementsystem.Model.InsuranceRequest
import ardents.workmanagementsystem.Model.SubmitTaskModel
import ardents.workmanagementsystem.Netwok.RetrofitClient
import ardents.workmanagementsystem.utils.NetworkResult

class InsuranceRepo {

    val _insuranceData= MutableLiveData<NetworkResult<List<InsuranceModelItem>>>()
    val insuranceData: LiveData<NetworkResult<List<InsuranceModelItem>>>
        get() = _insuranceData

    suspend fun getInsuranceData(data:InsuranceRequest){
        try {
            _insuranceData.postValue(NetworkResult.Loading())
            val response=RetrofitClient.apiServices.insuranceData(data)
            if (response.isSuccessful && response.body()!=null){
                _insuranceData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _insuranceData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _insuranceData.postValue(NetworkResult.Error(e.message))
        }
    }

}