package ardents.workmanagementsystem.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.workmanagementsystem.Model.RegistrationModel
import ardents.workmanagementsystem.Model.RegistrationRequest
import ardents.workmanagementsystem.Repository.RegistrationRepo
import ardents.workmanagementsystem.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel:ViewModel(){
    val registrationRepo=RegistrationRepo()
    val registrationData:LiveData<NetworkResult<RegistrationModel>>
        get() = registrationRepo._registrationData

    fun registration(body:RegistrationRequest){
        viewModelScope.launch(Dispatchers.IO) {
            registrationRepo.getRegistration(body)
        }
    }
}