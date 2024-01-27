package team.retum.savage_android.data.api

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import team.retum.savage_android.model.Post
import team.retum.savage_android.model.base.BaseResponse
import team.retum.savage_android.model.request.PathPostRequest
import team.retum.savage_android.model.request.WritePostRequest

interface PostApi {
    @GET("/post")
    suspend fun getPosts(): BaseResponse<List<Post>>

    @GET("/post/{postId}")
    suspend fun getPost(postId: Int): BaseResponse<Post>

    @POST("/post/write")
    suspend fun writePost(
        writePostRequest: BaseResponse<WritePostRequest>
    )

    @PATCH("/post/{postId}")
    suspend fun updatePost(
        pathPostRequest: BaseResponse<PathPostRequest>
    )

    @DELETE("/post/{postId}")
    suspend fun deletePost(
        postId: Int
    )
}