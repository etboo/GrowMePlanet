package com.yarrtest.balloon.base

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.Screen
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.utils.viewport.Viewport

/**
 * Created by etb on 21.08.2018.
 */
abstract class BaseScreen<out T : BaseGame, G : Viewport>(
        game: T, private var viewport: G
) : Screen {

    private var firstResume: Boolean = false
    protected var stage: BaseStage<G>? = null
    private val inputListeners = mutableSetOf<InputListener>()

    val game: BaseGame = game
    val inputProcessor: InputMultiplexer
    var lifecycleListener: ScreenLifecycleListener? = null

    init {
        this.firstResume = true
        this.inputProcessor = InputMultiplexer()
    }

    override fun resume() {
        if (this.firstResume) {
            this.firstResume = false
            stage = BaseStage(viewport)
            this.onFirstResume()
        }

        this.stage?.addListener(object : InputListener() {

            override fun keyDown(event: InputEvent?, keycode: Int): Boolean {
                if (4 != keycode && 67 != keycode) {
                    if (82 == keycode && this@BaseScreen.menuPressed()) {
                        return true
                    }
                } else if (this@BaseScreen.backPressed()) {
                    return true
                }

                return false
            }
        })
        inputListeners.forEach {
            this.stage?.addListener(it)
        }
        this.inputProcessor.addProcessor(this.stage!!)
        this.lifecycleListener?.onResume()

    }

    protected fun onFirstResume() {}

    override fun resize(width: Int, height: Int) {
        this.stage?.viewport?.update(width, height, true)
    }

    override fun show() {
        stage?.let {
            this.prepareStage(it)
        }

        this.onShow()
    }

    protected abstract fun prepareStage(stage: Stage)

    protected fun onFirstShow() {}

    fun onShow() {
        Gdx.input.inputProcessor = inputProcessor
        this.stage?.root?.touchable = Touchable.enabled
        this.lifecycleListener?.onShow()

    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(16384)
        this.stage?.act(delta)
        this.stage?.draw()
    }

    override fun hide() {
        this.lifecycleListener?.onHide()
    }

    override fun pause() {
        this.stage?.clear()

        this.inputProcessor.clear()
        this.lifecycleListener?.onPause()
    }

    fun addInputListener(listener: InputListener) {
        inputListeners.add(listener)
        stage?.addListener(listener)
    }

    override fun dispose() {
        this.stage?.dispose()

        this.lifecycleListener?.onDispose()
    }

    protected open fun backPressed(): Boolean {
        return false
    }

    protected open fun menuPressed(): Boolean {
        return false
    }

}