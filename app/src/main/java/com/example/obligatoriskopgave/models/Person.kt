package com.example.obligatoriskopgave.models

import java.io.Serializable
import java.util.Calendar

data class Person(
    val id: Int = 1,
    var userId: String? = null,
    var name: String? = null,
    var birthYear: Int = Calendar.getInstance().get(Calendar.YEAR),
    var birthMonth: Int = Calendar.getInstance().get(Calendar.MONTH),
    var birthDayOfMonth: Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
    var remarks: String? = null,
    var pictureUrl: Any? = null,
    val age: Int? = null
): Serializable
