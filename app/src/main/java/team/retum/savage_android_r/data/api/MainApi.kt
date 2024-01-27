package team.retum.savage_android_r.data.api

import retrofit2.http.GET
import team.retum.savage_android_r.model.Worker
import team.retum.savage_android_r.model.base.BaseResponse

interface MainApi {
    @GET("main/workers")
    suspend fun getWorkers(): BaseResponse<List<Worker>>
}