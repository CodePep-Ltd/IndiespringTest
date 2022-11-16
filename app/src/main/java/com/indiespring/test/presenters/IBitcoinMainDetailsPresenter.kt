package com.indiespring.test.presenters

import com.indiespring.test.data.BitcoinDetails

interface IBitcoinMainDetailsPresenter {
    fun formatMainDetailsAndPresent(item: BitcoinDetails)
    fun unbind()
}