package com.example.nimbletest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nimbletest.domain.ClearPreferencesUseCase
import com.example.nimbletest.domain.GetSurveysUseCase
import com.example.nimbletest.domain.GetTokenUseCase
import com.example.nimbletest.domain.LogOutUseCase
import com.example.nimbletest.domain.SetTokenUseCase
import com.example.nimbletest.domain.entities.Survey
import com.example.nimbletest.infrastructure.model.LogOutBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSurveysUseCase: GetSurveysUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val clearPreferencesUseCase: ClearPreferencesUseCase
) : ViewModel() {

    private val _surveyList = MutableLiveData<List<Survey>>()
    val surveyList : LiveData<List<Survey>> = _surveyList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _token = MutableLiveData<String>()
    private val token : LiveData<String> = _token

    fun onCreate() {
        viewModelScope.launch {
            getTokenUseCase("token")?.let {
                _token.value = it
            }
            val result = getSurveysUseCase("1","4", token.value!!)
            if(!result.isNullOrEmpty()){
                _surveyList.value = result
                _isLoading.value = false
            }else {

            }
        }
    }

    fun logOut(){
        viewModelScope.launch {
            val logOutBody = LogOutBody(
                token = token.value!!,
                clientId = "6GbE8dhoz519l2N_F99StqoOs6Tcmm1rXgda4q__rIw",
                clientSecret = "_ayfIm7BeUAhx2W1OUqi20fwO3uNxfo1QstyKlFCgHw"
            )
            logOutUseCase(logOutBody)
            clearPreferencesUseCase("token")
            clearPreferencesUseCase("refresh_token")
        }
    }

}