package com.bmprj.inotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bmprj.inotes.databinding.FragmentAddCheckListBinding
import com.bmprj.inotes.databinding.FragmentAddNoteBinding

class AddCheckListFragment : Fragment() {

    private lateinit var binding: FragmentAddCheckListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_add_check_list, container, false)
        binding.addCheckListDesign=this
        return binding.root
    }


}