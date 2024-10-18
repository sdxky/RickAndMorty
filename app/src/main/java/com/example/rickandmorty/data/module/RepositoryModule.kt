package com.example.rickandmorty.data.module

import com.example.rickandmorty.data.repository.RepositoryCharacter
import com.example.rickandmorty.data.repository.RepositoryCharacters
import org.koin.dsl.module

val repositoryModule = module {
    single { RepositoryCharacters(get()) }
    single { RepositoryCharacter(get()) }
}