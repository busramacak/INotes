package com.bmprj.inotes

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bmprj.inotes.databinding.FragmentAddCheckListBinding

class AddCheckListFragment : Fragment() {

    private lateinit var binding: FragmentAddCheckListBinding
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
        alertDialog.setPositiveButton(R.string.okButton){dialogInterface, i->

            val dh = DataBaseHelper(requireContext())
            ChecksDAO().addChecks(dh,editText.text.toString(),0)
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


        val dh = DataBaseHelper(requireContext())
        val checkList = ChecksDAO().getChecks(dh)


        for(i in checkList){
            list.add(i)
        }

        if(!list.isEmpty()){
            binding.checkTxt.text=""
        }

        binding.recyVToDo.apply {
            layoutManager=LinearLayoutManager(context)
            binding.recyVToDo.layoutManager=layoutManager
            adapter=CheckAdapter(list)
            binding.recyVToDo.adapter=adapter
        }

    }

}