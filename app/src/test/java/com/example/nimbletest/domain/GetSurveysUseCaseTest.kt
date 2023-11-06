package com.example.nimbletest.domain

import com.example.nimbletest.domain.entities.Survey
import com.example.nimbletest.infrastructure.repositories.NimbleRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetSurveysUseCaseTest {

    @RelaxedMockK
    private lateinit var nimbleRepository: NimbleRepositoryImpl

    lateinit var getSurveysUseCase: GetSurveysUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getSurveysUseCase = GetSurveysUseCase(nimbleRepository)
    }

    @Test
    fun `when api return a survey list`() = runBlocking {
        //given
        val surveyList = listOf<Survey>(
            Survey("title1", "description1", "image1"),
            Survey("title2", "description2", "image2")
        )
        coEvery { nimbleRepository.getSurveys("1", "2", "AaBbCcDdEe") } returns surveyList
        //when
        val response = getSurveysUseCase("1", "2", "AaBbCcDdEe")
        //then
        assert(surveyList == response)
    }

    @Test
    fun `when api return a survey emptyList`() = runBlocking {
        //given
        coEvery { nimbleRepository.getSurveys("1", "2", "ToEWjwiwndnsjis") } returns emptyList()
        //when
        val response = getSurveysUseCase("1", "2", "BaDToKeN")
        //then
        assert(response.isEmpty())
    }
}