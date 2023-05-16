package com.bmprj.inotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bmprj.inotes.databinding.FragmentAddNoteBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class AddNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var title:String
    private lateinit var noteEdTxt:String
    var isUpdate=false
    val bundle:AddNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)
        binding.addNoteDesign=this


        binding.title.setText(bundle.title)
        binding.noteEdTxt.setText(bundle.note)
        isUpdate=true

        return binding.root
    }

    fun backClick(view:View){


        Navigation.findNavController(view).navigate(R.id.action_addNoteFragment_pop)

    }


    fun saveClick(view: View){

        val dh = DataBaseHelper(requireContext())
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd-MMMM-yyyy")
        val current = formatter.format(date).toString()

        title=binding.title.text.toString()
        noteEdTxt=binding.noteEdTxt.text.toString()

        if(isUpdate){
            NotesDAO().updateNotes(dh, bundle.title.toString(), title, noteEdTxt, current)
            Navigation.findNavController(view).navigate(R.id.addNoteGoToFavNote)
        }
        else if(!isUpdate){

            NotesDAO().addNotes(dh,title,noteEdTxt,current,0)
            Navigation.findNavController(view).navigate(R.id.addNoteGoToNote)
        }



    }
}