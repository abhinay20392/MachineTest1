package com.net.machinetest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetest1.databinding.ListItemMainListBinding
import com.net.machinetest.data.responses.news.Article
import com.net.machinetest.setImageFromUrl
import timber.log.Timber
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class NewsAdapter(
    private var modelList: List<Article>,
    private var callback: OnItemAction
) :
    RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ListItemMainListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    /*   override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
           val model = modelList[position]

           bindViewHolder(.)
           holder.itemView.holder.itemView.tvAuther
           holder.itemView.tvTimeStamp

       }
   */

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        with(holder) {
            with(modelList[position]) {

                binding.thumbnailImage.setImageFromUrl(this.urlToImage)
                binding.tvTitle.text = this.title
                binding.tvAuther.text = this.author
                binding.tvDescription.text = this.description
                binding.tvTimeStamp.text = changeDateFormat(this.publishedAt)
                binding.root.setOnClickListener {
                    callback.onItemSelect(this.url)
                }
            }
        }
    }

    override fun getItemCount(): Int = modelList.size

    interface OnItemAction {
        fun onItemSelect(url: String)
    }


    fun changeDateFormat(date: String): String {
        Timber.d("***date :" + date)
        val df1: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        df1.timeZone = TimeZone.getTimeZone("UTC")
        val result1: Date = df1.parse(date.substring(0, 19))!!
        val sf = SimpleDateFormat("dd-MMM-yyyy 'at' HH:mm:ss", Locale.ENGLISH)
        sf.timeZone = TimeZone.getDefault()
        Timber.d("***date2 :" + sf.format(result1))
        return sf.format(result1)
    }

}


class NewsViewHolder(val binding: ListItemMainListBinding) :
    RecyclerView.ViewHolder(binding.root)