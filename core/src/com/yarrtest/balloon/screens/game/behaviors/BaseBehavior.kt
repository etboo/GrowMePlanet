package com.yarrtest.balloon.screens.game.behaviors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Shape2D
import com.yarrtest.balloon.screens.game.views.BaseView

/**
 * Created by etb on 22.08.2018.
 */
private const val TAG = "com.yarrtest.ballon.base.BaseBehavior"

abstract class BaseBehavior<M: Shape2D, out V: BaseView>(
        protected val collider: M,
        protected val view: V
) {

    init {
        updateViewPosition(collider)
    }

    abstract protected fun updateViewPosition(collider: M)

    open fun act(delta: Float) {
        Gdx.app.log(TAG, "act $delta")
    }

    open fun stop() {
        Gdx.app.log(TAG, "stop")
        Gdx.app.log(TAG, "use default behavior, removing view to stage")
        view.detach()
    }

    open fun start() {
        Gdx.app.log(TAG, "start")
        Gdx.app.log(TAG, "use default behavior, adding view to stage")
        view.attach()
    }

}