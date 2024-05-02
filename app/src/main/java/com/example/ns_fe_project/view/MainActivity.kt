package com.example.ns_fe_project.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ns_fe_project.addFragment
import com.example.ns_fe_project.databinding.ActivityMainBinding
import com.example.ns_fe_project.model.Departments
import com.example.ns_fe_project.model.Products
import com.example.ns_fe_project.view.fragment.DepartmentFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private lateinit var departmentFragment: DepartmentFragment

    private var departments: Departments? = null
    private var products: Products? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupViewFragment()
    }

    override fun onDestroy() {
        binding = null
        departments = null
        products = null
        super.onDestroy()
    }

    private fun setupViewFragment() {
        departmentFragment = DepartmentFragment.newInstance(
            departments,
            products
        )

        binding?.flContainer?.let { addFragment(departmentFragment, it.id) }
    }
}