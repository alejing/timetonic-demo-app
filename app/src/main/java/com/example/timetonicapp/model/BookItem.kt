package com.example.timetonicapp.model

/**
 * Data class representing the BookItem to map the JSON response to the list of books.
 * Properties: name - The name of the book
 *             urlImage - The URL of the book where the image is stored.
 *             description - The description of the book.
 */
data class BookItem(val name: String, val urlImage: String, val description: String)
