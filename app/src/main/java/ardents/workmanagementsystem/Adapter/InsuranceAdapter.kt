package ardents.workmanagementsystem.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ardents.workmanagementsystem.Activity.PdfInsuranceActivity
import ardents.workmanagementsystem.Model.InsuranceModelItem
import ardents.workmanagementsystem.R
import ardents.workmanagementsystem.databinding.InsurancelayBinding

class InsuranceAdapter(val context: Context, var insuranceList: List<InsuranceModelItem>) :
    RecyclerView.Adapter<InsuranceAdapter.ViewHolder>() {
    private val imageResId: Int = R.drawable.arrow
    class ViewHolder(val binding: InsurancelayBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    fun updateList(list: List<InsuranceModelItem>) {
        insuranceList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = InsurancelayBinding.inflate(layoutInflater, parent, false)
        val viewHolder = ViewHolder(binding)
        return viewHolder

    }

    override fun getItemCount(): Int {
        return insuranceList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.sno.text=(position+1).toString()
       holder.binding.expiry.text=insuranceList.get(position).Expired_On
        holder.binding.insuredBy.text=insuranceList.get(position).Insured_By
        holder.binding.vehicleNo.text=insuranceList.get(position).Vehicle_No
        holder.binding.imgNext.setImageResource(imageResId)
        holder.binding.imgNext.setColorFilter(ContextCompat.getColor(context, R.color.red))
//        holder.binding.imgNext.setOnClickListener {
//            val intent=Intent(context,PdfInsuranceActivity::class.java)
//            intent.putExtra("pdfurl",insuranceList.get(position).Insurance_pdf_path)
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            context.startActivity(intent)
//        }
    }
}