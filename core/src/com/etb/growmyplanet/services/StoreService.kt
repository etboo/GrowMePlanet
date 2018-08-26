package com.etb.growmyplanet.services

/**
 * Created by etb on 21.08.2018.
 */
interface StoreService {

    fun putString(key: String, value: String)

    fun getString(key: String, defaultValue: String): String
}