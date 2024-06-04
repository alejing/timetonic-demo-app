package com.example.timetonicapp.ui.view

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.example.timetonicapp.R
import com.example.timetonicapp.model.BookItem
import com.example.timetonicapp.utils.AppConstants


/**
 * Adapter for displaying the list of books in a RecyclerView.
 */
class LandingAdapter : ListAdapter<BookItem, LandingAdapter.BookViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        // Create a view holder for the landing item_book layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        // Bind the book data to the view holder
        val book = getItem(position)
        holder.bind(book)
    }

    class BookViewHolder(bookItemView: View) : RecyclerView.ViewHolder(bookItemView) {
        // Initialize views for the landing item_book layout
        private val nameTextView: TextView = bookItemView.findViewById(R.id.item_name)
        private val descriptionTextView: TextView = bookItemView.findViewById(R.id.item_description)
        private val imageView: ImageView = itemView.findViewById(R.id.item_image)
        private val progressBar: ProgressBar = bookItemView.findViewById(R.id.progress_bar)

        // Binds the book data to the view holder.
        fun bind(book: BookItem) {
            nameTextView.text = book.name
            descriptionTextView.text = book.description

            // Use a placeholder image if the URL is null otherwise load the image
            val urlImage = if (book.urlImage == "null") {
                "https://placehold.co/400x200.png"
            } else {
                AppConstants.BASE_URL + book.urlImage
            }

            // Show the progress bar before loading the image
            progressBar.visibility = View.VISIBLE

            Glide.with(imageView.context)
                .load(urlImage)
                .apply(RequestOptions().centerCrop())
                .into(object : DrawableImageViewTarget(imageView) {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        super.onResourceReady(resource, transition)
                        // Hide the progress bar when the image is loaded
                        progressBar.visibility = View.GONE
                    }
                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        super.onLoadFailed(errorDrawable)
                        // Hide the progress bar when the something went wrong
                        progressBar.visibility = View.GONE
                    }
                    override fun onLoadStarted(placeholder: Drawable?) {
                        super.onLoadStarted(placeholder)
                        // Show the progress bar when the image is starting to load
                        progressBar.visibility = View.VISIBLE
                    }
                })

        }
    }

    class DiffCallback : DiffUtil.ItemCallback<BookItem>() {
        override fun areItemsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
            return oldItem == newItem
        }
    }
}