package com.example.gitemup.repositories

import androidx.lifecycle.MutableLiveData
import com.example.gitemup.data.models.domain.Item
import com.example.gitemup.data.models.domain.ResponseWrapper
import com.example.gitemup.data.network.service.RepositoryService
import timber.log.Timber

class RepositoryRepository (private val service: RepositoryService) {

    val repositories: MutableLiveData<List<Item>?> = MutableLiveData()

    suspend fun getRepositories(query: String): List<Item> {
        val data = service.getRepositories(query).items
        Timber.i("Data --> $data")
        repositories.postValue(service.getRepositories(query).items)
        return data
    }

}