package com.indiespring.test.activities

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.indiespring.test.R
import com.indiespring.test.adapters.BitcoinListAdapter
import com.indiespring.test.api.BitcoinApi
import com.indiespring.test.data.Bitcoin
import com.indiespring.test.databinding.BitcoinListLayoutBinding
import com.indiespring.test.presenters.BitcoinListPresenter
import com.indiespring.test.views.IBitcoinListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BitcoinListActivity : Activity(), IBitcoinListView {
    private lateinit var presenter: BitcoinListPresenter
    private lateinit var binding: BitcoinListLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BitcoinListLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = BitcoinListPresenter(this)

        binding.bitcoinsRecycler.layoutManager = LinearLayoutManager(this)
        binding.bitcoinsRecycler.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
        )

        binding.pullToRefresh.setOnRefreshListener {
            binding.pullToRefresh.isRefreshing = true
            invokeMainListApiRequest()
        }

        binding.loadingBar.visibility = View.VISIBLE
        invokeMainListApiRequest()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    override fun showBitcoins(items: List<Bitcoin>) {
        binding.loadingBar.visibility = View.GONE
        binding.bitcoinsRecycler.adapter = BitcoinListAdapter(items, this, this)
    }

    override fun onItemClicked(id: String?) {
        // TODO Go to Bitcoin main details activity, pass id with bundle and make API request.
    }

    override fun onEmptyList() {
        binding.loadingBar.visibility = View.GONE
        binding.infoTextView.visibility = View.VISIBLE
        binding.infoTextView.text = getString(R.string.empty_list_msg)
    }

    private fun invokeMainListApiRequest() {
        binding.infoTextView.visibility = View.GONE

        BitcoinApi.get().getBitcoinsList().enqueue(
            object : Callback<List<Bitcoin>> {
                override fun onResponse(call: Call<List<Bitcoin>>, response: Response<List<Bitcoin>>) {
                    binding.pullToRefresh.isRefreshing = false
                    when {
                        response.isSuccessful -> {
                            val body = response.body()
                            if (body != null) {
                                presenter.orderByRankAndPresent(body)
                            } else {
                                onError()
                            }
                        }
                        else -> {
                            onError()
                        }
                    }
                }
                override fun onFailure(call: Call<List<Bitcoin>>, t: Throwable) {
                    onError()
                }
            }
        )
    }

    private fun onError() {
        binding.loadingBar.visibility = View.GONE
        binding.infoTextView.visibility = View.VISIBLE
        binding.infoTextView.text = getString(R.string.error_msg)
    }
}