package ardents.workmanagementsystem.Model

data class TaskReportModelItem(
    val Assigned_By: String,
    val Assigned_Date: String,
    val Assigned_Date_From: String,
    val Assigned_Date_To: String,
    val Assigned_To: String,
    val Checked_By: String,
    val Checked_Date: String,
    val Comments: String,
    val Remarks: String,
    val Task_Description: String,
    val Task_Duration: String,
    val Task_Id: String,
    val Task_Name: String,
    val Task_status: String,
    val Task_status_Name: String,
    val response: String
)