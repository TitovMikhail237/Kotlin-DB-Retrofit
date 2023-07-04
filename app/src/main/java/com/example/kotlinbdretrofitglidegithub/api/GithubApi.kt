package com.example.kotlinbdretrofitglidegithub.api

import android.text.Editable
import com.example.kotlinbdretrofitglidegithub.UserGithub
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubApi {

    @GET("users/{user}/followers")
    fun loadUsers(
        @Path("user") user: Editable
    ): Call<List<UserGithub>>
}