package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: Fragment

    private val navController by lazy { navHostFragment.findNavController() }
    private val appBarConfiguration by lazy { AppBarConfiguration(navController.graph) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            navHostFragment =
                supportFragmentManager.findFragmentById(fragmentContainerView.id) as NavHostFragment
            setupActionBarWithNavController(navController, appBarConfiguration)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.logout_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.loginFragment -> {
                val navOptions = NavOptions.Builder()
                    .setLaunchSingleTop(true)
                    .setPopUpTo(R.id.main_nav_graph, inclusive = true)
                    .build()
                navController.navigate(R.id.loginFragment, null, navOptions)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
