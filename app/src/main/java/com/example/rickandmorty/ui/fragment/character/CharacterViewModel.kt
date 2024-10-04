package com.example.rickandmorty.ui.fragment.character

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    fun getCharacters() = repository.getCharacters()
}