package com.eihror.coroutines.network

import com.eihror.coroutines.model.Post
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    const val BASE_URL = "https://jsonplaceholder.typicode.com"
    private var mBaseService: RetrofitService

    init {

        // Define Logging Interceptor for DEBUG mode
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        // Add the interceptors to OkHttpClient
        val httpClientBuilder = OkHttpClient.Builder()

        httpClientBuilder.addInterceptor(loggingInterceptor)

        val okHttpClient = httpClientBuilder
                .connectTimeout(45L, TimeUnit.SECONDS)
                .readTimeout(45L, TimeUnit.SECONDS)
                .build()

        // Set the custom client when building adapter
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()

        mBaseService = retrofit.create(RetrofitService::class.java)

    }

    fun getPosts(): Deferred<Response<List<Post>>> {
        return mBaseService.getPosts()
    }

}