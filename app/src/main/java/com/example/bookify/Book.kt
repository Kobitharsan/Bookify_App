package com.example.bookify

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val year: Int,
    val genres: List<String>,
    val description: String,
    val imageRes: Int
)
