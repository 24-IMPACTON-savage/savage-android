
package team.retum.savage_android.model.base

data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T,
)