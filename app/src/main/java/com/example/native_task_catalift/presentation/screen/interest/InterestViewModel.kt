package com.example.native_task_catalift.presentation.screen.interest

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InterestViewModel : ViewModel() {
    private val _allInterests = List(22) { index ->
        val base = listOf("Lorem ipsum", "Lorem", "sit amet", "amet", "elit amet")
        "${base[index % base.size]}$index"
    }
    val allInterests: List<String> get() = _allInterests

    // Search query state
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _filteredInterests = MutableStateFlow(allInterests)
    val filteredInterests: StateFlow<List<String>> = _filteredInterests

    // State for selected interest indexes
    private val _selectedValues = mutableStateListOf<String>()
    val selectedValues: List<String> get() = _selectedValues

    fun toggleSelection(value: String) {
        if (_selectedValues.contains(value)) {
            _selectedValues.remove(value)
        } else {
            _selectedValues.add(value)
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        _filteredInterests.value = if (query.isBlank()) {
            allInterests
        } else {
            allInterests.filter {
                it.contains(query, ignoreCase = true)
            }
        }
    }

}