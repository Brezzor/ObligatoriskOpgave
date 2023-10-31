package com.example.obligatoriskopgave.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.obligatoriskopgave.models.Person
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PersonRepository {
    private val baseUrl = "https://birthdaysrest.azurewebsites.net/api/"
    private val personService: PersonService
    val personLiveData: MutableLiveData<List<Person>> = MutableLiveData<List<Person>>()
    val errorMessageLiveData: MutableLiveData<String> = MutableLiveData()
    val updateMessageLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        val build: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        personService = build.create(PersonService::class.java)
        getPersons()
    }

    fun getPersons() {
        personService.getAllPersons().enqueue(object : Callback<List<Person>> {
            override fun onResponse(call: Call<List<Person>>, response: Response<List<Person>>) {
                if (response.isSuccessful) {
                    val b: List<Person>? = response.body()
                    personLiveData.postValue(b!!)
                    errorMessageLiveData.postValue("")
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageLiveData.postValue(message)
                    Log.d("APPLE", message)
                }
            }

            override fun onFailure(call: Call<List<Person>>, t: Throwable) {
                errorMessageLiveData.postValue(t.message)
                Log.d("APPLE", t.message!!)
            }
        })
    }

    fun getById(id: Int) {
        personService.getPersonById(id).enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.isSuccessful) {
                    Log.d("APPLE", "Got: " + response.body())
                    updateMessageLiveData.postValue("Got: " + response.message())
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageLiveData.postValue(message)
                    Log.d("APPLE", message)
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                errorMessageLiveData.postValue(t.message)
                Log.d("APPLE", t.message!!)
            }
        })
    }

    fun addPerson(person: Person) {
        personService.savePerson(person).enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.isSuccessful) {
                    Log.d("APPLE", "Added: " + response.body())
                    updateMessageLiveData.postValue("Added: " + response.message())
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageLiveData.postValue(message)
                    Log.d("APPLE", message)
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                errorMessageLiveData.postValue(t.message)
                Log.d("APPLE", t.message!!)
            }
        })
    }

    fun deletePerson(id: Int) {
        personService.deletePerson(id).enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.isSuccessful) {
                    Log.d("APPLE", "Deleted: " + response.body())
                    updateMessageLiveData.postValue("Deleted: " + response.message())
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageLiveData.postValue(message)
                    Log.d("APPLE", message)
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                errorMessageLiveData.postValue(t.message)
                Log.d("APPLE", t.message!!)
            }
        })
    }

    fun updatePerson(person: Person) {
        personService.updatePerson(person.id, person).enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.isSuccessful) {
                    Log.d("APPLE", "Updated: " + response.body())
                    updateMessageLiveData.postValue("Updated: " + response.message())
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageLiveData.postValue(message)
                    Log.d("APPLE", message)
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                errorMessageLiveData.postValue(t.message)
                Log.d("APPLE", t.message!!)
            }
        })
    }

    fun sortByName() {
        personLiveData.value = personLiveData.value?.sortedBy { it.name }
    }

    fun sortByNameDes() {
        personLiveData.value = personLiveData.value?.sortedByDescending { it.name }
    }

    fun sortByAge() {
        personLiveData.value = personLiveData.value?.sortedBy { it.age }
    }

    fun sortByAgeDes() {
        personLiveData.value = personLiveData.value?.sortedByDescending { it.age }
    }

    fun sortByYear() {
        personLiveData.value = personLiveData.value?.sortedBy { it.birthYear }
    }

    fun sortByYearDes() {
        personLiveData.value = personLiveData.value?.sortedByDescending { it.birthYear }
    }

    fun sortByMonth() {
        personLiveData.value = personLiveData.value?.sortedBy { it.birthMonth }
    }

    fun sortByMonthDes() {
        personLiveData.value = personLiveData.value?.sortedByDescending { it.birthMonth }
    }

    fun sortByDay() {
        personLiveData.value = personLiveData.value?.sortedBy { it.birthDayOfMonth }
    }

    fun sortByDayDes() {
        personLiveData.value = personLiveData.value?.sortedByDescending { it.birthDayOfMonth }
    }

    fun filter(filterText: String?) {
        if (filterText.isNullOrEmpty()) {
            getPersons()
        } else {
            TODO()
        }
    }
}