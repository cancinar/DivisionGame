package com.cinar.divisiongame.common.manager;

import com.cinar.divisiongame.common.domain.Game;
import com.cinar.divisiongame.common.domain.enums.GameMode;
import com.cinar.divisiongame.common.domain.enums.Player;

public interface GameManager {

  Game start(GameMode config, Player owner);

  Game move(Integer action, Player player);

  Game changeMode(GameMode mode);

  void start(Game game);
}
