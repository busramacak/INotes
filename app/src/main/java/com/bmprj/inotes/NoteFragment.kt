package com.bmprj.inotes

import android.content.Intent
import android.content.pm.ActivityInfo
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
    var spanCount:Int=2
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
    fun settingClick(){
        startActivity(Intent(context,SettingsActivity::class.java))
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
            layoutManager = GridLayoutManager(context,resources.getInteger(R.integer.grid_column_count))
            binding.recyV.layoutManager=layoutManager
            adapter = NoteAdapter(list)
            binding.recyV.adapter=adapter
        }
    }

}