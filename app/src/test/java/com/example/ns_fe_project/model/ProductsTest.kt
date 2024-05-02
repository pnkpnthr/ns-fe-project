package com.example.ns_fe_project.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductsTest {
    private lateinit var products: Products

    @Before
    fun setup() {
        products = Products(
            id = ID,
            name = NAME,
            imageUrl = IMAGE_URL,
            desc = DESC,
            departmentId = DEPARTMENT_ID,
            price = PRICE,
            type = TYPE
        )
    }

    @Test
    fun `test departments`() {
        assertEquals(products.id, ID)
        assertEquals(products.name, NAME)
        assertEquals(products.imageUrl, IMAGE_URL)
        assertEquals(products.desc, DESC)
        assertEquals(products.departmentId, DEPARTMENT_ID)
        assertEquals(products.price, PRICE)
        assertEquals(products.type, TYPE)
    }

    companion object {
        private const val ID = "ID"
        private const val NAME = "NAME"
        private const val IMAGE_URL = "IMAGE_URL"
        private const val DESC = "DESC"
        private const val DEPARTMENT_ID = "DEPARTMENT_ID"
        private const val PRICE = "PRICE"
        private const val TYPE = "TYPE"
    }
}