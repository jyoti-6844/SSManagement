package ardents.workmanagementsystem.Model

data class ExpenseVerifyModelItem(
    val Expense_Id:Int,
    val Approval_By: String,
    val Expense_Amount: String,
    val Expense_Type: String,
    val Reporting_Authority: String,
    val Expense_Approval:String,
    val Expense_Bill:String,
    val response: String
)