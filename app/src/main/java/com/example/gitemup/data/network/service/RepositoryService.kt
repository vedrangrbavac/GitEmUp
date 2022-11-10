package com.example.gitemup.data.network.service

import com.example.gitemup.common.base.BaseService
import com.example.gitemup.data.models.domain.ResponseWrapper
import com.example.gitemup.data.network.API

class RepositoryService(api: API) : BaseService(api) {

    suspend fun getRepositories(query: String): ResponseWrapper {
        return apiRequest { api.getRepositories(query) }

    }
}