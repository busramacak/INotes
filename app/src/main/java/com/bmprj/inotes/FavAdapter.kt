package com.bmprj.inotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bmprj.inotes.databinding.NotesLayoutBinding

class FavAdapter(private val list:ArrayList<Note>)
    :RecyclerView.Adapter<FavAdapter.ViewHolder>() {

        class ViewHolder(private val binding:NotesLayoutBinding)
            :RecyclerView.ViewHolder(binding.root){

                fun bind(note:Note?){
                    binding.note=note
                    binding.executePendingBindings()

                    val dh = DataBaseHelper(itemView.context)

                    binding.favoriCardV.setOnClickListener{
                        if(binding.favoriCardV.isChecked){
                            NotesDAO().updateFav(dh,binding.title.text.toString(),1)
                        }
                        else if(!binding.favoriCardV.isChecked){
                            NotesDAO().updateFav(dh,binding.title.text.toString(),0)
                        }
                        Navigation.findNavController(itemView).navigate(R.id.favNotesFragment)
                    }

                    binding.deleteCardV.setOnClickListener{
                        NotesDAO().deleteNotes(dh,binding.title.text.toString())
                        Navigation.findNavController(itemView).navigate(R.id.favNotesFragment)
                    }

                    binding.noteCardV.setOnClickListener{
                        val title = binding.title?.text.toString()
                        val note = binding.note?.note
                        val gecis = FavNotesFragmentDirections.noteGoToAddNote(title,note)
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
}