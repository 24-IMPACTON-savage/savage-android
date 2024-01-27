package team.retum.savage_android.data.api

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import team.retum.savage_android.model.Post
import team.retum.savage_android.model.request.PathPostRequest
import team.retum.savage_android.model.request.WritePostRequest

interface PostApi {
    @GET("/post")
    fun getPosts(): Call<List<Post>>

    @GET("/post/{postId}")
    fun getPost(postId: Int): Call<Post>

    @POST("/post/write")
    fun writePost(
        writePostRequest: WritePostRequest
    ): Call<Unit>

    @PATCH("/post/{postId}")
    fun updatePost(
        pathPostRequest: PathPostRequest
    ): Call<Unit>

    @DELETE("/post/{postId}")
    fun deletePost(
        postId: Int
    ): Call<Unit>
}