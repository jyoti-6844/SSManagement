package ardents.workmanagementsystem.Model

data class UpdateTaskRequest(
    val Checked_By: String,
    val Remarks: String,
    val Task_Id: String,
    val Task_status_Name: String
)