package com.example.timetonicapp.ui.viemodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.timetonicapp.model.BookItem
import com.example.timetonicapp.repository.AppRepository
import com.example.timetonicapp.utils.AppConstants


/**
 * ViewModel for the Landing screen.
 */
class LandingViewModel (application: Application): AndroidViewModel(application) {

    // Instantiate the repository
    private val repository = AppRepository(application.applicationContext)

    // LiveData to hold the list of books
    private val _books = MutableLiveData<List<BookItem>>()
    val books: LiveData<List<BookItem>> get() = _books

    // LiveData to hold the loading status
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    // LiveData to hold the error message
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    // Function to load books from the repository
    fun loadBooksItems(ou: String, sessKey: String) {
        _isLoading.value = true
        repository.getAllBooks(AppConstants.VERSION, AppConstants.REQ_GET_ALL_BOOKS, ou, sessKey, { books ->
            _books.value =  books
            _isLoading.value = false
        }, { error ->
            _isLoading.value = false
            _errorMessage.value = error.message
        })
    }
}