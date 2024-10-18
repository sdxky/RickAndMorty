package com.example.rickandmorty.data.module

import com.example.rickandmorty.ui.fragment.character.CharacterViewModel
import com.example.rickandmorty.ui.fragment.detail.CharacterDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        CharacterViewModel(get())
    }
    viewModel{
        CharacterDetailViewModel(get())
    }
}