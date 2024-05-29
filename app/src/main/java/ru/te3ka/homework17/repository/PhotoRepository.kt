package ru.te3ka.homework17.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.te3ka.homework17.model.Photo
import ru.te3ka.homework17.network.MarsRoverApi
import ru.te3ka.homework17.paging.PhotoPagingSource

class PhotoRepository(private val api: MarsRoverApi, private val apiKey: String) {

    fun getPhotos(sol: Int): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { PhotoPagingSource(api, apiKey, sol) }
        ).flow
    }
}