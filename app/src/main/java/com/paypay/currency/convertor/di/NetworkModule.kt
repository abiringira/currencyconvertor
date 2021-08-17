package com.paypay.currency.convertor.di

import androidx.databinding.library.BuildConfig
import com.paypay.currency.convertor.api.WebServiceApi
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class NetworkModule {

    internal fun okHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            // Only add HTTP logs for debug builds
            builder.addInterceptor(logging)
        }
        return builder.build()
    }



    companion object {

        val BASE_URL = "http://api.currencylayer.com"



        fun getRetroInstance(): WebServiceApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(WebServiceApi::class.java)
        }

    }
}
