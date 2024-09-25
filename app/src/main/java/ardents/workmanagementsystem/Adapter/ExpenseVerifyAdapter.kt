package ardents.workmanagementsystem.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ardents.workmanagementsystem.Activity.PdfInsuranceActivity
import ardents.workmanagementsystem.Activity.VerifyActivity
import ardents.workmanagementsystem.Model.ExpenseVerifyModelItem
import ardents.workmanagementsystem.databinding.ExpenseverifylayBinding


class ExpenseVerifyAdapter(val context: Context,var expenseList:List<ExpenseVerifyModelItem>): RecyclerView.Adapter<ExpenseVerifyAdapter.ViewHolder>() {
    class ViewHolder(val binding:ExpenseverifylayBinding):RecyclerView.ViewHolder(binding.root){

    }

    fun updateList(list:List<ExpenseVerifyModelItem>){
        expenseList=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(context)
        val binding=ExpenseverifylayBinding.inflate(layoutInflater,parent,false)
        val viewHolder=ViewHolder(binding)
        return viewHolder
    }

    override fun getItemCount(): Int {
       return expenseList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.expenseType.text=expenseList.get(position).Expense_Type
        holder.binding.approvalBy.text=expenseList.get(position).Approval_By
        holder.binding.price.text=expenseList.get(position).Expense_Amount
       // holder.binding.showPdf.text="https://ssapi.marmelos.co.in"+expenseList.get(position).Expense_Bill
        if (expenseList.get(position).Expense_Approval.isNullOrEmpty()){
            holder.binding.btnVerify.visibility=View.VISIBLE
            holder.binding.txtStatus.visibility=View.GONE
        }else{
            holder.binding.btnVerify.visibility=View.GONE
            holder.binding.txtStatus.visibility=View.VISIBLE
            holder.binding.txtStatus.text=expenseList.get(position).Expense_Approval
        }
        holder.binding.showPdf.setOnClickListener {
            val intent=Intent(context,PdfInsuranceActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("url",expenseList.get(position).Expense_Bill)
            context.startActivity(intent)
        }

        holder.binding.btnVerify.setOnClickListener {
            val intent=Intent(context,VerifyActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("Expense_Id",expenseList.get(position).Expense_Id.toString())
            context.startActivity(intent)
        }
    }

}