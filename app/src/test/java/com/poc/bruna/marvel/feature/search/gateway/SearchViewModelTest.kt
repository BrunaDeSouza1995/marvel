package com.poc.bruna.marvel.feature.search.gateway

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.poc.bruna.marvel.feature.base.business.data.Result
import com.poc.bruna.marvel.feature.search.business.interactor.SearchRepository
import com.poc.bruna.marvel.feature.search.business.interactor.SearchUseCase
import com.poc.bruna.marvel.plugin.model.response.CharacterComicsResponse
import com.poc.bruna.marvel.plugin.model.response.CharacterResponse
import com.poc.bruna.marvel.utils.extensions.FileLoaderUtils
import com.poc.bruna.marvel.utils.extensions.getComicsURI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

private const val CHARACTERS_JSON = "characters.json"
private const val CHARACTERS_COMICS_JSON = "characters_comics.json"

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: SearchViewModel

    lateinit var useCase: SearchUseCase

    @Mock
    lateinit var mockRepository: SearchRepository

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        useCase = SearchUseCase(mockRepository)
        viewModel = SearchViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when execute use case then return success result`() {
        runBlockingTest {
            val name = "Deadpool"
            val characterResponse = FileLoaderUtils.getDataFromJsonFile(
                javaClass,
                CharacterResponse::class.java,
                CHARACTERS_JSON
            )
            val characterComicsResponse = FileLoaderUtils.getDataFromJsonFile(
                this.javaClass,
                CharacterComicsResponse::class.java,
                CHARACTERS_COMICS_JSON
            )
            val url = characterResponse?.getComicsURI().orEmpty()

            `when`(mockRepository.getCharacters(name)).thenReturn(characterResponse)
            `when`(mockRepository.getComics(url)).thenReturn(characterComicsResponse)

            viewModel.searchByName(name)

            viewModel.charactersLiveData.observeForever {
                if (it is Result.Success) {
                    val (title, description, image, comics) = it.data
                    assertEquals("Avengers", title)
                    assertTrue(description.startsWith("Earth's Mightiest Heroes joined forces to take "))
                    assertTrue(image.startsWith("http://i.annihil.us/u/prod/marvel/i/mg/9/20/5102c774ebae7/"))
                    assertEquals(20, comics.size)
                }
            }
        }
    }
}
