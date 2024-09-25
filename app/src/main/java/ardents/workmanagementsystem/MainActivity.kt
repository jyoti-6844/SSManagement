package ardents.workmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ardents.workmanagementsystem.Activity.BaseActivity
import ardents.workmanagementsystem.Activity.DailyTaskActivity
import ardents.workmanagementsystem.Activity.ExpenseActivity
import ardents.workmanagementsystem.Activity.ExpenseVerifyActivity
import ardents.workmanagementsystem.Activity.InsuranceActivity
import ardents.workmanagementsystem.Activity.LoginActivity
import ardents.workmanagementsystem.Activity.ProfileActivity
import ardents.workmanagementsystem.Activity.ReportActivity
import ardents.workmanagementsystem.Activity.UpdateWorkActivity
import ardents.workmanagementsystem.databinding.ActivityMainBinding
import ardents.workmanagementsystem.utils.SharedPrefManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (SharedPrefManager.getInstance(this).getLoginMail().equals("fo@ss.com")){
            binding.cardVerifyExpense.visibility=View.VISIBLE
        }else if (SharedPrefManager.getInstance(this).getLoginMail().equals("md@ss.com")){
            binding.cardVerifyExpense.visibility=View.VISIBLE
            binding.cardReport.visibility=View.VISIBLE
            binding.cardInsurance.visibility=View.VISIBLE
            binding.cardUpdatework.visibility=View.VISIBLE
            binding.cardCreatework.visibility=View.VISIBLE
            binding.cardExpense.visibility=View.VISIBLE
        }else if (SharedPrefManager.getInstance(this).getLoginMail().equals("sachin@ss.com") ||
            SharedPrefManager.getInstance(this).getLoginMail().equals("lnshukla@ss.com") ||
            SharedPrefManager.getInstance(this).getLoginMail().equals("sanjay@ss.com") ||
            SharedPrefManager.getInstance(this).getLoginMail().equals("shubham@ss.com") ||
            SharedPrefManager.getInstance(this).getLoginMail().equals("virendra@gmail.com")||
            SharedPrefManager.getInstance(this).getLoginMail().equals("Surya@gmail.com")||
            SharedPrefManager.getInstance(this).getLoginMail().equals("Shivam@gmail.com")
            ){
            binding.cardReport.visibility=View.VISIBLE
            binding.cardCreatework.visibility=View.VISIBLE
            binding.cardUpdatework.visibility=View.VISIBLE
            binding.cardExpense.visibility=View.VISIBLE
            binding.cardInsurance.visibility=View.VISIBLE
        }else{
            binding.cardUpdatework.visibility=View.VISIBLE
            binding.cardExpense.visibility=View.VISIBLE
            binding.cardInsurance.visibility=View.VISIBLE
            binding.cardCreatework.visibility=View.VISIBLE
        }


        binding.cardReport.setOnClickListener {
            startActivity(Intent(applicationContext, ReportActivity::class.java))
        }
        binding.cardCreatework.setOnClickListener {
            startActivity(Intent(applicationContext, DailyTaskActivity::class.java))
        }
        binding.cardExpense.setOnClickListener {
            startActivity(Intent(applicationContext, ExpenseActivity::class.java))
        }
        binding.cardUpdatework.setOnClickListener {
            startActivity(Intent(applicationContext, UpdateWorkActivity::class.java))
        }
        binding.cardVerifyExpense.setOnClickListener {
            startActivity(Intent(applicationContext, ExpenseVerifyActivity::class.java))
        }
        binding.cardInsurance.setOnClickListener {
            startActivity(Intent(applicationContext, InsuranceActivity::class.java))
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_profile -> {
                    val intent=Intent(applicationContext,ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.bottom_logout -> {
                    showDialog()
                    true
                }

                else -> {
                    true
                }
            }
        }

        val sliderList = ArrayList<SlideModel>()
        sliderList.add(SlideModel(R.drawable.s3, ScaleTypes.FIT))
        sliderList.add(SlideModel(R.drawable.s1, ScaleTypes.FIT))
        sliderList.add(SlideModel(R.drawable.s2, ScaleTypes.FIT))
        sliderList.add(SlideModel(R.drawable.s4, ScaleTypes.FIT))
        binding.imageSlider.setImageList(sliderList)


    }

    fun showDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Logout")
        dialog.setMessage("Are you sure want to logout ?")
        dialog.setPositiveButton("Yes") { dialog, _ ->
            SharedPrefManager.getInstance(this).clearLoginResponse()
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()

        }
        dialog.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        dialog.setCancelable(false)
        dialog.create()
        dialog.show()

    }
}
