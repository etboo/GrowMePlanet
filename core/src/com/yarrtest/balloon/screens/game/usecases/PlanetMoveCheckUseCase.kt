package com.yarrtest.balloon.screens.game.usecases

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.math.Polyline
import com.badlogic.gdx.math.Vector2
import com.yarrtest.balloon.UseCase

class PlanetMoveCheckUseCase(
        private val ringCollidersProvider: () -> Polyline
) : UseCase<Circle, Boolean> {
    override fun invoke(circle: Circle): Boolean {
        ringCollidersProvider.invoke().let {
            val isIntersected = Intersector.intersectSegmentCircle(
                    it.start,
                    it.end,
                    circle.center,
                    circle.radius
            )

            if (isIntersected) {
                Gdx.app.log("@", "scored, x: ${circle.x} y: ${circle.y}")
                return false
            }
        }

        return true
    }

}

private val Polyline.start: Vector2
    get() = Vector2(this.vertices[0], this.vertices[1])


private val Polyline.end: Vector2
    get() = Vector2(this.vertices[2], this.vertices[3])


private val Circle.center: Vector2
    get() = Vector2(this.x, this.y)


