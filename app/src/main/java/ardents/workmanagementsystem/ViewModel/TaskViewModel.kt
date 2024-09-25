package ardents.workmanagementsystem.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ardents.workmanagementsystem.Model.ShowTaskModelItem
import ardents.workmanagementsystem.Model.SubmitTaskModel
import ardents.workmanagementsystem.Model.SubmitTaskRequest
import ardents.workmanagementsystem.Model.TaskReportModelItem
import ardents.workmanagementsystem.Model.TaskReportRequest
import ardents.workmanagementsystem.Model.UpdateTaskModel
import ardents.workmanagementsystem.Model.UpdateTaskRequest
import ardents.workmanagementsystem.Repository.TaskRepo
import ardents.workmanagementsystem.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel:ViewModel() {
    val taskRepo=TaskRepo()
    val submitTaskData:LiveData<NetworkResult<SubmitTaskModel>>
        get() = taskRepo.submitTaskData

    val showTaskData:LiveData<NetworkResult<List<ShowTaskModelItem>>>
        get() = taskRepo.showTaskData

    val updateTaskData:LiveData<NetworkResult<UpdateTaskModel>>
        get() = taskRepo.updateTaskData

    val taskReportData:LiveData<NetworkResult<List<TaskReportModelItem>>>
        get() = taskRepo.taskReportData


    fun taskData(data:SubmitTaskRequest){
        viewModelScope.launch(Dispatchers.IO) {
            taskRepo.getTaskData(data)
        }

    }


    fun showTaskData(){
        viewModelScope.launch(Dispatchers.IO) {
            taskRepo.getShowTaskData()
        }
    }


    fun updateTaskData(data:UpdateTaskRequest){
        viewModelScope.launch(Dispatchers.IO) {
            taskRepo.getUpdateTaskData(data)
        }
    }

    fun taskReportData(data:TaskReportRequest){
        viewModelScope.launch(Dispatchers.IO) {
            taskRepo.getTaskReportData(data)
        }
    }
}