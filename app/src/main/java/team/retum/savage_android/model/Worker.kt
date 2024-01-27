package team.retum.savage_android.model

data class Worker(
    val workerId: Int,
    val contact: String,
    val name: String,
    val hashed: String,
    val expr: String,
    val country: String,
    val introduce: String,
    val passport: String,
)
