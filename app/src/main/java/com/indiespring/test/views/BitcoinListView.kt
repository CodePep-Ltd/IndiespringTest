package com.indiespring.test.views

import com.indiespring.test.data.Bitcoin

interface BitcoinListView {
    fun showBitcoins(items: List<Bitcoin>)
    fun onEmptyList()
}