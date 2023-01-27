package com.sidukov.fitnesskittest.data.remote

import com.sidukov.fitnesskittest.domain.response.FitnessApiBody
import retrofit2.http.GET
import retrofit2.http.Query

interface FitnessAPI {

    @GET("schedule/get_v3/")
    suspend fun getSchedule(
        @Query("club_id") club_id: Int = 2
    ): FitnessApiBody

}