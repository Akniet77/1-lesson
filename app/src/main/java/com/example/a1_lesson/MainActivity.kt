package com.example.a1_lesson

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.a1_lesson.databinding.ActivityMainBinding
import com.example.a1_lesson.utils.Preferences
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,
                R.id.newTaskFragment, R.id.navigation_profile, R.id.onBoardFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val preferences = Preferences(this)
        if (preferences.getHaveSeenOnBoarding()) {
            navController.navigate(
                R.id.navigation_home
            )
        } else {
            navController.navigate(
                R.id.onBoardFragment
            )
        }

        navController.addOnDestinationChangedListener{_, dest, _ ->
            navView.visibility =
                if (dest.id == R.id.newTaskFragment || dest.id == R.id.onBoardFragment) View.GONE else View.VISIBLE
        }
    }
}