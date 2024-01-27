package team.retum.savage_android.data.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import team.retum.savage_android.model.base.BaseResponse
import team.retum.savage_android.model.request.SignUpRequest

interface UserApi {
    @POST("/user/senior")
    fun signUp(
        @Body signUpRequest: SignUpRequest
    ): BaseResponse<Unit>

    @GET("/user")
    fun getUser(): BaseResponse<Boolean>

    @POST("/user/visa")
    fun authenticationVisa(
        @Body passport: String
    ): BaseResponse<Boolean>
}
