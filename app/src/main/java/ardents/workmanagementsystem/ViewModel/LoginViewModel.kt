package ardents.workmanagementsystem.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.workmanagementsystem.Model.LoginModel
import ardents.workmanagementsystem.Model.LoginRequest
import ardents.workmanagementsystem.Repository.LoginRepo
import ardents.workmanagementsystem.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {
    val loginRepo=LoginRepo()
    val loginData:LiveData<NetworkResult<LoginModel>>
        get() = loginRepo._loginData


    fun loginData(body:LoginRequest){
        viewModelScope.launch(Dispatchers.IO) {
            loginRepo.getLogin(body)

        }
    }
}