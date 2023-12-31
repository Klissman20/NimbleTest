package com.example.nimbletest.ui.login

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nimbletest.domain.ForgotPasswordUseCase
import com.example.nimbletest.domain.GetTokenUseCase
import com.example.nimbletest.domain.LoginUseCase
import com.example.nimbletest.domain.SetTokenUseCase
import com.example.nimbletest.utils.USER_PREFERENCE_REFRESH_TOKEN
import com.example.nimbletest.utils.USER_PREFERENCE_TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val setTokenUseCase: SetTokenUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private val _hasToken = MutableLiveData<Boolean>()
    val hasToken: LiveData<Boolean> = _hasToken

    fun onLoginChanged(email: String, password: String?) {
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email , password ?: "")
    }

    private fun enableLogin(email: String , password: String): Boolean =
        PatternsCompat.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5

    fun onLoginSelected() {
        viewModelScope.launch {
            val result = loginUseCase(email.value!!, password.value!!)
            if (!result.accessToken.isNullOrEmpty()) {
                setTokenUseCase(USER_PREFERENCE_TOKEN, result.accessToken)
                setTokenUseCase(USER_PREFERENCE_REFRESH_TOKEN, result.refreshToken)
                _hasToken.value = true
            }
        }
    }
    fun onCreate(key: String) {
        viewModelScope.launch {
            getTokenUseCase(key)?.let {
                if (!it.isNullOrEmpty()) _hasToken.value = true
            }
        }
    }
    fun onResetPasswordSelected() {
        viewModelScope.launch {
            forgotPasswordUseCase(email.value ?: "")
        }
    }
}