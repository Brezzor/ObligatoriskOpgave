package com.example.obligatoriskopgave.models

import java.io.Serializable

data class Person(
    val id: Int,
    val userId: String?,
    val name: String?,
    val birthYear: Int,
    val birthMonth: Int,
    val birthDayOfMonth: Int,
    val remarks: String?,
    val pictureUrl: Any?,
    val age: Int?
): Serializable
