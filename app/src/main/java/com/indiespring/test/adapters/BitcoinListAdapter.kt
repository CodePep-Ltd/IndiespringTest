package com.indiespring.test.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.indiespring.test.data.Bitcoin
import com.indiespring.test.databinding.BitcoinItemLayoutBinding
import com.indiespring.test.views.IBitcoinListView


class BitcoinListAdapter(private var items: List<Bitcoin>, private var view: IBitcoinListView?, private val context: Context)
    : RecyclerView.Adapter<BitcoinListAdapter.BitcoinHolder>() {

    private lateinit var binding: BitcoinItemLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BitcoinHolder {
        binding = BitcoinItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BitcoinHolder(binding, view, context)
    }

    override fun onBindViewHolder(holder: BitcoinHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.count()

    class BitcoinHolder(private val itemBinding: BitcoinItemLayoutBinding,
                        private var view: IBitcoinListView?,
                        private val context: Context): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Bitcoin) {
            itemBinding.mainText.text = item.name
            itemBinding.subText.text = item.type
            val colour = if (item.isActive)
                ContextCompat.getColor(context, android.R.color.holo_green_light)
            else ContextCompat.getColor(context, android.R.color.holo_red_light)
            itemBinding.listImageActive.setBackgroundColor(colour)
            itemBinding.mainLayout.setOnClickListener {
                view?.onItemClicked(item.id)
            }
        }
    }
}