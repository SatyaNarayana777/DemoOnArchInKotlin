package com.kotlin.demoinkotlin.network

import com.kotlin.demoinkotlin.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/users")
    fun getUsers(): Observable<List<UserResponse>>

    @GET("/posts")
    fun getPosts(@Query("userId") userId: Int): Observable<List<PostResponse>>

    @GET("/photos")
    fun getPhotos(): Observable<List<PhotosResponse>>

    @GET("/v2/everything?q=movies&apiKey=079dac74a5f94ebdb990ecf61c8854b7")
    fun getFeeds(@Query("page") page: Int): Observable<Result>

}