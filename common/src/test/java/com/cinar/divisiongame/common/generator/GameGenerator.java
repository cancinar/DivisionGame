package com.cinar.divisiongame.common.generator;

import static com.cinar.divisiongame.common.generator.RandomGenerator.randomEnum;
import static com.cinar.divisiongame.common.generator.RandomGenerator.randomInt;

import com.cinar.divisiongame.common.domain.Game;
import com.cinar.divisiongame.common.domain.enums.GameMode;
import com.cinar.divisiongame.common.domain.enums.GameStatus;
import com.cinar.divisiongame.common.domain.enums.Player;

public class GameGenerator {

  public static Game randomFinishedGame() {
    return Game.builder()
        .gameMode(randomEnum(GameMode.class))
        .initialNumber(randomInt())
        .result(1)
        .winner(randomEnum(Player.class))
        .gameStatus(GameStatus.FINISHED)
        .build();
  }
}
