package com.artpit.android.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artpit.android.cryptoapp.R
import com.artpit.android.cryptoapp.data.network.ApiFactory.BASE_IMAGE_URL
import com.artpit.android.cryptoapp.domain.CoinInfo
import com.artpit.android.cryptoapp.utils.convertTimestampToTime
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {
    var coinInfoList: List<CoinInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun getItemCount() = coinInfoList.size

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        with(holder) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
                tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol) //"${fromSymbol} / ${toSymbol}"
                tvPrice.text = price.toString()
                tvLastUpdate.text = String.format(lastUpdateTemplate, convertTimestampToTime(lastUpdate)) //"Last update: ${getFormattedTime()}"
                Picasso.get().load(BASE_IMAGE_URL + imageUrl).into(ivLogoCoin)

                itemView.setOnClickListener {
                    onCoinClickListener?.onCoinClick(this)
                }
            }
        }
    }

    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivLogoCoin = itemView.findViewById<ImageView>(R.id.ivLogoCoin)
        var tvSymbols = itemView.findViewById<TextView>(R.id.tvSymbols)
        var tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        var tvLastUpdate = itemView.findViewById<TextView>(R.id.tvLastUpdate)
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinInfo)
    }
}
