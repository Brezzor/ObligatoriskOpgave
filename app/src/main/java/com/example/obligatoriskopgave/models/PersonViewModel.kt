package com.example.obligatoriskopgave.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.obligatoriskopgave.repository.PersonRepository
import com.google.firebase.auth.FirebaseAuth

class PersonViewModel: ViewModel() {
    private val repository = PersonRepository()
    val personLiveData: LiveData<List<Person>> = repository.personLiveData
    val errorMessageLiveData: LiveData<String> = repository.errorMessageLiveData
    val updateMessageLiveData: LiveData<String> = repository.updateMessageLiveData
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        reload(auth.currentUser!!.email!!.toString())
    }

    fun reload(userId: String) {
        repository.getPersonsByUserId(userId)
    }

    operator fun get(index: Int): Person? {
        return personLiveData.value?.get(index)
    }

    fun add(person: Person) {
        repository.addPerson(person)
    }

    fun delete(id: Int) {
        repository.deletePerson(id)
    }

    fun update(person: Person) {
        repository.updatePerson(person)
    }

    fun filter(filterText: String?) {
        repository.filter(filterText)
    }
}