package com.example.task

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("/hotpepper/gourmet/v1/")
    fun getGourmeSearchJson(
        @Query("format") format : String,
        @Query("key") key: String,
        @Query("count") count : Int,
        @Query("name_any") nameAny : String
    ): Single<RecruitGourmetSearchJson.Welcome>
}