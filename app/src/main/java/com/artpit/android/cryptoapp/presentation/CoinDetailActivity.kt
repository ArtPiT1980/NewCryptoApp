package com.artpit.android.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.artpit.android.cryptoapp.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {
    private val binding: ActivityCoinDetailBinding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fSym = if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            EMPTY_SYMBOL
        } else {
            intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY_SYMBOL
        }

        if (fSym == EMPTY_SYMBOL) {
            finish()
            return
        }

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fSym).observe(this) {
            with(binding) {
                Picasso.get().load(it.imageUrl).into(ivLogoCoin)
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.toSymbol
                tvPrice.translationX = it.price.toFloat()
                tvMinPrice.text = it.lowDay.toString()
                tvMaxPrice.text = it.highDay.toString()
                tvLastMarket.text = it.lastMarket
                tvLastUpdate.text = it.lastUpdate
            }

        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL = ""
        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}