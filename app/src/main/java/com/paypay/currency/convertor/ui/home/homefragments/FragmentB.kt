package com.paypay.currency.convertor.ui.home.homefragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.paypay.currency.convertor.R
import com.paypay.currency.convertor.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Demo fragment for tab content.
 *
 * TODO: Move the fragment to it's own feature package.
 */
@AndroidEntryPoint
class FragmentB : Fragment() {
    companion object {
        fun createInstance(): FragmentB {
            return FragmentB()
        }
    }

    val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("Got injected parent's viewmodel instance: %s.", viewModel)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_feature_x, container, false)
    }
}
