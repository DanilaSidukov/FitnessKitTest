package com.sidukov.fitnesskittest.data.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object APIClient {

    private const val BASE_URL = "https://olimpia.fitnesskit-admin.ru"
    private var retrofitFitness: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build()

    var fitnessApiClient: FitnessAPI = retrofitFitness.create(FitnessAPI::class.java)


}