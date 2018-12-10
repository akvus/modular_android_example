package com.waysnpaths.issueslist.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.waysnpaths.core.domain.model.Issue
import com.waysnpaths.issueslist.R

class IssuesAdapter(
    private val onClick: (issue: Issue) -> Unit
) : ListAdapter<Issue, IssuesAdapter.Holder>(ItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.issues_rv_item, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)
        holder.apply {
            cardView.setOnClickListener {
                onClick.invoke(getItem(holder.adapterPosition))
            }
            tvTitle.text = "#${item.id} - ${item.title.capitalize()}"
            tvState.text = item.state
            tvBody.text = item.body.substring(0, if (item.body.length < 200) item.body.length else 200).trim() + "..."
            ivStateIcon.setImageDrawable(ContextCompat.getDrawable(ivStateIcon.context, if (item.state == "closed") {
                R.drawable.ic_done_black_24dp
            } else {
                R.drawable.ic_error_outline_black_24dp
            }))
        }
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: CardView = view.findViewById(R.id.cardView)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvState: TextView = view.findViewById(R.id.tvState)
        val tvBody: TextView = view.findViewById(R.id.tvBody)
        val ivStateIcon: ImageView = view.findViewById(R.id.ivStateIcon)
    }

    class ItemCallback : DiffUtil.ItemCallback<Issue>() {
        override fun areItemsTheSame(oldItem: Issue, newItem: Issue): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Issue, newItem: Issue): Boolean {
            return oldItem == newItem
        }
    }
}