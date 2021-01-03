package com.sweed.saunameet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ImageCapture
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sweed.saunameet.login.DISPLAY_NAME_KEY
import com.sweed.saunameet.login.LoginActivity
import java.io.File
import java.util.concurrent.ExecutorService


//typealias LumaListener = (luma: Double) -> Unit


class MainActivity : AppCompatActivity() {
    private var imageCapture: ImageCapture? = null

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var navController: NavController
    private lateinit var displayName: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayName = intent?.extras?.getString(DISPLAY_NAME_KEY).toString()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.startSessionFragment,
                R.id.allOilsFragment,
                R.id.meetOverviewFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottom_nav.setupWithNavController(navController)


        bottom_nav.setOnNavigationItemSelectedListener { item ->
            Log.i("setOnNavigationItemSelectedListener", "$item")
            onNavDestinationSelected(item, Navigation.findNavController(this, R.id.nav_host_fragment))
        }

    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.logout_icon -> {
//                Toast.makeText(this, "Hi there", Toast.LENGTH_LONG).show()
//                navController.navigate()
                navigateToLogin()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(
            this,
            LoginActivity::class.java
        )
//                intent.putExtra("Text", Texthere)
        startActivity(intent)
    }

    override fun onBackPressed() {
        val fragment =
            this.supportFragmentManager.findFragmentById(R.id.startSessionFragment)
        (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let {
            super.onBackPressed()
        }
    }


}

interface IOnBackPressed {
    fun onBackPressed(): Boolean
}