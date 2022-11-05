package com.example.gitemup.common.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gitemup.R

fun ImageView.setImageWithURL(path: String?) {
    Glide.with(this)
        .load(path)
        .apply(
            RequestOptions().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
        )
        .into(this)
}

