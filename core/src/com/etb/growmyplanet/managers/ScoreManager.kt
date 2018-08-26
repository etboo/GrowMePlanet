package com.etb.growmyplanet.managers

import com.etb.growmyplanet.services.StoreService

/**
 * Created by etb on 22.08.2018.
 */
class ScoreManager(store: StoreService) {
    var score: Long = 0
    private set

    fun scored(value: Int) {
        score += value
    }
}