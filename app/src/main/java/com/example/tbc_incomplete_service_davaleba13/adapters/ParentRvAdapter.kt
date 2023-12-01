package com.example.tbc_incomplete_service_davaleba13.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_incomplete_service_davaleba13.databinding.RvCardItemBinding
import com.example.tbc_incomplete_service_davaleba13.models.Field

class ParentRvAdapter : RecyclerView.Adapter<ParentRvAdapter.ParentRvViewHolder>() {

    private var data = listOf<List<Field>>()
    val childAdapter = ChildRvAdapter()

    fun setData(data: List<List<Field>>) {
        this.data = data
    }

    inner class ParentRvViewHolder(private val binding: RvCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.rvChildItems.layoutManager = LinearLayoutManager(binding.root.context)
            childAdapter.setData(data[adapterPosition])
            binding.rvChildItems.adapter = childAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentRvViewHolder {
        return ParentRvViewHolder(
            RvCardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ParentRvViewHolder, position: Int) {
        holder.bind()
    }

    fun getFields(): Map<Int, String> = childAdapter.getAllFields()

    fun getRequiredFields(): Map<String, String> = childAdapter.getRequiredFields()
}