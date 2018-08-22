package com.yarrtest.balloon.screens.game.views

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage

/**
 * Created by etb on 22.08.2018.
 */
class Balloon(private val targetStage: Stage): Actor(), BaseView {

    override fun attach() {
        targetStage.addActor(this)
    }

    override fun detach() {
        remove()
    }
}