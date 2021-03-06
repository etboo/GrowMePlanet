package com.etb.growmyplanet.screens.game.views.animation

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
import com.etb.growmyplanet.screens.game.views.AnimationListener
import com.etb.growmyplanet.setCenterPosition

/**
 * Created by etb on 26.08.2018.
 */
private const val ANIMATION_ANGLE = 480 * MathUtils.degreesToRadians

class HelixAnimation(
        private val circle: Circle,
        private val durationSec: Float
) {

    fun createAction(completion: AnimationListener): Action {

        val parallelAction = ParallelAction(
                SpinAnimation(
                        circle,
                        ANIMATION_ANGLE,
                        durationSec
                ),
                Actions.scaleTo(0.01f, 0.01f, durationSec)
        )

        return Actions.sequence(
                parallelAction,
                Actions.run { completion.onAnimationFinished() }
        )
    }

    private class SpinAnimation(
            private val circle: Circle,
            private val angle: Float,
            durationSec: Float
    ) : TemporalAction(durationSec) {

        private var distance = circle.radius

        override fun setActor(actor: Actor?) {
            super.setActor(actor)
            actor?.let {
                distance = circle.radius - actor.width * actor.scaleX / 2
            }
        }

        override fun update(percent: Float) {
            val fadingKoef = 1 - percent

            val x = circle.x - MathUtils.sin(angle * percent) * distance * fadingKoef
            val y =  circle.y - MathUtils.cos(angle * percent) * distance * fadingKoef
            Gdx.app.log("@", "x= $x, y= $y")
            actor.setCenterPosition(x,y)
        }
    }

}

