package com.etb.growmyplanet.services

/**
 * Created by etb on 21.08.2018.
 */
class EmptyStoreServiceImpl: StoreService {
    override fun putInt(key: String, value: Int) {
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return defaultValue
    }

    override fun putString(key: String, value: String) {
    }

    override fun getString(key: String, defaultValue: String): String {
        return ""
    }

}