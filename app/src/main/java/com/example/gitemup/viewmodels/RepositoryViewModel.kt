package com.example.gitemup.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.gitemup.common.base.BaseViewModel
import com.example.gitemup.data.models.domain.ResponseWrapper
import com.example.gitemup.repositories.RepositoryRepository

class RepositoryViewModel(private val repository: RepositoryRepository) : BaseViewModel() {


    val repositories: MutableLiveData<ResponseWrapper?> =
        MutableLiveData(repository.repositories.value)

    suspend fun getRepositories(query: String) {
        repositories.postValue(repository.getRepositories(query))
    }

    fun callGetRepositories(query: String) {
        suspendCall {
            getRepositories(query)
        }
    }
}