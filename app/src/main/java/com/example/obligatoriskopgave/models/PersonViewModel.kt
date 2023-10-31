package com.example.obligatoriskopgave.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.obligatoriskopgave.repository.PersonRepository

class PersonViewModel: ViewModel() {
    private val repository = PersonRepository()
    val personLiveData: LiveData<List<Person>> = repository.personLiveData
    val errorMessageLiveData: LiveData<String> = repository.errorMessageLiveData
    val updateMessageLiveData: LiveData<String> = repository.updateMessageLiveData

    init {
        reload()
    }

    fun reload() {
        repository.getPersons()
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
}