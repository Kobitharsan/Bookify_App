package com.example.bookify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class BookGridAdapter(
    private var items: List<Book>,
    private val favouriteIds: MutableSet<String>,
    private val onClick: (Book) -> Unit,
    private val onToggleFavourite: (Book, Boolean) -> Unit
) : RecyclerView.Adapter<BookGridAdapter.VH>() {

    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        val card: CardView = v.findViewById(R.id.card)
        val cover: ImageView = v.findViewById(R.id.ivCover)
        val title: TextView = v.findViewById(R.id.tvTitle)
        val author: TextView = v.findViewById(R.id.tvAuthor)
        val heart: ImageButton = v.findViewById(R.id.btnHeart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book_grid, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(h: VH, position: Int) {
        val b = items[position]
        h.cover.setImageResource(b.imageRes)
        h.title.text = b.title
        h.author.text = b.author
        val fav = favouriteIds.contains(b.id)
        h.heart.setImageResource(if (fav) R.drawable.ic_heart_filled else R.drawable.ic_heart)
        h.card.setOnClickListener { onClick(b) }
        h.heart.setOnClickListener {
            val now = !favouriteIds.contains(b.id)
            if (now) favouriteIds.add(b.id) else favouriteIds.remove(b.id)
            notifyItemChanged(position)
            onToggleFavourite(b, now)
        }
    }

    override fun getItemCount() = items.size
    fun submitList(newItems: List<Book>) { items = newItems; notifyDataSetChanged() }
}
