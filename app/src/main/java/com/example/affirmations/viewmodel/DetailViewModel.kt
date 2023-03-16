package com.example.affirmations.viewmodel

import androidx.lifecycle.ViewModel
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel(affirmationID: Int) : ViewModel() {
    private val currentDetail = setAffirmation(affirmationID)
    private val uiState = MutableStateFlow(currentDetail)
    val _uiState: StateFlow<Affirmation> = uiState.asStateFlow()

    private fun setAffirmation(affirmationID: Int) : Affirmation{
        val loadAffirmations = Datasource().loadAffirmations()
        for (Affirmation in loadAffirmations){
            if (Affirmation.id == affirmationID){ return Affirmation }
        }
        return loadAffirmations[0]
    }
}