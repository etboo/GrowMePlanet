package com.etb.growmyplanet.screens.game.behaviors.phase_related

/**
 * Created by etb on 25.08.2018.
 */

sealed class Phase

class GrowingPhase: Phase()

class FloatingPhase: Phase()

class LevelFailed(
        val failedReason: FailedReason
): Phase()

class LevelPassed: Phase()