package com.example.gitemup.viewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.gitemup.TestCase
import com.example.gitemup.data.models.domain.Item
import com.example.gitemup.data.models.domain.Owner
import com.example.gitemup.data.models.domain.ResponseWrapper
import com.example.gitemup.data.network.service.RepositoryService
import com.example.gitemup.repositories.RepositoryRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.get
import java.util.*
import kotlin.test.Test

class RepositoryViewModelTest : TestCase() {

    private val repositoryViewModel = RepositoryViewModel(get())

    private val repositoryRepository get() = get<RepositoryRepository>()

    override val testCaseMocksModule: Module
        get() = module {
            single<RepositoryRepository> { mockk(relaxed = true) }
            single<RepositoryService> { mockk(relaxed = true) }
        }

    val ownerTemp = Owner(
        "vedrangrabavac",
        123,
        "https://github.com/vedrangrbavac",
        "https://avatars.githubusercontent.com/u/78698222?v=4"
    )

    val itemTemp = Item(
        1,
        "GitEmUp",
        "vedrangrbavac",
        false,
        ownerTemp,
        "description",
        "https://api.github.com/repos/vedrangrbavac/GitEmUp",
        "Kotlin",
        5,
        5,
        2,
        1,
        Date(),
        Date(System.currentTimeMillis() - 500000L),
        Date()
    )
    val responseRepositoryTemp = ResponseWrapper(
        2, false, listOf(
            itemTemp
        )
    )

    @Test
    fun testGetRepositories() = runTest {
        every { repositoryRepository.repositories } returns MutableLiveData(
            listOf(
                itemTemp
            )
        )

        val observer = mockk<Observer<List<Item>?>>(relaxed = true)
        repositoryViewModel.repositories.observeForever(observer)
        verify(timeout = 5000) { observer.onChanged(any()) }
    }

}