package com.indiespring.test.activities

import android.app.Activity
import android.os.Bundle
import com.indiespring.test.databinding.BitcoinMainDetailLayoutBinding
import com.indiespring.test.presenters.BitcoinMainDetailsPresenter
import com.indiespring.test.views.IBitcoinMainDetailsView

class BitcoinMainDetailsActivity : Activity(), IBitcoinMainDetailsView {
    private lateinit var presenter: BitcoinMainDetailsPresenter
    private lateinit var binding: BitcoinMainDetailLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BitcoinMainDetailLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = BitcoinMainDetailsPresenter(this)
    }

    override fun bindDataToView(
        name: String,
        isActive: Boolean,
        tagNames: String,
        teamMemberNames: String
    ) {

    }
}