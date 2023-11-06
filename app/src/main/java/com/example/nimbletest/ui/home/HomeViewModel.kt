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
import com.example.nimbletest.utils.USER_PREFERENCE_REFRESH_TOKEN
import com.example.nimbletest.utils.USER_PREFERENCE_TOKEN
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
    val token : LiveData<String> = _token

    private val _refreshToken = MutableLiveData<String>()
    val refreshToken : LiveData<String> = _refreshToken

    private val _userData = MutableLiveData<User>()
    val userData : LiveData<User> = _userData

    fun onCreate() {
        viewModelScope.launch {

            getTokenUseCase(USER_PREFERENCE_TOKEN)?.let {
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
                getTokenUseCase(USER_PREFERENCE_REFRESH_TOKEN)?.let {
                    _refreshToken.value = it
                }

                val result = refreshTokenUseCase(refreshToken.value ?: "")
                if (!result.accessToken.isNullOrEmpty()) {
                    setTokenUseCase(USER_PREFERENCE_TOKEN, result.accessToken)
                    setTokenUseCase(USER_PREFERENCE_REFRESH_TOKEN, result.refreshToken)
                    delay(500)

                }
                getTokenUseCase(USER_PREFERENCE_TOKEN)?.let {
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
            logOutUseCase(token.value!!)
            clearPreferencesUseCase(USER_PREFERENCE_TOKEN)
            clearPreferencesUseCase(USER_PREFERENCE_REFRESH_TOKEN)
        }
    }

}