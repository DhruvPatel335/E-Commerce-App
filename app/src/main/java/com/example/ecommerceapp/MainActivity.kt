package com.example.ecommerceapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.ecommerceapp.databinding.ActivityMainBinding
import com.example.ecommerceapp.viewModel.AuthorViewModel
import com.example.ecommerceapp.viewModel.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: AuthorViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        mAuth = FirebaseAuth.getInstance()
        viewModel = ViewModelProvider(this, ViewModelFactory())[AuthorViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment_content_main
        ) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnItemReselectedListener { menuItem ->
            val selectedMenuItemNavGraph =
                navController.graph.findNode(menuItem.itemId) as NavGraph

            selectedMenuItemNavGraph.let { menuGraph ->
                navController.popBackStack(menuGraph.startDestinationId, false)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.result.observe(this, Observer {
            val msg = if (it == null) {
                "Author Added Successfully"
            } else {
                "Error: $it"
            }
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}

