package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.apiService.ApiService
import com.example.rickandmorty.data.base.BaseRepository


class RepositoryCharacter(private val api: ApiService) : BaseRepository() {
    fun getCharacter(id: Int) = doRequest { api.getSingleCharacter(id) }
}