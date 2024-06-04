package com.example.timetonicapp.ui.viemodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.timetonicapp.model.BookItem
import com.example.timetonicapp.repository.AppRepository
import com.example.timetonicapp.utils.AppConstants

class LandingViewModel (application: Application): AndroidViewModel(application) {

    private val repository = AppRepository(application.applicationContext)

    private val _books = MutableLiveData<List<BookItem>>()
    val books: LiveData<List<BookItem>> get() = _books

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

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