package com.thumbnail.bookmark.presenter.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.thumbnail.bookmark.R

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .centerCrop()
        .skipMemoryCache(true)
        .fallback(R.drawable.ic_baseline_browser_not_supported_24)
        .error(R.drawable.ic_baseline_error_outline_24)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)

}