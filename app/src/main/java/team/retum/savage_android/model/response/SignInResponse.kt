package team.retum.savage_android.model.response

data class SignInResponse(
    val accessToken: String,
    val expiredAt: String,
    val errorMsg: String,
)
