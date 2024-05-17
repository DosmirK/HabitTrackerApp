package com.example.habittrackerapp.presentation.activity

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.habittrackerapp.R
import com.example.habittrackerapp.databinding.ActivityMainBinding
import com.example.habittrackerapp.domain.utils.notifications.NotificationService
import com.example.habittrackerapp.domain.utils.notifications.checkNotificationPermission
import com.example.habittrackerapp.domain.utils.notifications.showNotificationPermissionDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        if (!checkNotificationPermission(this)) {
            showNotificationPermissionDialog(this)
        } else {
            NotificationService.scheduleJob(this)
            Log.e("ololo", "Запуск задачи")
        }
    }
}