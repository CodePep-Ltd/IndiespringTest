package com.indiespring.test.presenters

import com.indiespring.test.data.Bitcoin

interface IBitcoinListPresenter {
    fun orderByRankAndPresent(items: List<Bitcoin>)
    fun unbind()
}