package me.lordierclaw.todo2.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import me.lordierclaw.todo2.R
import me.lordierclaw.todo2.databinding.ActivityMainBinding
import me.lordierclaw.todo2.model.Task
import me.lordierclaw.todo2.screen.task.dialog.AddTaskDialogFragment
import me.lordierclaw.todo2.screen.task.dialog.IAddTaskListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var fab: FloatingActionButton
    private lateinit var navHost: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNav = binding.bottomNav
        fab = binding.fab
        navHost = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        setupBottomNav()
        binding.fab.setOnClickListener { fabOnClick() }
    }

    private fun setupBottomNav() {
        bottomNav.setupWithNavController(navHost.navController)
        bottomNav.setOnItemSelectedListener {
            if (it.itemId == R.id.menuBtn) {
                binding.root.open()
                false
            } else {
                onNavDestinationSelected(it, navHost.navController)
            }
        }
        navHost.navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.mineFragment) {
                fab.visibility = View.GONE
            } else {
                fab.visibility = View.VISIBLE
            }
        }
        onBackPressedDispatcher.addCallback (this) { onBackClick() }
    }

    private fun onBackClick() {
        if (binding.root.isDrawerOpen(GravityCompat.START)) {
            binding.root.close()
        } else {
            onBackPressed()
        }
    }

    private fun fabOnClick() {
        when (navHost.navController.currentDestination?.id) {
            R.id.allTaskFragment -> {
                val dialog = AddTaskDialogFragment.newInstance(object : IAddTaskListener {
                    override fun onFinish(result: Int, task: Task?) {
                    }
                })
                dialog.show(supportFragmentManager, "add_task_dialog")
            }
            R.id.calendarFragment -> {
                Toast.makeText(applicationContext,
                    "This feature is not available",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}