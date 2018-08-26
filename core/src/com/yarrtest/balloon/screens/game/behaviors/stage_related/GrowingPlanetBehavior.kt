package com.yarrtest.balloon.screens.game.behaviors.stage_related

import com.yarrtest.balloon.UseCase
import com.yarrtest.balloon.screens.game.GLOBAL_TOUCHES_USE_CASE
import com.yarrtest.balloon.screens.game.GROWTH_TOUCH_UP_USE_CASE
import com.yarrtest.balloon.screens.game.ScreenTouchesListener
import com.yarrtest.balloon.screens.game.behaviors.PlanetBehavior
import com.yarrtest.balloon.screens.game.behaviors.stage_related.di.StageScope
import com.yarrtest.balloon.screens.game.models.PlanetModel
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by etb on 22.08.2018.
 */

private const val GROWTH_SPEED = 10f

@StageScope
class GrowingPlanetBehavior @Inject constructor(
        model: PlanetModel,
        @Named(GLOBAL_TOUCHES_USE_CASE)
        private val globalTouches: UseCase<@JvmWildcard ScreenTouchesListener?, @JvmWildcard Unit>,
        @Named(GROWTH_TOUCH_UP_USE_CASE)
        private val touchUpAction: UseCase<@JvmWildcard Unit, @JvmWildcard Unit>
): PlanetBehavior(
        model
), ScreenTouchesListener {

    private var active = false

    init {
        globalTouches.invoke(this)
    }

    override fun act(delta: Float) {
        if(active) {
            model.grow(delta * GROWTH_SPEED)
            model.moveBy(0f, 0f)
        }
    }

    override fun onTouchDown() {
        active = true
    }

    override fun onTouchUp() {
        active = false
        touchUpAction.invoke(Unit)
    }

    override fun dispose() {
        super.dispose()
        globalTouches.invoke(null)
    }
}