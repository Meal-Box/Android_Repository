package com.example.mealbox1.domain

import com.example.mealbox1.data.Meal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {
    @GET("mealServiceDietInfo")
    fun ApiService(
        @Query("Key") Key:String,
        @Query("Type") Type:String,
        @Query("pIndex") pIndex:Int,
        @Query("pSize") pSize:Int,
        @Query("ATPT_OFCDC_SC_CODE") ATPT_OFCDC_SC_CODE:String,
        @Query("SD_SCHUL_CODE") SD_SCHUL_CODE:String,
        @Query("MLSV_YMD") MLSV_YMD:String,
        @Query("MMEAL_SC_CODE") MMEAL_SC_CODE:String
    ): Call<Meal>
}