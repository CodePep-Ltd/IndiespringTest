package com.indiespring.test.api

import com.indiespring.test.data.Bitcoin
import com.indiespring.test.data.BitcoinDetails
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface BitcoinApi {

    // A rather large URL, but there's no queries etc.
    @GET("v1/coins")
    fun getBitcoinsList(): Call<List<Bitcoin>>

    @GET("v1/coins/{id}")
    fun getBitcoinDetails(@Path("id") id: String): Call<BitcoinDetails>

    companion object {
        fun get() : BitcoinApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                // We could define URL in BuildConfig if we had staging, production etc.
                .baseUrl("https://api.coinpaprika.com/")
                .build()
            return retrofit.create(BitcoinApi::class.java)
        }
    }
}