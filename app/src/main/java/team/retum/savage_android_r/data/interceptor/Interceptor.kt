package team.retum.savage_android_r.data.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import team.retum.savage_android_r.application.SavageApp

class Interceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("TAG", SavageApp.prefs.accessToken)
        val request = chain.request().newBuilder()
            .addHeader("authorization", "Bearer ${SavageApp.prefs.accessToken}")
            .build()
        val response = chain.proceed(request)
        return response
    }
}