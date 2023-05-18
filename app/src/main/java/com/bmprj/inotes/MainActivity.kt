package com.bmprj.inotes

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bmprj.inotes.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.mainTasarim=this


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNav, navHostFragment.navController)


        navHostFragment.navController.addOnDestinationChangedListener{_, nd: NavDestination, _ ->
            if( nd.id== R.id.addNoteFragment || nd.id == R.id.searchhFragment){
                binding.bottomNav.visibility= View.GONE

            }
            else if(nd.id == R.id.noteFragment || nd.id == R.id.favNotesFragment ){
                binding.bottomNav.visibility=View.VISIBLE

            }

        }


    }

}