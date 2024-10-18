package com.example.rickandmorty.ui.fragment.character

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.repository.RepositoryCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class CharacterViewModel (private val repository: RepositoryCharacters): ViewModel() {
    fun getCharacters() = repository.getCharacters()
}