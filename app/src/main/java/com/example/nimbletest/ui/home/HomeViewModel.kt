package com.example.nimbletest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nimbletest.domain.GetSurveysUseCase
import com.example.nimbletest.domain.GetTokenUseCase
import com.example.nimbletest.domain.entities.Survey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSurveysUseCase: GetSurveysUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {

    private val _surveyList = MutableLiveData<List<Survey>>()
    val surveyList : LiveData<List<Survey>> = _surveyList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _token = MutableLiveData<String>()
    private val token : LiveData<String> = _token

    fun onCreate() {
        getTokenValue()
        viewModelScope.launch {
            val result = getSurveysUseCase("1","9", token.value!!)
            if(!result.isNullOrEmpty()){
                _surveyList.value = result
                _isLoading.value = false
            }
        }
    }

    private fun getTokenValue() {
        viewModelScope.launch {
            getTokenUseCase("token")?.let {
                _token.value = it
            }
        }
    }

}