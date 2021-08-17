package com.paypay.currency.convertor.ui.home.homeAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.paypay.currency.convertor.data.model.ExchangeRateResponse
import com.paypay.currency.convertor.data.model.Quotes
import com.paypay.currency.convertor.databinding.CurrencyDataItemBinding

class RecycleViewAdapter : RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>() {

    var items =  ArrayList<ExchangeRateResponse>()

    fun setDataList(data: ArrayList<ExchangeRateResponse>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewAdapter.MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CurrencyDataItemBinding.inflate(layoutInflater)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size


    class MyViewHolder(val binding: CurrencyDataItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ExchangeRateResponse) {
            binding.recycleData = data

        }
    }

}