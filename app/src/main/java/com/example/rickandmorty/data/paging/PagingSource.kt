package com.example.rickandmorty.data.paging

import com.example.rickandmorty.data.apiService.ApiService
import com.example.rickandmorty.data.model.characters.Character
import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException

const val START_INDEX = 1
class PagingSource(private val api: ApiService): PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val currentPage = params.key ?: START_INDEX
            val previousKey = if (currentPage == START_INDEX) null else currentPage.minus(1)
            val response = (api.getCharacters(currentPage).body()?.results) ?: emptyList()
            LoadResult.Page(
                data = response,
                prevKey = previousKey,
                nextKey = if (response.isEmpty()) null else currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (e: HttpException){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition!!)?.prevKey?.plus(1)?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}