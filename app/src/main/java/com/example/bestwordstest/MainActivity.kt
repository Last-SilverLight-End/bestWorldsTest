package com.example.bestwordstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val viewPager: ViewPager2 by lazy {
        findViewById(R.id.viewPager2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initData()

    }

    private fun initViews() {

    }
    private fun initData(){
        val remoteDataConfig = Firebase.remoteConfig
        remoteDataConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0

            }
        )
        remoteDataConfig.fetchAndActivate().addOnCompleteListener {
            if(it.isSuccessful){
                val quotesBring = quotesBringJson(remoteDataConfig.getString("quotes"))
                val isNameRevealed = remoteDataConfig.getBoolean("is_name_revealed")
                displayQuotesPager(quotesBring,isNameRevealed)
                viewPager.adapter = QuotesPagerAdapter(
                    listOf(

                    )

                )
            }
        }
    }
    private fun quotesBringJson(json: String) : List<Quote>{
        val jsonArray = JSONArray(json)
        var jsonList = emptyArray<JSONObject>()
        for(index in 0 until jsonArray.length()){
            val jsonObject = jsonArray.getJSONObject(index)
            jsonObject?.let{
                jsonList  = jsonList+it
            }
        }

        return jsonList.map {
            Quote(
                quote =it.getString("quote"),
                name =it.getString("name"))
        }
    }

    private fun displayQuotesPager(quotes:List<Quote>,isNameRevealed : Boolean){
        viewPager.adapter = QuotesPagerAdapter(
            quotes
        )
    }
}