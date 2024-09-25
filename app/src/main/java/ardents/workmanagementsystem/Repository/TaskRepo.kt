package ardents.workmanagementsystem.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ardents.workmanagementsystem.Model.ShowTaskModelItem
import ardents.workmanagementsystem.Model.SubmitTaskModel
import ardents.workmanagementsystem.Model.SubmitTaskRequest
import ardents.workmanagementsystem.Model.TaskReportModelItem
import ardents.workmanagementsystem.Model.TaskReportRequest
import ardents.workmanagementsystem.Model.UpdateTaskModel
import ardents.workmanagementsystem.Model.UpdateTaskRequest
import ardents.workmanagementsystem.Netwok.RetrofitClient
import ardents.workmanagementsystem.utils.NetworkResult

class TaskRepo {

    val _submitTaskData=MutableLiveData<NetworkResult<SubmitTaskModel>>()
    val submitTaskData:LiveData<NetworkResult<SubmitTaskModel>>
        get() = _submitTaskData


    val _showTaskData=MutableLiveData<NetworkResult<List<ShowTaskModelItem>>>()
    val showTaskData:LiveData<NetworkResult<List<ShowTaskModelItem>>>
        get() = _showTaskData


    val _updateTaskData=MutableLiveData<NetworkResult<UpdateTaskModel>>()
    val updateTaskData:LiveData<NetworkResult<UpdateTaskModel>>
        get() = _updateTaskData


    val _taskReportData=MutableLiveData<NetworkResult<List<TaskReportModelItem>>>()
    val taskReportData:LiveData<NetworkResult<List<TaskReportModelItem>>>
        get() = _taskReportData


    suspend fun getTaskData(data:SubmitTaskRequest){
        try {
            _submitTaskData.postValue(NetworkResult.Loading())
            val response=RetrofitClient.apiServices.submitTask(data)
            if (response.isSuccessful && response.body()!=null){
                _submitTaskData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _submitTaskData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _submitTaskData.postValue(NetworkResult.Error(e.message))
        }

    }


    suspend fun getShowTaskData(){
        try {
            _showTaskData.postValue(NetworkResult.Loading())
            val response=RetrofitClient.apiServices.showtaskData()
            if (response.isSuccessful && response.body()!=null){
                _showTaskData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _showTaskData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _showTaskData.postValue(NetworkResult.Error(e.message))
        }

    }


    suspend fun getUpdateTaskData(data:UpdateTaskRequest){
        try {
            _updateTaskData.postValue(NetworkResult.Loading())
            val response=RetrofitClient.apiServices.updateTask(data)
            if (response.isSuccessful && response.body()!=null){
                _updateTaskData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _updateTaskData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _updateTaskData.postValue(NetworkResult.Error(e.message))
        }

    }


    suspend fun getTaskReportData(data:TaskReportRequest){
        try {
            _taskReportData.postValue(NetworkResult.Loading())
            val response=RetrofitClient.apiServices.taskReport(data)
            if (response.isSuccessful && response.body()!=null){
                _taskReportData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _taskReportData.postValue(NetworkResult.Error(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            _taskReportData.postValue(NetworkResult.Error(e.message))
        }

    }
}