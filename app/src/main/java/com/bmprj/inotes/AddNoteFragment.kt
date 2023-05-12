package com.bmprj.inotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bmprj.inotes.databinding.FragmentAddNoteBinding

class AddNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_note, container, false)
        binding.addNoteDesign=this
        return binding.root
    }

    fun backClick(view:View){
        Navigation.findNavController(view).navigate(R.id.noteGoToMenu)

    }

    fun saveClick(view: View){
        Navigation.findNavController(view).navigate(R.id.noteGoToMenu)
    }




}