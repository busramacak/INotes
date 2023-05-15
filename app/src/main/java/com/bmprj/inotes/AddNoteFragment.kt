package com.bmprj.inotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bmprj.inotes.databinding.FragmentAddNoteBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class AddNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var title:String
    private lateinit var noteEdTxt:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)
        binding.addNoteDesign=this
        return binding.root
    }

    fun backClick(view:View){
        Navigation.findNavController(view).navigate(R.id.noteGoToMenu)

    }

    fun saveClick(view: View){
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        val current = formatter.format(date).toString()

        title=binding.title.text.toString()
        noteEdTxt=binding.noteEdTxt.text.toString()
        val dh = DataBaseHelper(requireContext())
        NotesDAO().addNotes(dh,title,noteEdTxt,current)

        Navigation.findNavController(view).navigate(R.id.noteGoToMenu)
    }
}