package com.example.nimbletest.domain

import com.example.nimbletest.domain.entities.Auth
import com.example.nimbletest.infrastructure.repositories.NimbleRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LoginUseCaseTest {

    @RelaxedMockK
    private lateinit var nimbleRepository: NimbleRepositoryImpl

    lateinit var loginUseCase: LoginUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        loginUseCase = LoginUseCase(nimbleRepository)
    }

    @Test
    fun `when api return a valid auth request`() = runBlocking {
        //Given
        val email = "esteban@example.com"
        val password = "12345678"
        val mockAuth = Auth("AaBbCcDdEe", "Bearer", 0, "FfGgHhIiJj")
        coEvery { nimbleRepository.signIn(email, password) } returns mockAuth
        //When
        val authResponse = loginUseCase(email,password)
        //Then
        assertEquals(authResponse,mockAuth)
    }

    @Test
    fun `when api return a bad auth request`() = runBlocking {
        //Given
        val email = "bad@email.com"
        val password = "098765432"
        val blankAuth = Auth("", "", 0, "")
        coEvery { nimbleRepository.signIn(email, password) } returns blankAuth
        //When
        val authResponse = loginUseCase(email,password)
        //Then
        assertEquals(authResponse,blankAuth)
    }

}