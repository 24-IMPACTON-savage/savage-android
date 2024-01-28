package team.retum.savage_android_r.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import team.retum.savage_android_r.model.Worker
import team.retum.savage_android_r.model.base.BaseResponse
import team.retum.savage_android_r.model.request.SignUpRequest
import retrofit2.http.Query as Query1

interface UserApi {
    @POST("/user/senior")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): BaseResponse<Int>

    @GET("/user")
    suspend fun getUser(): BaseResponse<Boolean>

    @POST("/user/visa")
    suspend fun authenticationVisa(
        @Body passport: String
    ): BaseResponse<Boolean>

    @GET("/user/near")
    suspend fun getNearUser(
        @Query1 ("latitude") latitude: Double,
        @Query1 ("longitude") longitude: Double,
    ): BaseResponse<List<Worker>>
}
