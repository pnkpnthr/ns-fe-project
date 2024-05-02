package com.example.ns_fe_project.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DepartmentsTest {
    private lateinit var departments: Departments

    @Before
    fun setup() {
        departments = Departments(
            id = ID,
            name = NAME,
            imageUrl = IMAGE_URL
        )
    }

    @Test
    fun `test departments`() {
        assertEquals(departments.id, ID)
        assertEquals(departments.name, NAME)
        assertEquals(departments.imageUrl, IMAGE_URL)
    }

    companion object {
        private const val ID = "ID"
        private const val NAME = "NAME"
        private const val IMAGE_URL = "IMAGE_URL"
    }
}