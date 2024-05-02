package com.example.ns_fe_project.service

import com.example.ns_fe_project.model.Departments
import com.example.ns_fe_project.model.Products
import com.example.ns_fe_project.model.State
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NSRepositoryTest {
    private lateinit var nsService: NSService
    private lateinit var nsRepository: NSRepository

    @Before
    fun setup() {
        nsService = mock()
        nsRepository = NSRepository(nsService)
    }

    @Test
    fun `getDepartments success`() = runBlockingTest {
        // Mock data
        val departments = listOf(
            Departments(ID_1, NAME_1, IMAGE_URL_1),
            Departments(ID_2, NAME_2, IMAGE_URL_2)
        )
        whenever(nsService.getDepartments()).thenReturn(departments)

        // When
        val output = nsRepository.getDepartments().toList()

        // Then
        assertEquals(
            listOf(State.Loading, State.Success(departments)),
            output
        )
    }

    @Test
    fun `getProducts success`() = runBlockingTest {
        // Mock data

        val products = listOf(
            Products(id = ID_1, name = NAME_1, imageUrl = IMAGE_URL_1, departmentId = DEPARTMENT_ID_1, desc = DESC_1, type = TYPE_1, price = PRICE_1)
        )
        whenever(nsService.getProducts(EMPTY_STRING)).thenReturn(products)

        // When
        val output = nsRepository.getProducts(EMPTY_STRING).toList()

        // Then
        assertEquals(
            listOf(State.Loading, State.Success(products)),
            output
        )
    }

    companion object {
        private const val ID_1 = "1"
        private const val ID_2 = "2"
        private const val NAME_1 = "NAME_1"
        private const val NAME_2 = "NAME_2"
        private const val IMAGE_URL_1 = "IMAGE_URL_1"
        private const val IMAGE_URL_2 = "IMAGE_URL_2"
        private const val DEPARTMENT_ID_1 = "DEPARTMENT_ID_1"
        private const val DESC_1 = "DESC_1"
        private const val TYPE_1 = "TYPE_1"
        private const val PRICE_1 = "PRICE_1"
        private const val EMPTY_STRING = ""
    }
}