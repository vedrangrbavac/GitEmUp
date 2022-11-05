package com.example.gitemup.viewmodels

import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gitemup.R
import com.example.gitemup.common.base.BaseViewModel
import com.example.gitemup.common.extensions.debounced
import com.example.gitemup.data.models.domain.Item
import com.example.gitemup.repositories.RepositoryRepository

class RepositoryViewModel(private val repository: RepositoryRepository) : BaseViewModel() {


    val repositories: MutableLiveData<List<Item>?> =
        MutableLiveData(repository.repositories.value)


    val queryInput = MutableLiveData<String?>()

    val searchInput = MutableLiveData<String?>()

    val selectedItemPosition = MutableLiveData<Int?>()

    val searchedData = MediatorLiveData<List<Item>>().apply {
        listOf(
            searchInput.debounced(viewModelScope),
            repositories,
            selectedItemPosition
        ).forEach { it ->
            addSource(it) {
                val search = searchInput.value?.lowercase() ?: ""
                val selectedPosition = selectedItemPosition.value
                val searchedValue = if (search.isEmpty()) {
                    repositories.value
                } else {
                    repositories.value?.filter { item ->
                        item.fullName.contains(search)
                    }
                }
                val sortedValue = when (selectedPosition) {
                    0 -> searchedValue?.sortedByDescending { it.numberOfStarts }
                    1 -> searchedValue?.sortedByDescending { it.numberOfForks }
                    else -> searchedValue?.sortedBy { it.name }
                }
                value = sortedValue
            }
        }
    }

    private suspend fun getRepositories(query: String) {
        repositories.postValue(repository.getRepositories(query))
    }

    fun callGetRepositories(query: String) {
        suspendCall {
            getRepositories(query)
        }
    }


    fun onClick(view: View) {
        when (view.id) {
            R.id.btnSearch -> queryInput.value?.let { callGetRepositories(it) }
        }
    }


}