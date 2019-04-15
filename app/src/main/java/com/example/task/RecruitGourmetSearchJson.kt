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
        @Json(name = "results_start")
        val resultsStart: Long,

        @Json(name = "results_returned")
        val resultsReturned: String,

        @Json(name = "api_version")
        val apiVersion: String,

        val shop: List<Shop>,

        @Json(name = "results_available")
        val resultsAvailable: Long
    )

    data class Shop (
        @Json(name = "name_kana")
        val nameKana: String,

        @Json(name = "other_memo")
        val otherMemo: String,

        val photo: Photo,

        @Json(name = "large_area")
        val largeArea: LargeArea,

        @Json(name = "party_capacity")
        val partyCapacity: String,

        @Json(name = "large_service_area")
        val largeServiceArea: LargeArea,

        @Json(name = "mobile_access")
        val mobileAccess: String,

        val id: String,
        val address: String,
        val lng: String,
        val course: String,
        val show: String,
        val parking: String,

        @Json(name = "non_smoking")
        val nonSmoking: String,

        val horigotatsu: String,
        val name: String,
        val genre: Genre,
        val open: String,
        val card: String,
        val tatami: String,
        val charter: String,
        val wifi: String,

        @Json(name = "sub_genre")
        val subGenre: LargeArea,

        @Json(name = "shop_detail_memo")
        val shopDetailMemo: String,

        val band: String,

        @Json(name = "middle_area")
        val middleArea: LargeArea,

        val lat: String,
        val karaoke: String,

        @Json(name = "logo_image")
        val logoImage: String,

        val midnight: String,
        val budget: Budget,
        val urls: Urls,
        val english: String,
        val lunch: String,

        @Json(name = "service_area")
        val serviceArea: LargeArea,

        val close: String,

        @Json(name = "budget_memo")
        val budgetMemo: String,

        val tv: String,

        @Json(name = "private_room")
        val privateRoom: String,

        @Json(name = "coupon_urls")
        val couponUrls: CouponUrls,

        @Json(name = "barrier_free")
        val barrierFree: String,

        @Json(name = "small_area")
        val smallArea: LargeArea,

        val wedding: String,
        val access: String,
        val child: String,
        val capacity: String,
        val pet: String,

        @Json(name = "ktai_coupon")
        val ktaiCoupon: String,

        @Json(name = "free_food")
        val freeFood: String,

        @Json(name = "station_name")
        val stationName: String,

        val catch: String,

        @Json(name = "free_drink")
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