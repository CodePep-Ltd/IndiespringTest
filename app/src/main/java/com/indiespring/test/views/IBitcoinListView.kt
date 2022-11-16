package com.indiespring.test.views

import com.indiespring.test.data.Bitcoin

interface IBitcoinListView {
    fun showBitcoins(items: List<Bitcoin>)
    fun onItemClicked(id: String?)
    fun onEmptyList()
}