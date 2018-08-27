package com.etb.growmyplanet.screens.game.views

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.etb.growmyplanet.screens.game.Layer
import com.etb.growmyplanet.screens.game.LayerHandler
import com.etb.growmyplanet.screens.game.views.animation.HelixAnimation
import com.etb.growmyplanet.setCenterPosition

/**
 * Created by etb on 22.08.2018.
 */
class Planet(
        private val layerHandler: LayerHandler,
        texture: Texture
): Image(texture), BaseView {

    init {
        setOrigin(Align.center)
    }

    override fun isShown() = parent != null

    override fun show() {
        layerHandler.addActorOnLayer(this, Layer.PLAYER_LEVEL)
    }

    override fun hide() {
        remove()
    }

    override fun changePosition(x: Float, y: Float) {
        setCenterPosition(x, y)
    }

    override fun resize(newSize: Float) {
        val scaleKoef = newSize / width
        setScale(scaleKoef)

    }

    fun startFallingAnimation(listener: AnimationListener) {
        val actions = SequenceAction(
                Actions.moveBy(0f, - FALLING_DISTANCE, ANIMATION_DURATION),
                Actions.run { listener.onAnimationFinished() }
        )
        addAction(actions)
    }

    fun startAbsorbAnimation(target: Circle, listener: AnimationListener) {
        val animation = HelixAnimation(target, ANIMATION_DURATION)
        addAction(animation.createAction(listener))
    }

    fun startPassedAnimation(listener: AnimationListener) {

        val action = Actions.sequence(
                Actions.moveTo(x, parent.height, 0.6f),
                Actions.run { listener.onAnimationFinished() }
        )
        addAction(action)
    }
}

private const val FALLING_DISTANCE = 1000f
private const val ANIMATION_DURATION = 1f