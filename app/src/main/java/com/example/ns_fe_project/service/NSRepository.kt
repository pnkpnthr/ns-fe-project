package com.example.ns_fe_project.service

import com.example.ns_fe_project.model.Departments
import com.example.ns_fe_project.model.Products
import com.example.ns_fe_project.model.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NSRepository @Inject constructor(private var service: NSService) {
    fun getDepartments(): Flow<State<List<Departments>>> {
        return flow {
            try {
                emit(State.Loading)
                val departments = service.getDepartments()
                emit(State.Success(departments))
            } catch (e: Throwable) {
                emit(State.Failed(e))
            }
        }
    }

    fun getProducts(departmentId: String): Flow<State<List<Products>>> {
        return flow {
            try {
                emit(State.Loading)
                val products = service.getProducts(departmentId)
                emit(State.Success(products))
            } catch (e: Throwable) {
                emit(State.Failed(e))
            }
        }
    }
}