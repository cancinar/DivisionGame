package com.cinar.divisiongame.playerone.game.core.manager;

import com.cinar.divisiongame.common.domain.Game;
import com.cinar.divisiongame.common.domain.enums.GameMode;

public interface PlayerManager {

  Game start(GameMode config);

  Game move(Integer action);

  void changeMode(GameMode mode);

  void start(Game game);
}
