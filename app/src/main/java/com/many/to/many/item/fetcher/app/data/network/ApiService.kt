package com.many.to.many.item.fetcher.app.data.network

import com.many.to.many.item.fetcher.app.data.ItemDetailsResponse
import com.many.to.many.item.fetcher.app.data.ItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("items/random")
    suspend fun getRandomItems(): Response<ItemResponse>

    @GET("texts/{itemId}")
    suspend fun getItemDetails(@Path("itemId") itemId: String): Response<ItemDetailsResponse>
}
