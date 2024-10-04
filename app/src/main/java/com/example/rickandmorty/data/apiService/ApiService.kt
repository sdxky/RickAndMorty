package com.example.rickandmorty.data.apiService

import com.example.rickandmorty.data.model.characters.BaseResponse
import com.example.rickandmorty.data.model.detailCharacter.DetailModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ) : Response<BaseResponse>

    @GET("character/{id}")
    suspend fun getSingleCharacter(
        @Path("id") id: Int
    ) : Response<DetailModel>
}