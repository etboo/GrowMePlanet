package com.etb.growmyplanet.screens.game

enum class Layer {
    BACKGROUND,
    BEHIND_PLAYER,
    PLAYER_LEVEL,
    IN_FRONT_OF_PLAYER,
    FOREGROUND;

    fun isGameObjectsLayer()
            = ordinal < FOREGROUND.ordinal && ordinal > BACKGROUND.ordinal

}

