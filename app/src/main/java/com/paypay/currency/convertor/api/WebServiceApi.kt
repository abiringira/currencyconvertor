package com.paypay.currency.convertor.api

import androidx.lifecycle.MutableLiveData
import com.paypay.currency.convertor.data.model.ExchangeRateResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Web service API.
 * TODO: Replace with your API
 */
interface WebServiceApi {


    @GET("/live?")
    fun getExchangeRate( @Query("access_key") access_key: String): Call<ExchangeRateResponse>


}
