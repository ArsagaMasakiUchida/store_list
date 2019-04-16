package com.example.task

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Client {

    private val okHttpclient = OkHttpClient.Builder().build()

    private val gson = GsonConverterFactory.create(GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create())

    val retrofitApi = Retrofit.Builder()
        .baseUrl("https://webservice.recruit.co.jp")
        .client(okHttpclient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(gson)
        .build()
        .create(RetrofitApi::class.java)

}