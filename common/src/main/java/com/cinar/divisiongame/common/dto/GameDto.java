package com.cinar.divisiongame.common.dto;

import com.cinar.divisiongame.common.domain.Game;
import com.cinar.divisiongame.common.domain.Move;
import com.cinar.divisiongame.common.domain.enums.GameStatus;
import com.cinar.divisiongame.common.domain.enums.Player;
import java.util.Optional;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GameDto {

  private Integer added;
  private int result;
  private GameStatus gameStatus;
  private Player winner;

  public static GameDto fromGame(Game game) {
    return GameDto.builder()
        .added(Optional.ofNullable(game.getLastMove())
            .map(Move::getAction)
            .orElse(null))
        .result(game.getResult())
        .gameStatus(game.getGameStatus())
        .winner(game.getWinner())
        .build();
  }
}
