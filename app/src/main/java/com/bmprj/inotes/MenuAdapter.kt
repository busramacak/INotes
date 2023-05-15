package com.bmprj.inotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bmprj.inotes.databinding.NotesLayoutBinding

class MenuAdapter(private val list:ArrayList<Note>) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    class ViewHolder(private val binding:NotesLayoutBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(note: Note?){
                binding.note=note
                binding.executePendingBindings()

            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding:NotesLayoutBinding = NotesLayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])

    }

}