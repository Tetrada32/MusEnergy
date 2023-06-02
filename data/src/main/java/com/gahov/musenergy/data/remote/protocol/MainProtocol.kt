package com.gahov.musenergy.data.remote.protocol


import com.gahov.musenergy.data.remote.entities.success.DefaultSuccessResponse
import retrofit2.Response
import retrofit2.http.*

interface MainProtocol {

    @GET("everything")
    suspend fun getNewsList(@Query("q") category: String): Response<DefaultSuccessResponse>
}