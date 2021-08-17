package com.paypay.currency.convertor.ui.home.homefragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.paypay.currency.convertor.R
import com.paypay.currency.convertor.databinding.FragmentDemoContentABinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


/**
 * Demo fragment for tab content.
 *
 * This fragment is different than [FragmentB] and [FragmentC] in following ways:
 * - This has it's own view model [FragmentAViewModel] which is provided via injected factory.
 * - This loads a custom layout to test other activities.
 *
 * TODO: Move the fragment to it's own feature package.
 */
@AndroidEntryPoint
class FragmentA : Fragment() {
    companion object {
        fun createInstance(): FragmentA {
            return FragmentA()
        }
    }

    lateinit var binding: FragmentDemoContentABinding
    val viewModel: FragmentAViewModel by viewModels()


    var titles = arrayOf(
        "Source", "Currency", "Rates"
    )

    var currency = arrayOf("USD", "AED", "3.67298")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("Got injected fragment's own viewmodel instance: %s.", viewModel)


        // Inflate the layout for this fragment using data binding and set the view model
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_demo_content_a, container, false)


        val gridView = binding.gridview1

//        val currencyGridView = binding.gridview



        gridView.adapter = CustomAdapter(requireActivity().applicationContext, titles)
//        currencyGridView.adapter = CurrencyAdapter(requireActivity().applicationContext, currency)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeNavigationEvents(viewModel)
    }

    /**
     * TODO: This is an example of how LiveData can be used to navigate. Update accordingly.
     */
    private fun observeNavigationEvents(viewModel: FragmentAViewModel) {
        viewModel.featureXEvent.observe(viewLifecycleOwner, Observer {
            Timber.i("Launching feature X activity.")
//            startActivity(Intent(activity, FeatureXActivity::class.java))
        })

        viewModel.featureYEvent.observe(viewLifecycleOwner, Observer {
            Timber.i("Launching feature Y activity.")
//            startActivity(Intent(activity, FeatureYActivity::class.java))
        })

        viewModel.featureZEvent.observe(viewLifecycleOwner, Observer {
            Timber.i("Launching feature Z activity.")
//            startActivity(Intent(activity, FeatureZActivity::class.java))
        })
    }
}


class CustomAdapter(var context: Context, var title: Array<String>) : BaseAdapter() {


    override fun getCount(): Int {
        return title.size
    }

    override fun getItem(i: Int): Any? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var gridView: View
        if (convertView == null) {
            gridView = View(context)

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.text_title_item, null)

            // set value into textview
            val textView = gridView
                .findViewById<View>(R.id.grid_item_label) as TextView
            textView.setText(java.lang.String.valueOf(title[position]))
        } else {
            gridView = convertView
        }
        return gridView
    }


}


class CurrencyAdapter(var context: Context, var title: Array<String>) : BaseAdapter() {


    override fun getCount(): Int {
        return title.size
    }

    override fun getItem(i: Int): Any? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var gridView: View
        if (convertView == null) {
            gridView = View(context)

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.currency_data_item, null)

            // set value into textview
            val textView = gridView
                .findViewById<View>(R.id.grid_item_currency_label) as TextView
            textView.setText(java.lang.String.valueOf(title[position]))
        } else {
            gridView = convertView
        }
        return gridView
    }


}
