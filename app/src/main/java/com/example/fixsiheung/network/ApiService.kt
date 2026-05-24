package com.example.fix_siheung.network

import com.example.fixsiheung.model.Report
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    // 모든 제보 목록 가져오기
    @GET("reports")
    fun getAllReports(): Call<List<Report>>

    // 새로운 제보 등록하기
    @POST("reports")
    fun postReport(@Body report: Report): Call<Report>

    // 특정 제보 업데이트 (조회수 등)
    @PUT("reports/{id}")
    fun updateReport(@Path("id") id: Int, @Body report: Report): Call<Report>
}