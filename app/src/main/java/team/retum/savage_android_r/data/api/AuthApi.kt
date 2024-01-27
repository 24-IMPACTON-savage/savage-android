package team.retum.savage_android_r.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import team.retum.savage_android_r.model.base.BaseResponse
import team.retum.savage_android_r.model.request.SignInRequest
import team.retum.savage_android_r.model.response.SignInResponse

interface AuthApi {
    @POST("/auth/signin")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): BaseResponse<SignInResponse>
}