package com.example.ns_fe_project.view.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ns_fe_project.R
import com.example.ns_fe_project.databinding.FragmentDepartmentBinding
import com.example.ns_fe_project.model.Departments
import com.example.ns_fe_project.model.Products
import com.example.ns_fe_project.view.adapter.DepartmentAdapter
import com.example.ns_fe_project.view.adapter.ProductAdapter
import com.example.ns_fe_project.view.bindingadapter.setErrorVisibility
import com.example.ns_fe_project.view.bindingadapter.setLoadingVisibility
import com.example.ns_fe_project.view.custom.CustomDialog
import com.example.ns_fe_project.viewmodel.DepartmentsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DepartmentFragment : Fragment() {

    @Inject
    lateinit var string: String

    private lateinit var binding: FragmentDepartmentBinding
    private lateinit var departmentsViewModel: DepartmentsViewModel
    private val departmentsAdapter = DepartmentAdapter(::onDeptClick)
    private val productsAdapter = ProductAdapter(::onProdClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        departmentsViewModel = ViewModelProvider(this)[DepartmentsViewModel::class.java]
        binding = FragmentDepartmentBinding.inflate(inflater, container, false)
        binding.viewModel = departmentsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
        departmentsViewModel.getDepartment()
        departmentsViewModel.getProduct(INIT_PRODUCT_ID)
    }

    private fun initView() {
        binding.rvDepartmentList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = departmentsAdapter
        }
        binding.rvProductList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = productsAdapter
        }
    }

    private fun initObservers() {
        departmentsViewModel.apply {
            loading.observe(viewLifecycleOwner) {
                binding.clProgress.setLoadingVisibility(it)
            }
            error.observe(viewLifecycleOwner) {
                binding.tvError.setErrorVisibility(it)
                if (it) {
                    binding.rvProductList.visibility = View.GONE
                } else {
                    binding.rvProductList.visibility = View.VISIBLE
                }
            }
            dept.observe(viewLifecycleOwner) { dept ->
                departmentsAdapter.apply {
                    updateDeptList(dept)
                    notifyDataSetChanged()
                }
                binding.tvProdHeadName.text = context?.getString(R.string.default_prod_head_name, dept[0].name) ?: EMPTY_STRING
            }
            prod.observe(viewLifecycleOwner) { prod ->
                productsAdapter.apply {
                    updateProdList(prod)
                    notifyDataSetChanged()
                }
            }
        }
    }

    private fun onDeptClick(deptName: String, deptId: String) {
        binding.tvProdHeadName.text = context?.getString(R.string.default_prod_head_name, deptName) ?: EMPTY_STRING
        departmentsViewModel.getProduct(deptId)
    }

    private fun onProdClick(desc: String) {
        activity?.run {
            CustomDialog(this)
                .build()
                .setDescription(desc)
                .withOnlyButton(getString(R.string.close_text),
                    DialogInterface.OnClickListener {_, _ -> //nothing
                    })
                .show()
        }
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val INIT_PRODUCT_ID = "1"
        private const val DEPARTMENTS_EXTRA = "DEPARTMENTS_EXTRA"
        private const val PRODUCTS_EXTRA = "PRODUCTS_EXTRA"

        @JvmStatic
        fun newInstance(departments: Departments?, products: Products?) =
            DepartmentFragment().apply {
                arguments = bundleOf(
                    DEPARTMENTS_EXTRA to departments,
                    PRODUCTS_EXTRA to products
                )
            }
    }
}