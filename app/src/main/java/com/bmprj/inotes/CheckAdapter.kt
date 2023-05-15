package com.bmprj.inotes

import android.R
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bmprj.inotes.databinding.CheckListLayoutBinding


class CheckAdapter(private val list:ArrayList<Check>) : RecyclerView.Adapter<CheckAdapter.ViewHolder>(){
    class ViewHolder(private val binding:CheckListLayoutBinding):
            RecyclerView.ViewHolder(binding.root){

                fun bind(check: Check?){
                    binding.check=check
                    binding.executePendingBindings()
                    val db = DataBaseHelper(binding.root.context)

                    binding.checkBox.setOnClickListener{
                        if(binding.checkBox.isChecked==true){
                            ChecksDAO().updateChecks(db,binding.checkBox.text.toString(),1)
                            Navigation.findNavController(itemView).navigate(com.bmprj.inotes.R.id.addCheckListFragment)


                        }
                        else if(binding.checkBox.isChecked==false){
                            ChecksDAO().updateChecks(db,binding.checkBox.text.toString(),0)
                            Navigation.findNavController(itemView).navigate(com.bmprj.inotes.R.id.addCheckListFragment)

                        }
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

}