package com.example.ns_fe_project.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ns_fe_project.R
import com.example.ns_fe_project.databinding.ItemDepartmentsBinding
import com.example.ns_fe_project.loadImage
import com.example.ns_fe_project.model.Departments

class DepartmentAdapter(
    private val callBack: (String, String) -> Unit
) : RecyclerView.Adapter<DepartmentAdapter.DeptViewHolder>() {

    private var deptList = listOf<Departments>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeptViewHolder {
        return DeptViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_departments,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DeptViewHolder, position: Int) {
        holder.bind(deptList[position])
    }

    fun updateDeptList(list: List<Departments>) {
        this.deptList = list
    }

    override fun getItemCount() = deptList.size

    inner class DeptViewHolder(private val binding: ItemDepartmentsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dept: Departments) {
            binding.apply {
                model = dept
                ivDept.loadImage(dept.imageUrl)
                csDept.setOnClickListener { callBack(dept.name, dept.id) }
            }
        }
    }
}