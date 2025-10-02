package com.example.bookify

import android.content.Context
import kotlin.math.min

object BookRepository {
    fun all(context: Context): List<Book> {
        val titles = listOf(
            "Becoming",
            "A Gentleman in Moscow",
            "Normal People",
            "The Choice",
            "Educated",
            "Ordinary People",
            "No Friend but the Mountains",
            "Project Hail Mary",
            "Where the Crawdads Sing",
            "The Night Circus",
            "Sapiens",
            "Atomic Habits"
        )
        val authors = listOf(
            "Michelle Obama",
            "Amor Towles",
            "Sally Rooney",
            "Edith Eger",
            "Tara Westover",
            "Diana Evans",
            "Behrouz Boochani",
            "Andy Weir",
            "Delia Owens",
            "Erin Morgenstern",
            "Yuval Noah Harari",
            "James Clear"
        )
        val genresPool = listOf("Biography", "Fiction", "Memoir", "Sci-Fi", "Non-fiction", "Drama")
        val desc = "Dummy description for UI preview. Replace with real copy later."

        val count = min(titles.size, 12) // we’ll use 12
        return (1..count).map { i ->
            val resName = "book$i"
            val img = context.resources.getIdentifier(resName, "drawable", context.packageName)
                .takeIf { it != 0 } ?: R.drawable.book1

            Book(
                id = "bk_$i",
                title = titles[i - 1],
                author = authors[i - 1],
                year = 2012 + (i % 10),
                genres = genresPool.shuffled().take(2),
                description = desc,
                imageRes = img
            )
        }
    }

    fun byId(context: Context, id: String): Book? = all(context).find { it.id == id }
}
