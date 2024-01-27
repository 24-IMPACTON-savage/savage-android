package team.retum.savage_android.data.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import team.retum.savage_android.model.request.SignInRequest
import team.retum.savage_android.model.response.SignInResponse

interface AuthApi {
    @POST("/signIn")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): SignInResponse
}