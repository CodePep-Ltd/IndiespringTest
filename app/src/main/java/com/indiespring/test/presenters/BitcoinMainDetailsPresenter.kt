package com.indiespring.test.presenters

import com.indiespring.test.data.BitcoinDetails
import com.indiespring.test.views.IBitcoinMainDetailsView

class BitcoinMainDetailsPresenter(private var view: IBitcoinMainDetailsView?) : IBitcoinMainDetailsPresenter {
    override fun formatMainDetailsAndPresent(item: BitcoinDetails) {

    }

    override fun unbind() {
        TODO("Not yet implemented")
    }
}