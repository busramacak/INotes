package com.bmprj.inotes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.bmprj.inotes.databinding.FragmentSearchhBinding
import java.util.*


class SearchhFragment : Fragment() {

    private lateinit var binding: FragmentSearchhBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_searchh, container, false)
        binding.searchDesign=this

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

    fun onQueryChange(query:String) : Boolean{

        val dh = DataBaseHelper(requireContext())
        val searchNoteList = NotesDAO().getNotes(dh)
        val list = ArrayList<Note>()


        for(i in searchNoteList){

           query.toLowerCase(Locale.getDefault())
            if (query.length === 0) {
            }
            else {
                if (i.note_title.toLowerCase(Locale.getDefault()).contains(query)) {
                    list.add(i)
                }
            }
        }

        binding.recyVSearch.apply {
            layoutManager=GridLayoutManager(context,context.resources.getInteger(R.integer.grid_column_count))
            binding.recyVSearch.layoutManager=layoutManager
            adapter=NoteAdapter(list)
            binding.recyVSearch.adapter=adapter
        }

        return false
    }

    fun backClick(view:View){
       Navigation.findNavController(view).navigate(R.id.noteFragment)
    }


}