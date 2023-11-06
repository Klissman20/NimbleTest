package com.example.nimbletest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nimbletest.domain.ClearPreferencesUseCase
import com.example.nimbletest.domain.GetSurveysUseCase
import com.example.nimbletest.domain.GetTokenUseCase
import com.example.nimbletest.domain.GetUserDataUseCase
import com.example.nimbletest.domain.LogOutUseCase
import com.example.nimbletest.domain.RefreshTokenUseCase
import com.example.nimbletest.domain.SetTokenUseCase
import com.example.nimbletest.domain.entities.Survey
import com.example.nimbletest.domain.entities.User
import com.example.nimbletest.infrastructure.model.LogOutBody
import com.example.nimbletest.infrastructure.model.RefreshTokenBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSurveysUseCase: GetSurveysUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val clearPreferencesUseCase: ClearPreferencesUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val setTokenUseCase: SetTokenUseCase,
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {

    private val _surveyList = MutableLiveData<List<Survey>>()
    val surveyList : LiveData<List<Survey>> = _surveyList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _token = MutableLiveData<String>()
    private val token : LiveData<String> = _token

    private val _userData = MutableLiveData<User>()
    val userData : LiveData<User> = _userData

    fun onCreate() {
        viewModelScope.launch {

            getTokenUseCase("token")?.let {
                _token.value = it
            }
            val result = getSurveysUseCase("1","4", token.value!!)
            if(!result.isNullOrEmpty()){
                _surveyList.value = result
                getUserDataUseCase(token.value!!).let {
                    _userData.value = it
                }
                _isLoading.value = false
            }else {
                val refreshToken = getTokenUseCase("refresh_token")
                val refreshTokenBody = RefreshTokenBody (
                    grantType = "refresh_token",
                    refreshToken = refreshToken ?: "",
                    clientId = "6GbE8dhoz519l2N_F99StqoOs6Tcmm1rXgda4q__rIw",
                    clientSecret = "_ayfIm7BeUAhx2W1OUqi20fwO3uNxfo1QstyKlFCgHw"
                )
                var result = refreshTokenUseCase(refreshTokenBody)
                if (!result.accessToken.isNullOrEmpty()) {
                    setTokenUseCase("token", result.accessToken)
                    setTokenUseCase("refresh_token", result.refreshToken)
                    delay(500)

                }
                getTokenUseCase("token")?.let {
                    _token.value = it
                }
                val resultSurvey = getSurveysUseCase("1","4", token.value!!)
                _surveyList.value = resultSurvey
                getUserDataUseCase(token.value!!).let {
                    _userData.value = it
                }
                _isLoading.value = false
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