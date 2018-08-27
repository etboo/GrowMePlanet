package com.etb.growmyplanet.screens.game.views.animation

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction
import com.etb.growmyplanet.screens.game.views.AnimationListener

/**
 * Created by etb on 26.08.2018.
 */
class HelixAnimation(
        private val circle: Circle,
        private val durationSec: Float
) {

    fun createAction(completion: AnimationListener): Action {

        val parallelAction = ParallelAction(
                CenteringAnimation(
                        circle,
                        durationSec
                ),
                SpinAnimation(
                        circle,
                        durationSec
                ),
                Actions.sizeTo(0f, 0f, durationSec)
        )

        return Actions.sequence(
                parallelAction,
                Actions.run { completion.onAnimationFinished() }
        )
    }

    private open class TimedAnimation(
            private val durationSec: Float
    ):Action() {

        private var elapsedTime = 0f

        override fun act(delta: Float): Boolean {
            elapsedTime += delta
            return elapsedTime >= durationSec
        }
    }

    private class CenteringAnimation(
            private val circle: Circle,
            private val durationSec: Float
    ) : TimedAnimation(durationSec) {

        private var velocity: Float = 0f

        override fun setActor(actor: Actor?) {
            super.setActor(actor)
            if(actor != null) {

                velocity = (circle.radius - actor.width * actor.scaleX / 2) /
                        durationSec
            }
        }

        override fun act(delta: Float): Boolean {
            val radiansAngle = getAngleFromActorCenterToCircleCenter(actor, circle.center)

            actor.setPosition(
                    actor.x - delta * velocity * MathUtils.cos(radiansAngle),
                    actor.y - delta * velocity * MathUtils.sin(radiansAngle)
            )

            return super.act(delta)

        }
    }

    private class SpinAnimation(
            private val circle: Circle,
            private val durationSec: Float
    ) : TimedAnimation(durationSec) {

        private var velocity: Float = 0f

        override fun setActor(actor: Actor?) {
            super.setActor(actor)
            if(actor != null) {
                velocity = (circle.radius - actor.width * actor.scaleX / 2) /
                        durationSec
            }
        }

        override fun act(delta: Float): Boolean {
            val radiansAngle = getAngleFromActorCenterToCircleCenter(actor, circle.center)

            actor.setPosition(
                    actor.x - delta * velocity * MathUtils.sin(radiansAngle),
                    actor.y - delta * velocity * MathUtils.cos(radiansAngle)
            )

            return super.act(delta)
        }
    }
}

private val Circle.center: Vector2
    get() = Vector2(x, y)

private fun getAngleFromActorCenterToCircleCenter(
        actor: Actor,
        circleCenter: Vector2
): Float {
    val actorCenter = Vector2(
            actor.x + (actor.width  * actor.scaleX) / 2,
            actor.y + (actor.height * actor.scaleY ) / 2
    )

    return MathUtils.atan2(
            actorCenter.y - circleCenter.y,
            actorCenter.x - circleCenter.x
    )
}

