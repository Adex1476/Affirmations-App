package com.example.affirmations.viewmodel

import androidx.lifecycle.ViewModel
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val uiState = MutableStateFlow(Datasource().loadAffirmations())
    val _uiState: StateFlow<List<Affirmation>> = uiState.asStateFlow()
}