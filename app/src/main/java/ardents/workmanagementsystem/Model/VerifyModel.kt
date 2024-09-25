package ardents.workmanagementsystem.Model

data class VerifyModel(
    val Approval_By: String,
    val Comments: String,
    val Created_By: String,
    val Created_Date: String,
    val Expense_Amount: String,
    val Expense_Approval: String,
    val Expense_Bill: String,
    val Expense_Bill2: String,
    val Expense_Bill_Base64: String,
    val Expense_Id: Int,
    val Expense_Remark: String,
    val Expense_Status: Int,
    val Expense_Type: String,
    val Modified_By: String,
    val Modified_Date: String,
    val Reporting_Authority: String,
    val response: String
)