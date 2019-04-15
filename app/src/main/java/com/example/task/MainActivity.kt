package com.example.task

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.widget.Button
import android.widget.TextView
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpGet

import kotlinx.coroutines.experimental.*

import com.beust.klaxon.*
import com.beust.klaxon.JsonObject
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken


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
        search_button.setOnClickListener{
            val search_text = findViewById(R.id.searchText) as TextView
            renderList(search_text.text.toString())
        }
    }

    private fun renderList(strSearchText: String){
        var result_list = runBlocking{ search(Type.storeName, strSearchText) }

        setContentView(R.layout.list_shop)
        var tmp_text = findViewById(R.id.textView0) as TextView
        tmp_text.setText(result_list)


        val home_button = findViewById(R.id.homeButton) as Button
        home_button.setOnClickListener{ renderMain() }
    }



    private suspend fun search(type: Type, input: String): Response {

        var uri = when (type) {
            Type.storeName -> "${base_uri}name_any=${input}"
            Type.stationName -> "${base_uri}"
            Type.Distance -> "${base_uri}"
        }
        var result = async{
            val res = uri.httpGet().response().second
            val str_res_body = String(res.data)
            val gson = GsonBuilder().setPrettyPrinting().create()
            val res_body: Map<String, Any> = gson.fromJson(str_res_body, object : TypeToken<Map<String, Any>>() {}.type)
            res
        }

        return result.await()

    }


}

enum class Type {
    storeName, stationName, Distance;
}
