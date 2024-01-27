package team.retum.savage_android.model.request

data class WritePostRequest (
    val location: String,
    val todo: String,
    val payment: Int,
    val unit: String,
    val time: String,
    val latitude: Double,
    val longitude: Double,
)