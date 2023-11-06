package com.example.nimbletest.domain

import com.example.nimbletest.domain.entities.User
import com.example.nimbletest.infrastructure.repositories.NimbleRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetUserDataUseCaseTest{
    @RelaxedMockK
    private lateinit var nimbleRepository: NimbleRepositoryImpl

    lateinit var getUserDataUseCase: GetUserDataUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getUserDataUseCase = GetUserDataUseCase(nimbleRepository)
    }

    @Test
    fun `when api return a valid user`() = runBlocking {
        //given
        val user = User("email","name","avatar")
        coEvery { nimbleRepository.getUserData("token") } returns user
        //when
        val response = getUserDataUseCase("token")
        //then
        assert(user == response)
    }

    @Test
    fun `when api return a bad user request`() = runBlocking {
        //given
        val user = User("","","")
        coEvery { nimbleRepository.getUserData("badtoken") } returns user
        //when
        val response = getUserDataUseCase("badtoken")
        //then
        assert(response == user)
    }
}