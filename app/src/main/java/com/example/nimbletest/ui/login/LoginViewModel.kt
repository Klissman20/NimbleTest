package com.example.nimbletest.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nimbletest.domain.GetTokenUseCase
import com.example.nimbletest.domain.LoginUseCase
import com.example.nimbletest.domain.SetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val setTokenUseCase: SetTokenUseCase
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private val _isAuthenticated = MutableLiveData<Boolean>()
    val isAuthenticated: LiveData<Boolean> = _isAuthenticated

    private val _hasToken = MutableLiveData<Boolean>()
    val hasToken: LiveData<Boolean> = _hasToken

    fun onLoginChanged(email: String, password: String?) {
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email)
    }

    private fun enableLogin(email: String /*password: String*/): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() //&& password.length > 6

    fun onLoginSelected() {
        viewModelScope.launch {
            val result = loginUseCase(email.value!!, password.value!!)
            if (!result.accessToken.isNullOrEmpty()) {
                saveTokenValue(result.accessToken)
            }
        }
    }

    fun onCreate() {
        getTokenValue()
    }

    private fun getTokenValue() {
        viewModelScope.launch {
            getTokenUseCase("token")?.let {
                _hasToken.value = true
            }
        }
    }

    private fun saveTokenValue(token: String) {
        viewModelScope.launch {
            setTokenUseCase("token", token)
        }
    }

    fun onResetPasswordSelected() {
        viewModelScope.launch {

        }
    }
}