package com.example.ns_fe_project.service

import com.example.ns_fe_project.model.Departments
import com.example.ns_fe_project.model.Products
import retrofit2.http.GET
import retrofit2.http.Path

interface NSService {
    @GET("api/v1/departments")
    suspend fun getDepartments(): List<Departments>

    @GET("api/v1/departments/{departmentId}/products")
    suspend fun getProducts(@Path("departmentId") departmentId: String): List<Products>
}