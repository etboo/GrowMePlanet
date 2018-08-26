package com.etb.growmyplanet

import com.etb.growmyplanet.base.AppConfig

/**
 * Created by etb on 21.08.2018.
 */
class AndroidConfig: AppConfig {

    override val isDebug = BuildConfig.DEBUG
    override val minDeltaTime = 0.033333335f

    override val camWidth = 720f
    override val camHeight = 1280f
}