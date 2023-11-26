package com.artpit.android.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.artpit.android.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.artpit.android.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.artpit.android.cryptoapp.data.model.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinViewModel
    private lateinit var binding: ActivityCoinPriceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
//                Log.d("TEST_OF_ONCLICK_ITEM", "Selected: ${coinPriceInfo.fromSymbol}")
//                val intent = Intent(this@CoinPriceListActivity, CoinDetailActivity::class.java)
//                intent.putExtra(CoinDetailActivity.EXTRA_FROM_SYMBOL, coinPriceInfo.fromSymbol)
                val coinDetailInfoIntent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(coinDetailInfoIntent)
            }

        }
        binding.rvCoinPriceList.adapter = adapter

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
//        viewModel.loadData()
        viewModel.priceList.observe(this, Observer {
//            Log.d("TEST_OF_LOADING_DATA", "Success in activity: $it")
            adapter.coinInfoList = it
        })
//
//        viewModel.getDetailInfo("BTC").observe(this, Observer {
//            Log.d("TEST_OF_LOADING_DATA", "Success in activity: $it")
//        })

    }
}
