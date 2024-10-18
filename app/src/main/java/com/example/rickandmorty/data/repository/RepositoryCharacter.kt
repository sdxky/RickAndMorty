package com.example.rickandmorty.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.rickandmorty.data.apiService.ApiService
import com.example.rickandmorty.data.model.detailCharacter.DetailModel
import com.example.rickandmorty.utils.Resource
import kotlinx.coroutines.Dispatchers

class RepositoryCharacter(private val api: ApiService) {

    fun getCharacter(id: Int): LiveData<Resource<DetailModel>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = api.getSingleCharacter(id)
                if (response.isSuccessful && response.body() != null) {
                    emit(Resource.Success(response.body()!!))
                } else {
                    emit(Resource.Error("Error: ${response.message()}"))
                }
            } catch (e: Exception) {
                emit(Resource.Error("Network error: ${e.message}"))
            }
        }
    }
}