package com.bmprj.inotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.bmprj.inotes.databinding.FragmentFavNotesBinding


class FavNotesFragment : Fragment() {
    private lateinit var binding:FragmentFavNotesBinding
    val list = ArrayList<Note>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_fav_notes, container, false)
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

        val dh = DataBaseHelper(requireContext())
        val favNoteList = NotesDAO().getNotes(dh)

        for(i in favNoteList){
            if(i.note_fav==1){
                list.add(i)
            }
        }

        if(!list.isEmpty()){
            binding.favTxt.text=""
        }

        binding.recyVFav.apply {
            layoutManager=GridLayoutManager(context,resources.getInteger(R.integer.grid_column_count))
            binding.recyVFav.layoutManager=layoutManager
            adapter=FavAdapter(list)
            binding.recyVFav.adapter=adapter
        }
    }


}