package com.danielm.redditskin

import com.danielm.redditskin.data.Data
import com.danielm.redditskin.data.RedditResponse
import com.danielm.redditskin.di.appModule
import com.danielm.redditskin.mvvm.Repository
import kotlinx.coroutines.runBlocking
import okhttp3.Response
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.mockito.Mockito
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : KoinTest {


    @Test
    fun `load posts from repository`() = runBlocking {

        startKoin{ modules(appModule) }

        var repositorioMock = mock(Repository::class.java)


        Mockito.`when`(repositorioMock.posts()).thenReturn(Response<RedditResponse(Data("something", Any(), emptyList(), 10, ""),"123"))
    }




}

