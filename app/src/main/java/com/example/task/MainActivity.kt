package com.example.task

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpGet

import kotlinx.coroutines.experimental.*

import com.beust.klaxon.*
import com.beust.klaxon.JsonObject
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_shop.*

class MainActivity : AppCompatActivity() {

    val base_uri = "https://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key=bac3d201e89240dc&count=6&format=json&"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        renderMain()
    }

    private fun renderMain(){
        setContentView(R.layout.activity_main)
        val search_button = findViewById(R.id.searchButton) as Button
        search_button.setOnClickListener {
            val strSearchText = searchText.text.toString()
            renderList(strSearchText)

        }
    }

    private fun renderList(strSearchText: String){
        val type = Type.storeName
        var uri = when (type) {
            Type.storeName -> "${base_uri}name_any=${strSearchText}"
            Type.stationName -> "${base_uri}"
            Type.Distance -> "${base_uri}"
        }
        setContentView(R.layout.list_shop)
        val resourceIds = Class.forName(getPackageName() + ".R\$id")

        val result =   Client
            .retrofitApi
            .getGourmeSearchJson("json", "bac3d201e89240dc", 6, strSearchText)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe { welcom ->
                Log.v("ろぐ", "${welcom.results.shop[0].address}")
                welcom.results.shop.fold(0) {idx, shopInfo ->
                    val resourceId = resourceIds.getField("textView${idx}").get(resourceIds) as Int
                    val tmpTextView = findViewById<TextView>(resourceId)
                    tmpTextView.text = shopInfo.name
                    idx + 1
                }
            }

        val home_button = findViewById(R.id.homeButton) as Button
        home_button.setOnClickListener{ renderMain() }
    }



//    private suspend fun search(type: Type, input: String): Disposable {
//
//        var uri = when (type) {
//            Type.storeName -> "${base_uri}name_any=${input}"
//            Type.stationName -> "${base_uri}"
//            Type.Distance -> "${base_uri}"
//        }
//
//        val result =   Client
//            .retrofitApi
//            .getGourmeSearchJson("json", "bac3d201e89240dc", 6, input)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread()).subscribe { welcom ->
//                Log.v("ろぐ", "${welcom.results.shop[0].address}")
//                welcom.results.shop
//            }
//
//        return result
//
//    }


}

enum class Type {
    storeName, stationName, Distance;
}
