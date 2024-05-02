package com.example.ns_fe_project.di

import com.nhaarman.mockitokotlin2.mock
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertEquals
import org.junit.Test

class NSModuleTest {

    @Test
    fun `test provideBaseUrl`() {
        val module = NSModule()

        val baseUrl = module.provideBaseUrl()

        assertEquals(BASE_URL, baseUrl)
    }

    @Test
    fun `test provideHttpLoggingInterceptor`() {
        val module = NSModule()

        val interceptor = module.provideHttpLoggingInterceptor()

        assertEquals(HttpLoggingInterceptor.Level.BODY, interceptor.level)
    }

    @Test
    fun `test provideHttpClient`() {
        val module = NSModule()
        val mockInterceptor = mock<HttpLoggingInterceptor>()

        val client = module.provideHttpClient(mockInterceptor)

        assertEquals(TIME_OUT, client.readTimeoutMillis)
        assertEquals(TIME_OUT, client.connectTimeoutMillis)
    }

    @Test
    fun `test provideRetrofit`() {
        val module = NSModule()
        val mockClient = mock<OkHttpClient>()

        val retrofit = module.provideRetrofit(mockClient)

        assertEquals(BASE_URL, retrofit.baseUrl().toString())
    }

    companion object {
        private const val BASE_URL = "https://659f86b15023b02bfe89c737.mockapi.io/"
        private const val TIME_OUT = 15_000
    }
}