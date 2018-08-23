package com.yarrtest.balloon.screens.game

import com.badlogic.gdx.scenes.scene2d.InputListener
import com.yarrtest.balloon.base.ScreenLifecycleListener
import com.yarrtest.balloon.managers.LevelManager
import com.yarrtest.balloon.managers.ScoreManager
import com.yarrtest.balloon.screens.game.behaviors.BaseBehavior
import dagger.Module
import javax.inject.Inject

/**
 * Created by etb on 22.08.2018.
 */
@Module
class GameController: InputListener(), ScreenLifecycleListener {

    @Inject lateinit var levels: LevelManager

    @Inject lateinit var score: ScoreManager

    //TODO: inject via DI
    val behaviors = listOf<BaseBehavior<*, *>>()

    override fun onShow() {
        behaviors.forEach {
            it.start()
        }
    }

    override fun onPause() {
    }

    override fun onResume() {
    }

    override fun onHide() {
        behaviors.forEach {
            it.stop()
        }
    }

    override fun onDispose() {
    }

    fun update(delta: Float) {
        behaviors.forEach {
            it.act(delta)
        }
    }
}