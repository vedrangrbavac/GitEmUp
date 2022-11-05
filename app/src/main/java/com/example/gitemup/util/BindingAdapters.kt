package com.example.gitemup.util

import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gitemup.R
import com.example.gitemup.common.extensions.setImageWithURL

object BindingAdapters {

    @BindingAdapter("items")
    @JvmStatic
    fun <T> setData(recyclerView: RecyclerView, data: List<T>?) {
        if (recyclerView.layoutAnimation == null) {
            recyclerView.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(
                    recyclerView.context,
                    R.anim.layout_animation_fall_down
                )
        }
        with(recyclerView.adapter as? ListAdapter<T, *>) {
            this?.run {
                if (currentList.size == 0) {
                    recyclerView.scheduleLayoutAnimation()
                }
                submitList(data)
            }
        }
    }

    @BindingAdapter("imageFromURL")
    @JvmStatic
    fun setImageFromUrl(imageView: ImageView, path: String?) {
        path?.let { imageView.setImageWithURL(it) }
    }
}