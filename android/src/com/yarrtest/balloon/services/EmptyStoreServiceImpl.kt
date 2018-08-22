package com.yarrtest.balloon.services

/**
 * Created by etb on 21.08.2018.
 */
class EmptyStoreServiceImpl: StoreService {
    override fun putString(key: String, value: String) {
    }

    override fun getString(key: String, defaultValue: String): String {
        return ""
    }

}