package com.example.projectforvk.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.projectforvk.R
import com.example.projectforvk.data.network.ConnectionType
import com.example.projectforvk.data.network.NetworkMonitorUtil
import com.example.projectforvk.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val networkMonitor = NetworkMonitorUtil(this)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        networkStatusTracking()
        val itemListFragment = ItemListFragment()
        replaceFragment(itemListFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun networkStatusTracking() {
        networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                        when (type) {
                            ConnectionType.Wifi -> {
                                Timber.i("NETWORK_MONITOR_STATUS", "Wifi Connection")
                                isVisibleView(true)
                            }
                            ConnectionType.Cellular -> {
                                Timber.i("NETWORK_MONITOR_STATUS", "Cellular Connection")
                                isVisibleView(true)
                            }
                            else -> isVisibleView(false)
                        }
                    }
                    false -> {
                        isVisibleView(false)
                        Timber.i("NETWORK_MONITOR_STATUS", "No Connection")
                    }
                }
            }
        }
    }

    private fun isVisibleView(isVisible: Boolean) {
        with(binding) {
            navHostFragmentContainer.isVisible = isVisible
            tvNoInternet.isVisible = !isVisible
        }
    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }

    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }

}
