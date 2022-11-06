package com.example.gitemup.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gitemup.R
import com.example.gitemup.common.base.AbstractViewHolder
import com.example.gitemup.common.base.BaseAdapter
import com.example.gitemup.common.base.ItemClickListener
import com.example.gitemup.data.models.domain.Item
import com.example.gitemup.databinding.LayoutRepositoryItemBinding

class RepositoriesRecyclerViewAdapter(itemClickListener: ((Item, Int) -> Unit)? = null) :
    BaseAdapter<Item, RepositoriesViewHolder>(itemClickListener) {
    override val itemLayout: Int = R.layout.layout_repository_item

    override fun createViewHolder(view: View): RepositoriesViewHolder {
        val layoutInflater = LayoutInflater.from(view.context)
        val binding = LayoutRepositoryItemBinding.inflate(layoutInflater, view as ViewGroup?, false)
        return RepositoriesViewHolder(binding)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}

class RepositoriesViewHolder(view: View) : AbstractViewHolder<Item>(view) {

    lateinit var binding: LayoutRepositoryItemBinding

    constructor(binding: LayoutRepositoryItemBinding) : this(binding.root) {
        this.binding = binding
    }


    override fun bind(model: Item, position: Int, listener: ItemClickListener<Item>) {
        binding.repository = model
        binding.executePendingBindings()

    }

}