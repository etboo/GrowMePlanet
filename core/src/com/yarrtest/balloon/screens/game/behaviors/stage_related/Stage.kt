package com.yarrtest.balloon.screens.game.behaviors.stage_related

/**
 * Created by etb on 25.08.2018.
 */

sealed class Stage

class GrowingStage: Stage()

class FloatingStage(): Stage()

class LevelFailed(
        val failedReason: FailedReason
): Stage()

class LevelPassed: Stage()