package team.retum.savage_android.data.api

import retrofit2.Call
import retrofit2.http.GET
import team.retum.savage_android.model.Worker
import team.retum.savage_android.model.base.BaseResponse

interface MainApi {
    @GET("auth/signin")
    suspend fun getWorkers(): BaseResponse<List<Worker>>
}