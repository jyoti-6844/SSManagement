package ardents.workmanagementsystem.Model

data class ExpenseRequest(
    val Approval_By: String,
    val Expense_Amount: String,
    val Expense_Bill: String,
    val Expense_Bill_Base64: String,
    val Expense_Id: Int,
    val Expense_Type: String,
    val Reporting_Authority: String
)