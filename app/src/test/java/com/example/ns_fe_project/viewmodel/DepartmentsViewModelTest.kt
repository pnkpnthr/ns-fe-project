package com.example.ns_fe_project.viewmodel

import com.example.ns_fe_project.InstantTaskExecutorRule
import com.example.ns_fe_project.MainCoroutineRule
import com.example.ns_fe_project.model.Departments
import com.example.ns_fe_project.model.Products
import com.example.ns_fe_project.model.State
import com.example.ns_fe_project.service.NSRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DepartmentsViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: NSRepository
    private lateinit var viewModel: DepartmentsViewModel


    @Before
    fun setup() {
        repository = mock()
        viewModel = DepartmentsViewModel(repository)
    }

    @Test
    fun `test getDepartment success`() = runTest {
        // Mock Data
        val departments = listOf(Departments(ID_1, NAME_1, IMAGE_URL_1))
        whenever(repository.getDepartments()).thenReturn(flowOf(State.Success(departments)))

        // When
        viewModel.getDepartment()
        advanceUntilIdle()

        // Then
        verify(repository).getDepartments()
        assertEquals(departments, viewModel.dept.value)
        assertEquals(false, viewModel.loading.value)
        assertEquals(false, viewModel.error.value)
    }

    @Test
    fun `test getDepartment failure`() = runTest {
        // Mock failure response
        whenever(repository.getDepartments()).thenReturn(flowOf(State.Failed(Exception("Failed to get departments"))))

        // Call the method
        viewModel.getDepartment()
        advanceUntilIdle()

        // Verify that repository method is called
        verify(repository).getDepartments()

        // Verify LiveData values
        assertEquals(null, viewModel.dept.value)
        assertEquals(false, viewModel.loading.value)
        assertEquals(true, viewModel.error.value)
    }

    @Test
    fun `test getProduct success`() = runTest {
        // Mock data
        val products = mock<List<Products>>()
        whenever(repository.getProducts(ID_1)).thenReturn(flowOf(State.Success(products)))

        // Call the method
        viewModel.getProduct(ID_1)
        advanceUntilIdle()

        // Verify that repository method is called
        verify(repository).getProducts(ID_1)

        // Verify LiveData values
        assertEquals(products, viewModel.prod.value)
        assertEquals(false, viewModel.loading.value)
        assertEquals(false, viewModel.error.value)
    }

    @Test
    fun `test getProduct failure`() = runTest {
        // Mock failure response
        whenever(repository.getProducts(ID_1)).thenReturn(flowOf(State.Failed(Exception("Failed to get products"))))

        // Call the method
        viewModel.getProduct(ID_1)
        advanceUntilIdle()

        // Verify that repository method is called
        verify(repository).getProducts(ID_1)

        // Verify LiveData values
        assertEquals(null, viewModel.prod.value)
        assertEquals(false, viewModel.loading.value)
        assertEquals(true, viewModel.error.value)
    }

    companion object {
        private const val ID_1 = "1"
        private const val NAME_1 = "NAME_1"
        private const val IMAGE_URL_1 = "IMAGE_URL_1"
    }
}