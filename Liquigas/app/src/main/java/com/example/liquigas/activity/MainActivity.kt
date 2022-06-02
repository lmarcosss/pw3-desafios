package com.example.liquigas.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.liquigas.R
import com.example.liquigas.dao.AppDatabase
import com.example.liquigas.databinding.ActivityMainBinding
import com.example.liquigas.utils.LocalStorage
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var database: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        database = AppDatabase.getInstance(this)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val navHeaderView = navView.getHeaderView(0)
        val tvHeaderName = navHeaderView.findViewById(R.id.userNameTextView) as TextView
        val tvHeaderAddress = navHeaderView.findViewById(R.id.userAddressTextView) as TextView

        val localStorage = LocalStorage(this)

        var userId = localStorage.getValue(getString(R.string.user_logged))

        if (userId != null) {
            val databaseUserDAO = database!!.userDAO()

            val user = databaseUserDAO.getUserById(userId)

            tvHeaderName.setText(user.name)
            tvHeaderAddress.setText(user.address)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId

        if (itemId == R.id.action_info) {
            findNavController(R.id.nav_host_fragment_content_main)
                .navigate(R.id.action_nav_home_to_nav_information)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}