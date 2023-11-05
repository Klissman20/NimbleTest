package com.example.nimbletest.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nimbletest.domain.LoginUseCase
import com.example.nimbletest.ui.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isAuthenticated = MutableLiveData<Boolean>()
    val isAuthenticated: LiveData<Boolean> = _isAuthenticated

    fun onLoginChanged(email: String, password: String?) {
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email)
    }

    private fun enableLogin(email: String /*password: String*/): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() //&& password.length > 6

    fun onLoginSelected() {
        viewModelScope.launch {
            val response = loginUseCase(email.value!!, password.value!!)
            if (response == 200) {
                _isAuthenticated.value = true
            }
        }
    }

    fun onResetPasswordSelected() {
        viewModelScope.launch {

        }
    }
}