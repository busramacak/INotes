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
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bmprj.inotes.databinding.FragmentAddCheckListBinding

class AddCheckListFragment : Fragment() {

    private lateinit var binding: FragmentAddCheckListBinding

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
        alertDialog.setPositiveButton("OK"){dialogInterface, i->

            val dh = DataBaseHelper(requireContext())
            ChecksDAO().addChecks(dh,editText.text.toString(),0)
            Navigation.findNavController(view).navigate(R.id.addCheckListFragment)
        }
        alertDialog.show()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dh = DataBaseHelper(requireContext())
        val checkList = ChecksDAO().getChecks(dh)
        var list = ArrayList<Check>()

        for(i in checkList){
            list.add(i)
        }

        binding.recyVToDo.apply {
            layoutManager=LinearLayoutManager(context)
            binding.recyVToDo.layoutManager=layoutManager
            adapter=CheckAdapter(list)
            binding.recyVToDo.adapter=adapter
        }
    }

}