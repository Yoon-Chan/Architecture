package com.example.booksearchapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.booksearchapp.data.model.Book
import com.example.booksearchapp.databinding.ItemBookPreviewBinding

class BookSearchViewHolder(private val binding: ItemBookPreviewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(book: Book) {
        val author = book.authors.toString().removeSurrounding("[", "]")
        val publisher = book.publisher
        val date = if (book.datetime.isNotEmpty()) book.datetime.substring(0, 10) else ""

        with(binding){
            ivArticleImage.load(book.thumbnail)
            tvTitle.text = book.title
            tvAuthor.text = "$author | $publisher"
            tvDatetime.text = date
        }
    }
}