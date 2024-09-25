package ardents.workmanagementsystem.Model

data class SubmitTaskRequest(
    val Assigned_By: String,
    val Assigned_To: String,
    val Task_Description: String,
    val Task_Duration: String,
    val Task_Name: String
)