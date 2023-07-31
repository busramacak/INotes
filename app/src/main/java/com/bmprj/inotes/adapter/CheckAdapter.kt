package com.bmprj.inotes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bmprj.inotes.data.dao.ChecksDAO
import com.bmprj.inotes.data.DataBaseHelper
import com.bmprj.inotes.R
import com.bmprj.inotes.databinding.CheckListLayoutBinding
import com.bmprj.inotes.model.Check


class CheckAdapter(private val list:ArrayList<Check>) : RecyclerView.Adapter<CheckAdapter.ViewHolder>(){
    class ViewHolder(private val binding:CheckListLayoutBinding):
            RecyclerView.ViewHolder(binding.root){

                fun bind(check: Check?){
                    binding.check=check
                    binding.executePendingBindings()
                    val db = DataBaseHelper(binding.root.context)

                    binding.checkBox.setOnClickListener{
                        if(binding.checkBox.isChecked){

                            ChecksDAO().updateChecks(db,binding.check?.check_id?.toInt(),1)


                        }
                        else if(!binding.checkBox.isChecked){

                            ChecksDAO().updateChecks(db,binding.check?.check_id?.toInt(),0)
                        }
                        Navigation.findNavController(itemView).navigate(R.id.addCheckListFragment)
                    }

                    binding.deleteCheck.setOnClickListener{
                        ChecksDAO().deleteChecks(db,binding.check?.check_id?.toInt())
                        Navigation.findNavController(itemView).navigate(R.id.addCheckListFragment)
                    }

                }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding:CheckListLayoutBinding= CheckListLayoutBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(itemBinding)
    }


    override fun getItemCount(): Int {
        return list.size
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList:ArrayList<Check>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

}

