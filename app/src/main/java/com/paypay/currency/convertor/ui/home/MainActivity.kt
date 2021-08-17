package com.paypay.currency.convertor.ui.home

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.viewpager2.widget.ViewPager2
import com.paypay.currency.convertor.R
import com.paypay.currency.convertor.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.paypay.currency.convertor.BR
import com.paypay.currency.convertor.data.model.ExchangeRateResponse
import com.paypay.currency.convertor.data.model.Quotes
import com.paypay.currency.convertor.ui.home.homeAdapter.HomePagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.currency_recycle_view.*
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainPagerAdapter: HomePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Timber.d("Activity created.") // Test logging using timber.


        val viewModel = makeApiCall();

        setUpinding(viewModel)

        setupBottomNavigationBar()

    }


    fun setUpinding(viewModel: HomeViewModel) {


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
        recycler_view_currency.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, VERTICAL)
            addItemDecoration(decoration)
        }
    }

    private fun setupBottomNavigationBar() {
        // Initialize components/views.
        mainPagerAdapter = HomePagerAdapter(this)

        // Show the default screen.
        selectScreen(Screen.HOME)

        // Set the listener for item selection in the bottom navigation view.
        binding.navView.setOnNavigationItemSelectedListener(this)

        // Attach an adapter to the view pager and make it select the bottom navigation
        // menu item and change the title to proper values when selected.
        binding.viewPager.adapter = mainPagerAdapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val selectedScreen: Screen = Screen.values()[position]
                selectBottomNavigationViewMenuItem(selectedScreen.menuItemId)
                supportActionBar?.setTitle(selectedScreen.titleStringId)


            }
        })
    }


    fun makeApiCall(): HomeViewModel {


        val viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        viewModel.getRecycleListDataObserver().observe(this, Observer<ExchangeRateResponse> {
            if (it != null) {
//                viewModel.setAdapter(it.quotes))

            } else {
                Toast.makeText(this, "Error in Fetching data", Toast.LENGTH_LONG).show()

            }
        })

        viewModel.makeExchangeRateApiCall("7fa7ed09889aeaa53a21ca95dc999c9e")

        return viewModel

    }

    private fun selectScreen(defaultScreen: Screen) {
        scrollToScreen(defaultScreen)
        selectBottomNavigationViewMenuItem(defaultScreen.menuItemId)
        supportActionBar?.setTitle(defaultScreen.titleStringId)
    }

    /**
     * Scrolls ViewPager to show the provided screen.
     */
    private fun scrollToScreen(screen: Screen) {
        if (screen.ordinal != binding.viewPager.currentItem) {
            binding.viewPager.setCurrentItem(screen.ordinal, true)
        }
    }

    /**
     * Selects the specified item in the bottom navigation view.
     */
    private fun selectBottomNavigationViewMenuItem(@IdRes menuItemId: Int) {
        binding.navView.setOnNavigationItemSelectedListener(null)
        binding.navView.selectedItemId = menuItemId
        binding.navView.setOnNavigationItemSelectedListener(this)
    }

    /**
     * Listener implementation for registering bottom navigation clicks.
     */
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        getMainScreenForMenuItem(menuItem.itemId)?.let { screenItem ->
            scrollToScreen(screenItem)
            supportActionBar?.setTitle(screenItem.titleStringId)
            return true
        }
        return false
    }
}
