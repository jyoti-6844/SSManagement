package ardents.workmanagementsystem.Model

data class  LoginModel(
    val Comments: String,
    val Created_By: Int,
    val Created_date: String,
    val LoginStatus: Int,
    val Login_Email: String,
    val Person_Name: String,
    val Person_Mobile: String,
    val Login_Id: Int,
    val Login_Password: String,
    val Modified_By: Int,
    val Modified_Date: String,
    val PersonDetails_Id: Int,
    val Role_Id: Int,
    val response: String
)