package com.example.starwarsapp.presentation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapp.R
import com.example.starwarsapp.common.BUNDLE_KEY_PERSON
import com.example.starwarsapp.databinding.ItemPersonBinding
import com.example.starwarsapp.domain.model.PersonModel
import com.example.starwarsapp.presentation.activity.PlanetActivity

class PeoplePagingAdapter(private val context: Context) : PagingDataAdapter<PersonModel, PeoplePagingAdapter.PeoplePagingViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeoplePagingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return PeoplePagingViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeoplePagingViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class PeoplePagingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = DataBindingUtil.bind<ItemPersonBinding>(view)

        fun bind(person: PersonModel) {
            binding?.person = person
            binding?.root?.setOnClickListener {
                context.startActivity(Intent(context, PlanetActivity::class.java).putExtra(
                    BUNDLE_KEY_PERSON, person))
            }
        }
    }

    private class DiffUtilCallBack : DiffUtil.ItemCallback<PersonModel>() {
        override fun areItemsTheSame(oldItem: PersonModel, newItem: PersonModel): Boolean = oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: PersonModel, newItem: PersonModel): Boolean = oldItem == newItem
    }
}