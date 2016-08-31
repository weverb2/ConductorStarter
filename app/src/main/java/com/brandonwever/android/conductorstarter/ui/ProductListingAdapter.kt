package com.brandonwever.android.conductorstarter.ui

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.brandonwever.android.conductorstarter.data.lcbo.model.Product
import com.squareup.picasso.Picasso

class ProductListingAdapter : RecyclerView.Adapter<ProductListingAdapter.ProductViewHolder>() {

    var products: List<Product> = mutableListOf()

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.productName.text = product.name
        holder.productUnitType.text = product.packageUnitType
        holder.setImageUrl(product.imageThumbUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = ProductListingView(parent.context)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ProductViewHolder : RecyclerView.ViewHolder {
        var productName: TextView
        var productUnitType: TextView
        var productThumbnail: ImageView

        constructor(itemView: ProductListingView) : super(itemView) {
            productName = itemView.productName
            productUnitType = itemView.productUnitType
            productThumbnail = itemView.productImage
        }

        fun setImageUrl(imageUrl: String?) {
            Picasso.with(itemView.context).load(imageUrl).fit().into(productThumbnail)
        }
    }
}