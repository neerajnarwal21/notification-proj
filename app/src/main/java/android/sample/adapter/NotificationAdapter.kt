package android.sample.adapter

import android.sample.data.User
import android.sample.databinding.AdapterDataBindingBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class NotificationAdapter(private val items: ArrayList<User>) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterDataBindingBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(val binding: AdapterDataBindingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}