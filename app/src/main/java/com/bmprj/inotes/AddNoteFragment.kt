package com.bmprj.inotes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavArgs
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bmprj.inotes.databinding.FragmentAddNoteBinding
import java.text.SimpleDateFormat
import java.util.*


class AddNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddNoteBinding
    var isUpdate=false
    val bundle:AddNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)
        binding.addNoteDesign=this


        binding.title.setText(bundle.noteTitle)
        binding.noteEdTxt.setText(bundle.note)
        if(bundle.note!="") {
            isUpdate = true
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this,object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Navigation.findNavController(binding.root).navigate(R.id.noteFragment)
            }
        })
    }



    fun backClick(view:View){


        Navigation.findNavController(view).navigate(R.id.noteFragment)

    }



    @SuppressLint("ResourceAsColor")
    fun saveClick(view: View){

        val dh = DataBaseHelper(requireContext())
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd MMM yy")
        val current = formatter.format(date).toString()


        if(binding.title.text.isEmpty() || binding.noteEdTxt.text.isEmpty()){
            Toast.makeText(context,"Title or notes cannot be left blank if you want to save",Toast.LENGTH_LONG).show()
        }
        else{
            if(isUpdate){
                NotesDAO().updateNotes(dh, bundle.noteId?.toInt(), binding.title.text.toString(), binding.noteEdTxt.text.toString(), current)
            }
            else if(!isUpdate){
                NotesDAO().addNotes(dh,binding.title.text.toString(), binding.noteEdTxt.text.toString(),current,0)
            }
            Navigation.findNavController(view).navigate(R.id.addNoteGoToNote)
        }




    }
}