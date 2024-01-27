package team.retum.savage_android.data.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import team.retum.savage_android.model.request.SignUpRequest

interface UserApi {
    @POST("/user/signUp")
    fun signUp(
        @Body signUpRequest: SignUpRequest
    ): Call<Unit>

    @GET("/user")
    fun getUser() : Call<Boolean>

    @POST("/user/visa")
    fun authenticationVisa(
        @Body passport: String
    ) : Call<Boolean>
}