package com.indiespring.test.views

interface IBitcoinMainDetailsView {
    fun bindDataToView(name: String, isActive: Boolean, tagNames: String, teamMemberNames: String)
}