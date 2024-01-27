package team.retum.savage_android.model.request

data class WritePostRequest (
    val location: List<RequestLocation>,
    val todo: String,
    val payment: Int,
    val unit: String,
    val time: String,
)