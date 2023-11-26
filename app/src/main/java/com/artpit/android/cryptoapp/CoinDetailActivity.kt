package com.artpit.android.cryptoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.artpit.android.cryptoapp.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoinDetailBinding
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val fSym = if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            ""
        } else {
            intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: ""
        }

        if (fSym == "") {
            finish()
            return
        }

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fSym).observe(this, Observer {
//            Log.d("TEST_OF_DETAIL_INFO", "Detail info: $it")
            Picasso.get().load(it.getFullImageUrl()).into(binding.ivLogoCoin)
            binding.tvFromSymbol.text = it.fromSymbol
            binding.tvToSymbol.text = it.toSymbol
            binding.tvPrice.translationX = it.price.toFloat()
            binding.tvMinPrice.text = it.lowDay.toString()
            binding.tvMaxPrice.text = it.highDay.toString()
            binding.tvLastMarket.text = it.lastMarket
            binding.tvLastUpdate.text = it.getFormattedTime()
        })
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(CoinDetailActivity.EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}