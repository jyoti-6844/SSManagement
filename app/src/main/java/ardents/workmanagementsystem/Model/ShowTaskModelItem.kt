package ardents.workmanagementsystem.Model

data class ShowTaskModelItem(
    val Assigned_By: Int,
    val Assigned_Date: Any,
    val Assigned_Date_From: Any,
    val Assigned_Date_To: Any,
    val Assigned_To: Any,
    val Checked_By: Any,
    val Checked_Date: Any,
    val Comments: Any,
    val Remarks: Any,
    val Task_Description: String,
    val Task_Duration: Any,
    val Task_Id: String,
    val Task_Name: String,
    val Task_status: Int,
    val Task_status_Name: Any,
    val response: String
)