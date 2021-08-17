package com.paypay.currency.convertor.ui.home.homeAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.paypay.currency.convertor.ui.home.Screen
import com.paypay.currency.convertor.ui.home.homefragments.FragmentA
import com.paypay.currency.convertor.ui.home.homefragments.FragmentB

class HomePagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {
    companion object {
        private val TABS = arrayListOf(
            Screen.HOME,
            Screen.DASHBOARD

        )
    }

    override fun getItemCount(): Int {
        return TABS.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (TABS[position]) {
            Screen.HOME -> {
                FragmentA.createInstance()
            }
            Screen.DASHBOARD -> {
                FragmentB.createInstance()
            }
//            Screen.NOTIFICATION -> {
//                FragmentC.createInstance()
//            }
        }
    }
}
