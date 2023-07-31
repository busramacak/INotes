package com.bmprj.inotes.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.bmprj.inotes.R
import com.bmprj.inotes.adapter.FavAdapter
import com.bmprj.inotes.databinding.FragmentFavNotesBinding
import com.bmprj.inotes.viewmodel.FavNoteViewModel


class FavNotesFragment : Fragment() {
    private lateinit var binding:FragmentFavNotesBinding
    private val adapter = FavAdapter(arrayListOf())
    private lateinit var viewModel: FavNoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_fav_notes, container, false)
        binding.favDesign=this
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Navigation.findNavController(binding.root).navigate(R.id.noteFragment)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[FavNoteViewModel::class.java]

        binding.recyVFav.layoutManager=GridLayoutManager(context,resources.getInteger(R.integer.grid_column_count))
        binding.recyVFav.adapter=adapter

        viewModel.refresh(requireContext())

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.note.observe(viewLifecycleOwner){note ->
            note?.let{
                adapter.updateList(note)
            }
            if(!note.isEmpty()){
                binding.favTxt.text=""
            }
        }
    }


}