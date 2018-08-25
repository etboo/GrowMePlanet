package com.yarrtest.balloon.screens.game.behaviors

import com.badlogic.gdx.Gdx
import com.yarrtest.balloon.screens.game.models.GameObjectModel
import com.yarrtest.balloon.screens.game.models.ModelObserver
import com.yarrtest.balloon.screens.game.views.BaseView

/**
 * Created by etb on 22.08.2018.
 */
private const val TAG = "com.yarrtest.ballon.base.BaseBehavior"

abstract class BaseBehavior<V: BaseView, out M: GameObjectModel>(
        protected val model: M
): ModelObserver {

    protected var view: V? = null

    open fun interceptPositionChanged(x: Float, y: Float): Boolean = false

    open fun interceptSizeChanged(width: Float, height: Float): Boolean = false

    open fun act(delta: Float) {
        Gdx.app.log(TAG, "act $delta")
    }

    open fun detachView() {
        Gdx.app.log(TAG, "stop")
        Gdx.app.log(TAG, "use default behavior, removing view to stage")
        view?.hide()
        this.view = null
    }

    open fun attachView(view: V) {
        Gdx.app.log(TAG, "start")
        Gdx.app.log(TAG, "use default behavior, adding view to stage")
        this.view = view
        model.observer = this
        view.show()
    }

    override fun positionChanged(x: Float, y: Float) {
        if(interceptPositionChanged(x, y).not()) {
            view?.setPosition(x, y)
        }
    }

    override fun sizeChanged(width: Float, height: Float) {
        if(interceptSizeChanged(width, height).not()) {
            view?.setSize(width, height)
        }
    }

    fun dispose() {
        detachView()
        model.observer = null
    }
}