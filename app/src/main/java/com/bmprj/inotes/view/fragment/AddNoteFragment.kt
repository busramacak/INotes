package com.bmprj.inotes.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bmprj.inotes.R
import com.bmprj.inotes.databinding.FragmentAddNoteBinding
import com.bmprj.inotes.viewmodel.AddNoteViewModel
import java.util.*


class AddNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var viewModel: AddNoteViewModel
    var isUpdate=false
    val bundle: AddNoteFragmentArgs by navArgs()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AddNoteViewModel::class.java]
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



        if(binding.title.text.isEmpty() || binding.noteEdTxt.text.isEmpty()){
            Toast.makeText(context,context?.resources?.getString(R.string.titleOrNoteIsNotBlank),Toast.LENGTH_LONG).show()
        }
        else{


            if(isUpdate){
                viewModel.update(requireContext(), bundle.noteId?.toInt(), binding.title.text.toString(), binding.noteEdTxt.text.toString())
            }
            else if(!isUpdate){
                viewModel.add(requireContext(),binding.title.text.toString(), binding.noteEdTxt.text.toString())
            }
            Navigation.findNavController(view).navigate(R.id.addNoteGoToNote)
        }




    }
}