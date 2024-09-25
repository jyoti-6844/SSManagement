package ardents.workmanagementsystem.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.workmanagementsystem.Model.InsuranceModelItem
import ardents.workmanagementsystem.Model.InsuranceRequest
import ardents.workmanagementsystem.Repository.InsuranceRepo
import ardents.workmanagementsystem.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InsuranceViewModel:ViewModel() {
    val insuranceRepo=InsuranceRepo()
    val insuranceData:LiveData<NetworkResult<List<InsuranceModelItem>>>
        get() = insuranceRepo.insuranceData

    fun insuranceData(data:InsuranceRequest){
        viewModelScope.launch(Dispatchers.IO) {
            insuranceRepo.getInsuranceData(data)
        }
    }
}