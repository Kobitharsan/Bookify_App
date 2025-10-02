package com.example.bookify

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExploreFragment : Fragment(R.layout.fragment_explore) {
    private lateinit var allBooks: List<Book>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allBooks = BookRepository.all(requireContext())

        setupRow(view.findViewById(R.id.rvTrending), allBooks.take(8))
        setupRow(view.findViewById(R.id.rvNew), allBooks.drop(4).take(8))
        setupRow(view.findViewById(R.id.rvSelected), allBooks.shuffled().take(8))
    }

    private fun setupRow(rv: RecyclerView, data: List<Book>) {
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = BookHorizontalAdapter(
            items = data,
            favouriteIds = AppData.favourites,
            onClick = { b ->
                startActivity(
                    Intent(requireContext(), BookDetailActivity::class.java).putExtra("bookId", b.id)
                )
            },
            onToggleFavourite = { book, nowFav ->
                if (nowFav) AppData.favourites.add(book.id) else AppData.favourites.remove(book.id)
            }
        )
    }
}
