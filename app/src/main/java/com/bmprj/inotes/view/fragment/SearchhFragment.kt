package com.bmprj.inotes.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.bmprj.inotes.R
import com.bmprj.inotes.adapter.NoteAdapter
import com.bmprj.inotes.databinding.FragmentSearchhBinding
import com.bmprj.inotes.viewmodel.SearchViewModel


class SearchhFragment : Fragment() {

    private lateinit var binding: FragmentSearchhBinding
    private lateinit var viewModel: SearchViewModel
    private val adapter = NoteAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_searchh, container, false)
        binding.searchDesign=this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        binding.recyVSearch.layoutManager=GridLayoutManager(context,resources.getInteger(R.integer.grid_column_count))
        binding.recyVSearch.adapter=adapter


    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Navigation.findNavController(binding.root).navigate(R.id.noteFragment)
            }
        })
    }

    fun onQueryChange(query:String) : Boolean{


        viewModel.refresh(requireContext(),query)

        observeLiveData()

        return false
    }

    private fun observeLiveData(){
        viewModel.search.observe(viewLifecycleOwner){note->
            note?.let{
                adapter.updateList(note)
            }
        }
    }


    fun backClick(view:View){
       Navigation.findNavController(view).navigate(R.id.noteFragment)
    }


}