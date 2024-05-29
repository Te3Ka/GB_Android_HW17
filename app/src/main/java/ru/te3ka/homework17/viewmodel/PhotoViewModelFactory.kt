package ru.te3ka.homework17.viewmodel;

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel;

import kotlin.Suppress;
import ru.te3ka.homework17.repository.PhotoRepository;

class PhotoViewModelFactory(val repository: PhotoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        if (modelClass.isAssignableFrom(PhotoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PhotoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}