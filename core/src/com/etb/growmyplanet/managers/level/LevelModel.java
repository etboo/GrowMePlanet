package com.etb.growmyplanet.managers.level;

import com.etb.growmyplanet.screens.game.di.LevelScope;
import com.etb.growmyplanet.screens.game.models.BlackHoleModel;
import com.etb.growmyplanet.screens.game.models.PlanetModel;
import com.etb.growmyplanet.screens.game.models.RingModel;
import com.google.gson.annotations.SerializedName;

import dagger.Module;
import dagger.Provides;

/**
 * Created by etb on 28.08.2018.
 */
@Module
public class LevelModel {

    private static final Float START_PLAYER_RADIUS = 30f;
    private static final Float START_PLAYER_Y = 200f;
    private static final Float START_PLAYER_X = 360f;

    @SerializedName("ring")
    private RingModel ring;

    @SerializedName("black_hole")
    private BlackHoleModel blackHole;

    private transient PlanetModel player;

    LevelModel() {
        player = new PlanetModel(
            START_PLAYER_X,
                START_PLAYER_Y,
                START_PLAYER_RADIUS
        );
    }

    @LevelScope
    @Provides
    public PlanetModel providePlayerModel() {
        return player;
    }

    @LevelScope
    @Provides
    public RingModel provideRingModel() {
        return ring;
    }

    @LevelScope
    @Provides
    public BlackHoleModel provideBlackHoleModel() {
        return blackHole;
    }

}
