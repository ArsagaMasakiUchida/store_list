package com.example.task

import com.beust.klaxon.Klaxon
import com.beust.klaxon.Json

class RecruitGourmetSearchJson {

    private val klaxon = Klaxon()

    data class Welcome (
        val results: Results
    ) {
        public fun toJson() = Klaxon().toJsonString(this)

        companion object {
            public fun fromJson(json: String) = Klaxon().parse<Welcome>(json)
        }
    }

    data class Results (
        val resultsStart: Long,

        val resultsReturned: String,

        val apiVersion: String,

        val shop: List<Shop>,

        val resultsAvailable: Long
    )

    data class Shop (
        val nameKana: String,

        val otherMemo: String,

        val photo: Photo,

        val largeArea: LargeArea,

        val partyCapacity: String,

        val largeServiceArea: LargeArea,

        val mobileAccess: String,

        val id: String,
        val address: String,
        val lng: String,
        val course: String,
        val show: String,
        val parking: String,

        val nonSmoking: String,

        val horigotatsu: String,
        val name: String,
        val genre: Genre,
        val open: String,
        val card: String,
        val tatami: String,
        val charter: String,
        val wifi: String,

        val subGenre: LargeArea,

        val shopDetailMemo: String,

        val band: String,

        val middleArea: LargeArea,

        val lat: String,
        val karaoke: String,

        val logoImage: String,

        val midnight: String,
        val budget: Budget,
        val urls: Urls,
        val english: String,
        val lunch: String,

        val serviceArea: LargeArea,

        val close: String,

        val budgetMemo: String,

        val tv: String,

        val privateRoom: String,

        val couponUrls: CouponUrls,

        val barrierFree: String,

        val smallArea: LargeArea,

        val wedding: String,
        val access: String,
        val child: String,
        val capacity: String,
        val pet: String,

        val ktaiCoupon: String,

        val freeFood: String,

        val stationName: String,

        val catch: String,

        val freeDrink: String
    )

    data class Budget (
        val average: String,
        val name: String,
        val code: String
    )

    data class CouponUrls (
        val sp: String,
        val pc: String
    )

    data class Genre (
        val name: String,
        val catch: String,
        val code: String
    )

    data class LargeArea (
        val name: String,
        val code: String
    )

    data class Photo (
        val mobile: Mobile,
        val pc: PC
    )

    data class Mobile (
        val l: String,
        val s: String
    )

    data class PC (
        val l: String,
        val m: String,
        val s: String
    )

    data class Urls (
        val pc: String
    )

}