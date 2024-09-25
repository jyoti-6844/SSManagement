package ardents.workmanagementsystem.Netwok

import ardents.workmanagementsystem.Model.ExpenseModel
import ardents.workmanagementsystem.Model.ExpenseRequest
import ardents.workmanagementsystem.Model.ExpenseVerifyModelItem
import ardents.workmanagementsystem.Model.InsuranceModelItem
import ardents.workmanagementsystem.Model.InsuranceRequest
import ardents.workmanagementsystem.Model.LoginModel
import ardents.workmanagementsystem.Model.LoginRequest
import ardents.workmanagementsystem.Model.RegistrationModel
import ardents.workmanagementsystem.Model.RegistrationRequest
import ardents.workmanagementsystem.Model.ShowTaskModelItem
import ardents.workmanagementsystem.Model.SubmitTaskModel
import ardents.workmanagementsystem.Model.SubmitTaskRequest
import ardents.workmanagementsystem.Model.TaskReportModelItem
import ardents.workmanagementsystem.Model.TaskReportRequest
import ardents.workmanagementsystem.Model.UpdateTaskModel
import ardents.workmanagementsystem.Model.UpdateTaskRequest
import ardents.workmanagementsystem.Model.VerifyModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {


    @POST("api/Registration")
    suspend fun registration(
        @Body body:RegistrationRequest
    ):Response<RegistrationModel>

    @POST("api/Login")
    suspend fun login(
        @Body body:LoginRequest
    ):Response<LoginModel>

    @POST("api/RegisterTask")
    suspend fun submitTask(
        @Body body:SubmitTaskRequest
    ):Response<SubmitTaskModel>

    @POST("api/Insurance")
    suspend fun insuranceData(
        @Body body:InsuranceRequest
    ):Response<List<InsuranceModelItem>>

    @POST("api/ShowTask")
    suspend fun showtaskData():Response<List<ShowTaskModelItem>>

    @POST("api/VerifyExpense")
    suspend fun expenseData():Response<List<ExpenseVerifyModelItem>>

    @POST("api/Expense")
    suspend fun registerExpense(
        @Body body:ExpenseRequest
    ):Response<ExpenseModel>

    @POST("api/UpdateTask")
    suspend fun updateTask(
        @Body body:UpdateTaskRequest
    ):Response<UpdateTaskModel>

    @POST("api/Reports")
    suspend fun taskReport(
        @Body body:TaskReportRequest
    ):Response<List<TaskReportModelItem>>

    @FormUrlEncoded
    @POST("api/ExpenceVerify")
    suspend fun verify(
        @Field("Expense_Id") Expense_Id:Int,@Field("Expense_Approval") Expense_Approval:String,@Field("Expense_Remark") Expense_Remark:String
    ):Response<VerifyModel>

}