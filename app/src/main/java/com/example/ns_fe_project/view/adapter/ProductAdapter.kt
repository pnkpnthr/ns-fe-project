package com.example.ns_fe_project.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ns_fe_project.R
import com.example.ns_fe_project.databinding.ItemProductsBinding
import com.example.ns_fe_project.loadImage
import com.example.ns_fe_project.model.Products

class ProductAdapter(
    private val callBack: (String) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProdViewHolder>() {

    private var prodList = listOf<Products>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdViewHolder {
        return ProdViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_products,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProdViewHolder, position: Int) {
        holder.bind(prodList[position])
    }

    fun updateProdList(list: List<Products>) {
        this.prodList = list
    }

    override fun getItemCount() = prodList.size

    inner class ProdViewHolder(private val binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(prod: Products) {
            binding.apply {
                this.model = prod
                this.ivProd.loadImage(prod.imageUrl)
                csProd.setOnClickListener { callBack(prod.desc) }
            }
        }
    }
}