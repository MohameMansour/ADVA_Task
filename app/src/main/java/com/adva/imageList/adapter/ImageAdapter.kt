package com.adva.imageList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adva.databinding.ItemImageBinding
import com.bumptech.glide.Glide
import com.adva.imageList.model.ImageModelItem
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

class ImageAdapter(
    val imageList: List<ImageModelItem>,
    val onImageClickListener: OnImageClickListener
) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding =
            ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.imageModel = imageList[position]
        holder.bindViews(position)

        holder.binding.root.setOnClickListener {
            onImageClickListener.onImageClicked(imageList[position])
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    inner class ViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindViews(position: Int) {
            val imagePosition = imageList[position]
            imagePosition.let {

                val fullUrl = it.thumbnailUrl

                val url = GlideUrl(
                    fullUrl, LazyHeaders.Builder()
                        .addHeader("User-Agent", "your-user-agent")
                        .build()
                )

                Glide
                    .with(binding.root.context)
                    .load(url)
                    .into(binding.profileDoctorImage)
            }
        }

    }

    interface OnImageClickListener {
        fun onImageClicked(imageModelItem: ImageModelItem)
    }
}