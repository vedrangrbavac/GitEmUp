package com.example.gitemup.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitemup.common.base.BaseViewModel
import com.example.gitemup.data.models.domain.Item
import com.example.gitemup.data.models.domain.ResponseWrapper
import com.example.gitemup.repositories.RepositoryRepository

class RepositoryViewModel(private val repository: RepositoryRepository) : BaseViewModel() {


    val repositories: MutableLiveData<List<Item>?> =
        MutableLiveData(repository.repositories.value)

    val repositoriesLiveData: LiveData<List<Item>?> get() = repository.repositories

    suspend fun getRepositories(query: String) {
        repositories.postValue(repository.getRepositories(query))
    }

    fun callGetRepositories(query: String) {
        suspendCall {
            getRepositories(query)
        }
    }
}