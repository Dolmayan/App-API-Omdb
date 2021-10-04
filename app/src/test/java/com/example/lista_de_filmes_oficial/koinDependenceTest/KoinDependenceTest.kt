package com.example.lista_de_filmes_oficial.koinDependenceTest

import android.app.Application
import android.telephony.TelephonyManager
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.SavedStateHandle
import com.example.di.AppModule
import com.google.common.base.Defaults.defaultValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.ArgumentMatchers.any

class AppDependencyTest {


    @get:Rule
    val observerRule = InstantTaskExecutorRule()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz -> mockkObject(clazz.java) }

    @Before
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `check module`() {
        val mockContext = mockk<Application> {
            every { getSystemService(any()) } returns mockk<TelephonyManager>(relaxUnitFun = true)
        }
        koinApplication {
            androidContext(mockContext)
            modules(AppModule.appModule)
        }.checkModules {
            defaultValue<SavedStateHandle>(mockk())
        }
    }
}
