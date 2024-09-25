package ardents.workmanagementsystem.utils

import android.content.Context
import ardents.workmanagementsystem.Model.LoginModel
import com.google.gson.Gson

class SharedPrefManager(context:Context) {

    private val SHARED_PREF_LOGIN="login"
    val LOGIN_RESPONSE="response"
    val LOGIN_MAIL="email"
    var mContext: Context
    private val gson = Gson()
    init {
        mContext=context
    }
    companion object{
        private var mInstance:SharedPrefManager?=null

        @Synchronized
        fun getInstance(context: Context):SharedPrefManager{
            if (mInstance==null){
                mInstance= SharedPrefManager(context)
            }
            return mInstance!!
        }
    }

    fun setLoginResponse(response:LoginModel){
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_LOGIN,Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        val json=gson.toJson(response)
        editor.putString(LOGIN_RESPONSE,json)
        editor.apply()
    }

    fun setLoginMail(mail:String){
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_LOGIN,Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.putString(LOGIN_MAIL,mail)
        editor.apply()
    }

    fun getLoginResponse():LoginModel? {
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_LOGIN,Context.MODE_PRIVATE)
        val json=sharedPreferences.getString(LOGIN_RESPONSE,null)
      //  val response=sharedPreferences.getString(LOGIN_RESPONSE,null)
        return if (json != null) {
            try {
                gson.fromJson(json, LoginModel::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } else {
            null
        }
    }
    fun getLoginMail():String? {
        val sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_LOGIN,Context.MODE_PRIVATE)
        //  val response=sharedPreferences.getString(LOGIN_RESPONSE,null)
        return sharedPreferences.getString(LOGIN_MAIL,null)
    }

    fun clearLoginResponse() {
        val sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_LOGIN, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear().commit()
        editor.apply()

    }

}
