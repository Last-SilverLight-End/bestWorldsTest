package com.example.bestwordstest

import android.text.Layout
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.regex.Pattern.quote

class QuotesPagerAdapter(
    private val quotes: List<Quote>,
    private val isNameRevealed: Boolean
) : RecyclerView.Adapter<QuotesPagerAdapter.QuoteViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        QuoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_quote,parent,false)
        )

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.init(quotes[position],isNameRevealed)
    }

    override fun getItemCount() = quotes.size

    class QuoteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val itemTextView: TextView = itemView.findViewById(R.id.quoteTextView)
        private val nameTextview: TextView = itemView.findViewById(R.id.nameTextView)

        fun init(quote: Quote, isNameRevealed: Boolean) {
            itemTextView.text = quote.quote
            if(isNameRevealed){

            }
        }
    }
}


