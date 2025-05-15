package com.example.native_task_catalift.presentation.screen.profession

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfessionViewModel : ViewModel(){
    private val _professions = listOf("Software Engineer", "Doctor", "Designer", "Teacher")
    private val _companies = listOf("Apple", "Google", "Microsoft", "Tesla")

    val professions: List<String> get() = _professions
    val companies: List<String> get() = _companies

    private val _selectedProfession = MutableStateFlow(_professions.first())
    val selectedProfession: StateFlow<String> = _selectedProfession

    private val _selectedCompany = MutableStateFlow(_companies.first())
    val selectedCompany: StateFlow<String> = _selectedCompany

    fun setProfession(profession: String) {
        _selectedProfession.value = profession
    }

    fun setCompany(company: String) {
        _selectedCompany.value = company
    }
}