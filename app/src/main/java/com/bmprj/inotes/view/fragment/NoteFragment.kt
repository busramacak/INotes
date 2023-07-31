package com.bmprj.inotes.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bmprj.inotes.R
import com.bmprj.inotes.adapter.NoteAdapter
import com.bmprj.inotes.databinding.FragmentNoteBinding
import com.bmprj.inotes.view.activity.SettingsActivity
import com.bmprj.inotes.viewmodel.NoteViewModel


class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private var adapter = NoteAdapter(arrayListOf())
    private lateinit var viewModel :NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false)
        binding.noteDesign=this

        return binding.root
    }
    fun settingClick(){
        startActivity(Intent(context, SettingsActivity::class.java))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        binding.recyV.layoutManager=GridLayoutManager(context,resources.getInteger(R.integer.grid_column_count))
        binding.recyV.adapter=adapter

        viewModel.refresh(requireContext())


        observeLiveData()
    }


    private fun observeLiveData(){
        viewModel.note.observe(viewLifecycleOwner) { note ->
            note?.let {
                adapter.updateList(note)
            }

            if (!note.isEmpty()) {
                binding.noteTxt.text = ""
            }
        }
    }

}