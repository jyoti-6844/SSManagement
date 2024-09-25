package ardents.workmanagementsystem.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ardents.workmanagementsystem.Model.TaskReportModelItem
import ardents.workmanagementsystem.databinding.ReportStatusLayBinding

class ReportStatusAdapter(val context: Context,var taskReportList:List<TaskReportModelItem>): RecyclerView.Adapter<ReportStatusAdapter.ViewHolder>() {
    class ViewHolder(val binding:ReportStatusLayBinding):RecyclerView.ViewHolder(binding.root){

    }
    fun updateList(list:List<TaskReportModelItem>){
        taskReportList=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=ReportStatusLayBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return taskReportList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.binding.taskStatus.text=taskReportList.get(position).Task_status_Name
        holder.binding.taskName.text=taskReportList.get(position).Task_Description
    }
}