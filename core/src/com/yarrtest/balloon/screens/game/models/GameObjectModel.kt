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
        private set

    var y: Float = y
        private set

    var radius: Float = radius
        private set(value) {
            field = value
            observer?.sizeChanged(value * 2, value * 2)
        }

    var observer: ModelObserver? = null
        set(value) {
            field = value
            value?.positionChanged(x, y)
            value?.sizeChanged(radius * 2, radius * 2)
        }

    fun moveBy(diffX: Float = 0f, diffY: Float = 0f) {
        x += diffX
        y += diffY
        observer?.positionChanged(x, y)
    }
}

class PlanetModel(
        x: Float,
        y: Float,
        radius: Float
) : GameObjectModel(x, y, radius)

class RingModel(
        x: Float,
        y: Float,
        radius: Float
) : GameObjectModel(x, y, radius)

class BlackHoleModel(
        x: Float,
        y: Float,
        radius: Float
) : GameObjectModel(x, y, radius)

