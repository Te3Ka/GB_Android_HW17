package ru.te3ka.homework17.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.te3ka.homework17.databinding.ItemPhotoBinding
import ru.te3ka.homework17.model.Photo

class PhotoPagedAdapter :
    PagingDataAdapter<Photo, PhotoPagedAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {
    private var onItemClickListener: ((Photo) -> Unit)? = null

    fun setOnItemClickListener(listener: (Photo) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        photo?.let {
            holder.bind(photo)

            holder.itemView.setOnClickListener {
                onItemClickListener?.invoke(photo)
            }
        }
    }

    class PhotoViewHolder(private val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            Glide.with(itemView.context).load(photo.imgSrc).into(binding.imagePhoto)
            binding.textRoverName.text = "Rover: ${photo.rover.name}"
            binding.textCameraName.text = "Camera: ${photo.camera.name}"
            binding.textSolDate.text = "Sol: ${photo.sol}"
            binding.textEarthDate.text = "Earth Date: ${photo.earthDate}"

        }
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem == newItem
        }
    }
}