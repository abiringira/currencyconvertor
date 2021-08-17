package com.paypay.currency.convertor.ui.home


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paypay.currency.convertor.data.model.ExchangeRateResponse
import com.paypay.currency.convertor.data.model.Quotes
import com.paypay.currency.convertor.di.NetworkModule
import com.paypay.currency.convertor.ui.home.homeAdapter.RecycleViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel : ViewModel() {

    lateinit var recycleListData: MutableLiveData<ExchangeRateResponse>

    lateinit var recycleViewAdapter : RecycleViewAdapter

    init {
        recycleListData = MutableLiveData()
        recycleViewAdapter = RecycleViewAdapter()
    }



    fun getAdapter () : RecycleViewAdapter {
        return  recycleViewAdapter
    }

    fun setAdapter (data: ArrayList<ExchangeRateResponse>) {
        recycleViewAdapter.setDataList(data)
        recycleViewAdapter.notifyDataSetChanged()
    }

    fun getRecycleListDataObserver(): MutableLiveData<ExchangeRateResponse> {

        return recycleListData

    }

    fun makeExchangeRateApiCall(accessKey: String) {


        System.out.println("BOOOOOOOMMMMMM")

        val call = NetworkModule.getRetroInstance().getExchangeRate(accessKey)
        call.enqueue(object : Callback<ExchangeRateResponse> {
            override fun onFailure(call: Call<ExchangeRateResponse>, t: Throwable) {

                System.out.println("FAILED")
                System.out.println(t)
                recycleListData.postValue(null)
            }

            override fun onResponse(call: Call<ExchangeRateResponse>, response: Response<ExchangeRateResponse>) {
                System.out.println("SUCCESSSSSS")
                System.out.println(response.raw().toString())
                if (response.isSuccessful) {
                    recycleListData.postValue(response.body())
                } else {
                    recycleListData.postValue(null)
                }
            }

        })


    }
}
