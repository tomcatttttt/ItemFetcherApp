package com.many.to.many.item.fetcher.app.data

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("items/random")
    suspend fun getRandomItems(): Response<ItemResponse>
}
