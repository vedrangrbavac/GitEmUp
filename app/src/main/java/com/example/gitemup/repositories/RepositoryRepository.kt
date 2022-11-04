package com.example.gitemup.repositories

import androidx.lifecycle.MutableLiveData
import com.example.gitemup.data.models.domain.ResponseWrapper
import com.example.gitemup.data.network.service.RepositoryService
import timber.log.Timber

class RepositoryRepository (private val service: RepositoryService) {

    val repositories: MutableLiveData<ResponseWrapper?> = MutableLiveData()

    suspend fun getRepositories(query: String): ResponseWrapper {
        val data = service.getRepositories(query)
        Timber.i("Data --> $data")
        repositories.postValue(service.getRepositories(query))
        return data
    }

}