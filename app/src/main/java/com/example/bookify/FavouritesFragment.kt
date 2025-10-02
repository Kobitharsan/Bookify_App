package com.example.bookify

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {
    private lateinit var allBooks: List<Book>
    private lateinit var adapter: BookGridAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allBooks = BookRepository.all(requireContext())

        val rv = view.findViewById<RecyclerView>(R.id.rvFav)
        rv.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = BookGridAdapter(
            items = favList(),
            favouriteIds = AppData.favourites,
            onClick = { b ->
                startActivity(
                    Intent(requireContext(), BookDetailActivity::class.java).putExtra("bookId", b.id)
                )
            },
            onToggleFavourite = { book, nowFav ->
                if (nowFav) AppData.favourites.add(book.id) else AppData.favourites.remove(book.id)
                adapter.submitList(favList())
            }
        )
        rv.adapter = adapter
    }

    private fun favList(): List<Book> = allBooks.filter { AppData.favourites.contains(it.id) }

    override fun onResume() {
        super.onResume()
        adapter.submitList(favList())
    }
}
