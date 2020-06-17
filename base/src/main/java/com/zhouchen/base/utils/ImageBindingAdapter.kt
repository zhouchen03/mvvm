package com.zhouchen.base.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("bind:image_url")
fun loadImage(imageView: ImageView, url: String) {
//        Picasso.with(imageView.context).load(url).into(imageView)
}
