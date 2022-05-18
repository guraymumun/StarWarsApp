package com.example.starwarsapp.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwarsapp.data.remote.dto.PersonDTO
import com.example.starwarsapp.domain.datasource.PeopleDataSource
import retrofit2.HttpException
import java.io.IOException

class PeoplePagingSource(
    private val peopleDataSource: PeopleDataSource,
    private val search: String
) : PagingSource<String, PersonDTO>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PersonDTO> {
        val cursor = params.key

        return try {
            val response = cursor?.let { peopleDataSource.getNextPeople(it) }
                ?: peopleDataSource.getNextPeople("people/?search=$search")
            val resultItems = response.body()?.results ?: listOf()

            LoadResult.Page(
                data = resultItems,
                prevKey = response.body()?.previous,
                nextKey = response.body()?.next
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<String, PersonDTO>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.nextKey
        }
    }
}