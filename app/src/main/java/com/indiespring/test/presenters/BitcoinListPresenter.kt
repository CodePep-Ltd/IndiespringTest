package com.indiespring.test.presenters

import com.indiespring.test.data.Bitcoin
import com.indiespring.test.views.IBitcoinListView

class BitcoinListPresenter(private var view: IBitcoinListView?) : IBitcoinListPresenter {
    override fun orderByRankAndPresent(items: List<Bitcoin>) {
        if (items.isEmpty()) {
            view?.onEmptyList()
        } else {
            val orderedItems = items.sortedBy { it.rank }
            view?.showBitcoins(orderedItems)
        }
    }

    override fun unbind() {
        view = null
    }
}