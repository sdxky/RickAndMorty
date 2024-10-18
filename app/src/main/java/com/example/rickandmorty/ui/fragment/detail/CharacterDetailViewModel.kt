package com.example.rickandmorty.ui.fragment.detail

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.repository.RepositoryCharacter

class CharacterDetailViewModel (private val repository: RepositoryCharacter): ViewModel(){

    fun getCharacter(id: Int) = repository.
    getCharacter(id)
}