package com.example.ns_fe_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ns_fe_project.model.Departments
import com.example.ns_fe_project.model.Products
import com.example.ns_fe_project.service.NSRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentsViewModel @Inject constructor(
    private var repository: NSRepository
) : ViewModel() {
    private var _dept = MutableLiveData<List<Departments>>()
    private var _prod = MutableLiveData<List<Products>>()
    private val _loading by lazy { MutableLiveData<Boolean>() }
    private val _error by lazy { MutableLiveData<Boolean>() }
    val dept: LiveData<List<Departments>> = _dept
    val prod: LiveData<List<Products>> = _prod
    val loading: LiveData<Boolean> = _loading
    val error: LiveData<Boolean> = _error

    fun getDepartment() {
        viewModelScope.launch {
            repository.getDepartments().collect { response ->
                when {
                    response.isLoading -> {
                        _loading.value = true
                        _error.value = false
                    }
                    response.isFailed -> {
                        _loading.value = false
                        _error.value = true
                    }
                    else -> {
                        //Success
                        _dept.value = response.valueOrNull
                        _loading.value = false
                        _error.value = false
                    }
                }
            }
        }
    }

    fun getProduct(prodId: String) {
        viewModelScope.launch {
            repository.getProducts(prodId).collect { response ->
                when {
                    response.isLoading -> {
                        _loading.value = true
                        _error.value = false
                    }
                    response.isFailed -> {
                        _loading.value = false
                        _error.value = true
                    }
                    else -> {
                        //Success
                        _prod.value = response.valueOrNull
                        _loading.value = false
                        _error.value = false
                    }
                }
            }
        }
    }
}