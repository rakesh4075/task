package com.agonsoft.task

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GetData {
    //Specify the request type and pass the relative URL//

    //Specify the request type and pass the relative URL//
    @GET("/users")
    fun  //Wrap the response in a Call object with the type of the expected result//
            getAllUsers(): Call<userListData>

    @GET("/users/{id}")
    fun getPostWithID(@Path("id") id: Int): Call<UserData>?
}