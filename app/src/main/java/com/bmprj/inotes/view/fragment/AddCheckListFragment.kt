package com.bmprj.inotes.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bmprj.inotes.R
import com.bmprj.inotes.adapter.CheckAdapter
import com.bmprj.inotes.databinding.FragmentAddCheckListBinding
import com.bmprj.inotes.model.Check
import com.bmprj.inotes.viewmodel.CheckViewModel

class AddCheckListFragment : Fragment() {

    private lateinit var binding: FragmentAddCheckListBinding
    private lateinit var viewModel: CheckViewModel
    private val adapter = CheckAdapter(arrayListOf())
    var list = ArrayList<Check>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_add_check_list, container, false)
        binding.addCheckListDesign=this

        return binding.root
    }

    fun floatBtnClick(view:View){
        val alertDialog = AlertDialog.Builder(context)
        val dialogLayout=layoutInflater.inflate(R.layout.alert_dialog_layout,null)
        val editText=dialogLayout.findViewById<EditText>(R.id.alertEdtxt)

        alertDialog.setView(dialogLayout)
        alertDialog.setPositiveButton(R.string.okButton){ _,_->

            viewModel.addCheck(requireContext(),editText.text.toString())


            Navigation.findNavController(view).navigate(R.id.addCheckListFragment)
        }
        alertDialog.show()
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

        viewModel = ViewModelProvider(this)[CheckViewModel::class.java]

        binding.recyVToDo.layoutManager=LinearLayoutManager(context)
        binding.recyVToDo.adapter=adapter

        viewModel.refresh(requireContext())



        observeLiveData()

    }

    private fun observeLiveData(){
        viewModel.check.observe(viewLifecycleOwner){check->
            check?.let{
                adapter.updateList(check)
            }

            if(!check.isEmpty()){
                binding.checkTxt.text=""
            }

        }
    }

}