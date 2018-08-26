package com.etb.growmyplanet.base

/**
 * Created by etb on 21.08.2018.
 */
interface ScreenLifecycleListener {

    fun onShow()

    fun onPause()

    fun onResume()

    fun onHide()

    fun onDispose()
}