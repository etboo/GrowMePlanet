package com.etb.growmyplanet.screens.game.behaviors

import com.badlogic.gdx.Gdx
import com.etb.growmyplanet.screens.game.models.GameObjectModel
import com.etb.growmyplanet.screens.game.models.ModelObserver
import com.etb.growmyplanet.screens.game.views.BaseView

/**
 * Created by etb on 22.08.2018.
 */
private const val TAG = "com.etb.growmyplanet.base.BaseBehavior"

abstract class BaseBehavior<V: BaseView, out M: GameObjectModel>(
        behaviorModel: M
): ModelObserver {

    protected var view: V? = null

    protected val model by lazy {
        behaviorModel.observer = this
        behaviorModel
    }

    open fun validatePositionChanged(x: Float, y: Float): Boolean = true

    open fun validateSizeChanged(value: Float): Boolean = true

    open fun act(delta: Float) {
    }

    open fun detachView() {
        this.view = null
    }

    open fun attachView(view: V) {
        Gdx.app.log(TAG, "attachView $view")
        this.view = view
        setInitialViewProperties()

        if(!view.isShown()) {
            view.show()
        }
    }

    override fun positionChanged(x: Float, y: Float) {
        if(validatePositionChanged(x, y)) {
            view?.changePosition(x, y)
        }
    }

    override fun radiusChanged(value: Float) {
        if(validateSizeChanged(value)) {
            view?.resize(value * 2)
        }
    }

    protected open fun setInitialViewProperties() {
        view?.changePosition(model.x, model.y)
        view?.resize(model.radius * 2)
    }

    open fun dispose() {
        model.observer = null
    }
}