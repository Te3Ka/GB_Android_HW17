package ru.te3ka.homework17.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import ru.te3ka.homework17.model.Photo
import ru.te3ka.homework17.repository.PhotoRepository

class PhotoViewModel(private val repository: PhotoRepository) : ViewModel() {

    fun getPhotos(sol: Int): Flow<PagingData<Photo>> {
        return repository.getPhotos(sol).cachedIn(viewModelScope)
    }
}