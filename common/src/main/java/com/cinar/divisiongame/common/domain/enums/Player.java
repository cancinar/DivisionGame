package com.cinar.divisiongame.common.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Player {

  PLAYER_ONE, PLAYER_TWO;

  public static Player findNextPlayer(Player player) {
    return player.equals(PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
  }
}
