package com.demo.nytimes.ui.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.nytimes.R
import com.demo.nytimes.network.model.MostPopularArticleListModel
import kotlinx.android.synthetic.main.popular_list_row.view.*
import javax.inject.Inject

/**
 * Popular article list adapter class for bind data to the view
 *
 * @property mostPopularItemClickListener MostPopularItemClickListener
 */
class MostPopularRecycleViewAdapter @Inject constructor(
    var mostPopularItemClickListener:
    MostPopularItemClickListener?
) :
    RecyclerView.Adapter<MostPopularRecycleViewAdapter.ViewHolder>() {
    private var mostPopularDataList: List<MostPopularArticleListModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.popular_list_row, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mostPopularDataList!![position])
    }

    override fun getItemCount(): Int {
        if (mostPopularDataList != null) {
            return mostPopularDataList!!.size
        }
        return 0

    }

    fun setPopularDataList(mostPopularList: List<MostPopularArticleListModel>) {
        mostPopularDataList = mostPopularList
        notifyDataSetChanged()
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        override fun onClick(view: View) {
            if (mostPopularItemClickListener != null) {
                mostPopularItemClickListener!!.onItemClick(view, adapterPosition)
            }
        }

        fun bind(data: MostPopularArticleListModel) {
            itemView.articleTitle.text = data.storyTitle
            itemView.articleDate.text = data.publishedDate.toString()
            itemView.authorName.text = data.author
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    interface MostPopularItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
}