package com.eihror.coroutines

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eihror.coroutines.model.Post
import kotlinx.android.synthetic.main.item_post.view.*

/**
 * Created by igormelo on 08/10/2018.
 */
class MainAdapter(private val posts: ArrayList<Post>,
                  private val clickListener: (Post) -> Unit,
                  private val onLoadMoreListener: () -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = posts[position]
        (holder as ViewHolder).bindView(post, clickListener)

        if (!posts.isEmpty() || posts == null) {
            if (position == (posts.size - 1)) {
                onLoadMoreListener()
            }
        }

    }

    fun clearAll() {
        posts.clear()
        notifyDataSetChanged()
    }

    fun addToList(newList: List<Post>): ArrayList<Post> {
        posts.addAll(newList)
        notifyDataSetChanged()

        return posts
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(post: Post, clickListener: (Post) -> Unit) {
            val item = itemView.textView

            item.text = post.title

            item.setOnClickListener {
                clickListener(post)
            }

        }

    }

}