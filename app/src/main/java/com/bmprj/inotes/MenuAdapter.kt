package com.bmprj.inotes

import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bmprj.inotes.databinding.NotesLayoutBinding


class MenuAdapter(private val list:ArrayList<Note>) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    class ViewHolder(private val binding:NotesLayoutBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(note: Note?) {
                binding.note = note
                binding.executePendingBindings()

                val dh = DataBaseHelper(itemView.context)

                binding.favoriCardV.setOnClickListener {

                    if(binding.favoriCardV.isChecked==true){
                        NotesDAO().updateFav(dh, binding.title.text.toString(), 1)
                    }else if(binding.favoriCardV.isChecked==false){
                        NotesDAO().updateFav(dh, binding.title.text.toString(), 0)
                    }

                    Navigation.findNavController(itemView).navigate(com.bmprj.inotes.R.id.menuFragment)
                }

                binding.deleteCardV.setOnClickListener{

                }
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