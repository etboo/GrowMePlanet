package com.etb.growmyplanet.screens.game.views

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.etb.growmyplanet.screens.game.Layer
import com.etb.growmyplanet.screens.game.LayerHandler

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

    fun startFallingAnimation() {
        addAction(Actions.moveBy(0f, -1000f, 1f))
    }

    fun startAbsorbAnimation(center: Vector2) {

    }
}