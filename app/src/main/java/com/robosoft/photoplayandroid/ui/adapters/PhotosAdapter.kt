package com.example.myapplication.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robosoft.photoplayandroid.R
import com.robosoft.photoplayandroid.data.model.Photo
import com.robosoft.photoplayandroid.data.model.Src
import com.robosoft.photoplayandroid.ui.fragments.PhotosFragment
import com.robosoft.photoplayandroid.utils.setImage
import javax.xml.transform.Source


class PhotosAdapter(private val itemClickListener: ((Pair<ACTION, Photo>) -> Unit)? = null,
private val screenSize: PhotosFragment.ScreenSize) :
    RecyclerView.Adapter<PhotosAdapter.DataViewHolder>() {

    private var itemList: List<Photo>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return DataViewHolder(view,screenSize)
    }

    override fun getItemCount(): Int = itemList?.size ?: 0

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        itemList?.get(position)?.let {
            holder.bind(it)
        }
        setViewClickListener(holder, position)
    }

    private fun setViewClickListener(
        holder: DataViewHolder,
        position: Int
    ) {
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(Pair(ACTION.CARD_CLICK, itemList!![position]))
        }
        holder.itemView.findViewById<ImageView>(R.id.favorite).setOnClickListener {
            itemClickListener?.invoke(Pair(ACTION.BOOKMARK, itemList!![position]))
        }
    }

    class DataViewHolder(itemView: View,private val screenSize: PhotosFragment.ScreenSize) : RecyclerView.ViewHolder(itemView) {

        fun bind(result: Photo) {
            itemView.findViewById<ImageView>(R.id.image)
                .setImage(getImage(result.src))
            itemView.findViewById<TextView>(R.id.photographer).text = result.photographer
        }

        private fun getImage(source: Src):String{
            return when(screenSize){
                PhotosFragment.ScreenSize.NORMAL-> source.medium
                PhotosFragment.ScreenSize.SMALL-> source.small
                PhotosFragment.ScreenSize.LARGE-> source.large
                PhotosFragment.ScreenSize.X_LARGE-> source.large2x
                PhotosFragment.ScreenSize.LANDSCAPE-> source.landscape
            }
        }
    }

    fun addData(list: List<Photo>) {
        itemList = list
    }
}

enum class ACTION {
    BOOKMARK,
    CARD_CLICK
}
