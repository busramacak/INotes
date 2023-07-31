package com.bmprj.inotes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bmprj.inotes.data.DataBaseHelper
import com.bmprj.inotes.data.dao.NotesDAO
import com.bmprj.inotes.R
import com.bmprj.inotes.databinding.NotesLayoutBinding
import com.bmprj.inotes.model.Note
import com.bmprj.inotes.view.fragment.FavNotesFragmentDirections

@Suppress("NAME_SHADOWING")
class FavAdapter(private val list:ArrayList<Note>)
    :RecyclerView.Adapter<FavAdapter.ViewHolder>() {

        class ViewHolder(private val binding:NotesLayoutBinding)
            :RecyclerView.ViewHolder(binding.root){

                fun bind(note: Note?){
                    binding.note=note
                    binding.executePendingBindings()

                    val dh = DataBaseHelper(itemView.context)

                    binding.favoriCardV.setOnClickListener{
                        if(binding.favoriCardV.isChecked){
                            NotesDAO().updateFav(dh,binding.note?.note_id?.toInt(),1)
                        }
                        else if(!binding.favoriCardV.isChecked){
                            NotesDAO().updateFav(dh,binding.note?.note_id?.toInt(),0)
                        }
                        Navigation.findNavController(itemView).navigate(R.id.favNotesFragment)
                    }

                    binding.deleteCardV.setOnClickListener{
                        NotesDAO().deleteNotes(dh,binding.note?.note_id?.toInt())
                        Navigation.findNavController(itemView).navigate(R.id.favNotesFragment)
                    }

                    binding.noteCardV.setOnClickListener{
                        val note_id=binding.note?.note_id
                        val title = binding.note?.note_title
                        val note = binding.note?.note
                        val gecis = FavNotesFragmentDirections.noteGoToAddNote(note_id, title, note)
                        Navigation.findNavController(itemView).navigate(gecis)
                    }
                }


            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val itemBinding:NotesLayoutBinding = NotesLayoutBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList:ArrayList<Note>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}