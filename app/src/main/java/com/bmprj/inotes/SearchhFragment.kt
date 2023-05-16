package com.bmprj.inotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bmprj.inotes.R
import com.bmprj.inotes.databinding.FragmentSearchhBinding


class SearchhFragment : Fragment() {

    private lateinit var binding: FragmentSearchhBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_searchh, container, false)
        binding.searchDesign=this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    fun backClick(view:View){
       Navigation.findNavController(view).navigate(R.id.searchhGoToNote)
    }


}