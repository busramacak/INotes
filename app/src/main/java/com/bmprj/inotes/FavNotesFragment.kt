package com.bmprj.inotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.bmprj.inotes.databinding.FragmentFavNotesBinding


class FavNotesFragment : Fragment() {
    private lateinit var binding:FragmentFavNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_fav_notes, container, false)
        binding.favDesign=this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dh = DataBaseHelper(requireContext())
        val favNoteList = NotesDAO().getNotes(dh)
        val list = ArrayList<Note>()
        for(i in favNoteList){
            if(i.note_fav==1){
                list.add(i)
            }
        }

        binding.recyVFav.apply {
            layoutManager=GridLayoutManager(context,2)
            binding.recyVFav.layoutManager=layoutManager
            adapter=NoteAdapter(list)
            binding.recyVFav.adapter=adapter
        }
    }


}