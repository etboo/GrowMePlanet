package com.yarrtest.balloon.screens.game.models

/**
 * Created by etb on 22.08.2018.
 */
sealed class GameObjectModel(
        x: Float,
        y: Float,
        radius: Float
) {

    var x: Float = x
        protected set

    var y: Float = y
        protected set

    var radius: Float = radius
        protected set(value) {
            field = value
            observer?.radiusChanged(value)
        }

    var observer: ModelObserver? = null
}

class PlanetModel(
        x: Float,
        y: Float,
        radius: Float
) : GameObjectModel(x, y, radius) {

    fun moveBy(diffX: Float = 0f, diffY: Float = 0f) {
        x += diffX
        y += diffY
        observer?.positionChanged(x, y)
    }

    fun grow(diff: Float) {
        radius += diff
    }
}

sealed class ObstacleModel(
        x: Float,
        y: Float,
        radius: Float,
        val score: Int
) : GameObjectModel(x, y, radius)

class RingModel(
        x: Float,
        y: Float,
        radius: Float,
        score: Int
) : ObstacleModel(x, y, radius, score)

class BlackHoleModel(
        x: Float,
        y: Float,
        radius: Float,
        score: Int
) : ObstacleModel(x, y, radius, score)

