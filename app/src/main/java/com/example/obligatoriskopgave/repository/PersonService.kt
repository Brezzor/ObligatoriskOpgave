package com.example.obligatoriskopgave.repository

import com.example.obligatoriskopgave.models.Person
import retrofit2.Call
import retrofit2.http.*

interface PersonService {
    @GET("Persons")
    fun getPersonsByUserId(@Query("user_id") userId: String): Call<List<Person>>

    @POST("Persons")
    fun savePerson(@Body person: Person): Call<Person>

    @DELETE("Persons/{id}")
    fun deletePerson(@Path("id") id: Int): Call<Person>

    @PUT("Persons/{id}")
    fun updatePerson(@Path("id") id: Int, @Body person: Person): Call<Person>
}