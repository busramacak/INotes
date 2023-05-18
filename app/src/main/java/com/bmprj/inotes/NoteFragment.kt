package com.bmprj.inotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bmprj.inotes.databinding.FragmentNoteBinding
import java.util.*


class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    val list = ArrayList<Note>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false)
        binding.noteDesign=this

        return binding.root
    }
    override fun onPause() {
        super.onPause()

        list.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dh = DataBaseHelper(requireContext())
        val noteList = NotesDAO().getNotes(dh)

        for(i in noteList){
            list.add(i)

        }

        if(!list.isEmpty()){
            binding.noteTxt.text=""
        }


        binding.recyV.apply{
            layoutManager = GridLayoutManager(context,2)
            binding.recyV.layoutManager=layoutManager
            adapter = NoteAdapter(list)
            binding.recyV.adapter=adapter
        }
    }

}