package com.example.bookify

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class BookDetailActivity : AppCompatActivity() {
    private lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_book_detail)

        val id = intent.getStringExtra("bookId") ?: return finish()
        book = BookRepository.byId(this, id) ?: return finish()

        findViewById<ImageView>(R.id.ivCover).setImageResource(book.imageRes)
        findViewById<TextView>(R.id.tvTitle).text = book.title
        findViewById<TextView>(R.id.tvAuthor).text = book.author
        findViewById<TextView>(R.id.tvYear).text = book.year.toString()
        findViewById<TextView>(R.id.tvGenres).text = book.genres.joinToString("  ")
        findViewById<TextView>(R.id.tvDesc).text = book.description

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        val btnHeart = findViewById<ImageButton>(R.id.btnHeart)

        btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        // heart
        setHeartIcon(btnHeart, AppData.favourites.contains(book.id))
        btnHeart.setOnClickListener {
            val nowFav = if (AppData.favourites.contains(book.id)) {
                AppData.favourites.remove(book.id); false
            } else {
                AppData.favourites.add(book.id); true
            }
            setHeartIcon(btnHeart, nowFav)
        }
    }

    private fun setHeartIcon(btn: ImageButton, fav: Boolean) {
        btn.setImageResource(if (fav) R.drawable.ic_heart_filled else R.drawable.ic_heart)
    }
}
