package com.bmprj.inotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.bmprj.inotes.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {

    private lateinit var binding:FragmentMenuBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)
        binding.menuDesign=this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dh = DataBaseHelper(requireContext())
        val noteList = NotesDAO().getNotes(dh)
        val list = ArrayList<Note>()
        for(i in noteList){
            list.add(i)

        }

        binding.recyV.apply{
            layoutManager = GridLayoutManager(context,2)
            binding.recyV.layoutManager=layoutManager
            adapter = MenuAdapter(list)
            binding.recyV.adapter=adapter
        }
    }

}