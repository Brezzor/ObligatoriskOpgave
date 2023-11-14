package com.example.obligatoriskopgave.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.Calendar

private val calendar = Calendar.getInstance()
@Parcelize
data class Person(
    val id: Int = 1,
    var userId: String? = null,
    var name: String? = null,
    var birthYear: Int = calendar.get(Calendar.YEAR),
    var birthMonth: Int = calendar.get(Calendar.MONTH),
    var birthDayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH),
    var remarks: String? = null,
    var pictureUrl: String? = null,
    val age: Int? = null
): Serializable, Parcelable
