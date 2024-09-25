package ardents.workmanagementsystem.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ardents.workmanagementsystem.Activity.DailyTaskListStausActivity
import ardents.workmanagementsystem.Model.ShowTaskModelItem
import ardents.workmanagementsystem.databinding.ShowtasklayBinding

class ShowTaskAdapter(val context: Context,var taskList:List<ShowTaskModelItem>): RecyclerView.Adapter<ShowTaskAdapter.ViewHolder>() {
    class ViewHolder(val binding:ShowtasklayBinding):RecyclerView.ViewHolder(binding.root) {

    }
    fun updateList(list:List<ShowTaskModelItem>){
        taskList=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(context)
        val binding=ShowtasklayBinding.inflate(layoutInflater,parent,false)
        val viewHolder=ViewHolder(binding)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.taskName.text=taskList.get(position).Task_Description
        holder.binding.assignTo.text=taskList.get(position).Assigned_To.toString()
        holder.binding.cardtask.setOnClickListener {
            val intent=Intent(context,DailyTaskListStausActivity::class.java)
            intent.putExtra("TaskId",taskList.get(position).Task_Id)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}