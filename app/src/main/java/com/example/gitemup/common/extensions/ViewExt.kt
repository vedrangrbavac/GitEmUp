package com.example.gitemup.common.extensions

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gitemup.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun ImageView.setImageWithURL(path: String?) {
    Glide.with(this)
        .load(path)
        .apply(
            RequestOptions().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
        )
        .into(this)
}

fun <T> LiveData<T>.debounced(coroutineScope: CoroutineScope, duration: Long = 300L) =
    MediatorLiveData<T>().also { mld ->
        val source = this
        var job: Job? = null
        mld.addSource(source) {
            job?.cancel()
            job = coroutineScope.launch {
                delay(duration)
                mld.value = source.value
            }
        }
    }