package com.cinar.divisiongame.common.domain;

import static com.cinar.divisiongame.common.domain.enums.Player.findNextPlayer;

import com.cinar.divisiongame.common.domain.enums.GameMode;
import com.cinar.divisiongame.common.domain.enums.GameStatus;
import com.cinar.divisiongame.common.domain.enums.Player;
import com.cinar.divisiongame.common.exception.ActionRequiredException;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Game implements Serializable {

  private int initialNumber;
  private Player hasMove;
  private int result;
  private GameMode gameMode;
  private Move lastMove;
  private GameStatus gameStatus;
  private Player winner;

  public Game(GameMode gameMode, Player owner) {
    this.gameMode = gameMode;
    this.hasMove = findNextPlayer(owner);
    this.initialNumber = (int) ((Math.random() * (100 - 10)) + 10);
    this.result = initialNumber;
    this.gameStatus = GameStatus.STARTED;
  }

  public Game move(Move move, Player player) {
    if (getGameMode().equals(GameMode.AUTO)) {
      move.setAction(calculateAutoMove());
    } else {
      if (move.getAction() == null) {
        throw new ActionRequiredException("Action cannot be null when game mode is manuel.");
      }
    }

    this.result = (this.result + (move.getAction())) / 3;

    if (result == 1) {
      gameStatus = GameStatus.FINISHED;
      winner = this.hasMove;
    }

    this.hasMove = findNextPlayer(player);
    this.lastMove = move;
    return this;
  }

  private int calculateAutoMove() {
    if (result % 3 == 0) {
      return 0;
    } else if ((result - 1) % 3 == 0) {
      return -1;
    } else {
      return 1;
    }
  }

  public GameStatus getGameStatus() {
    return gameStatus;
  }

  public void setGameStatus(GameStatus gameStatus) {
    this.gameStatus = gameStatus;
  }

  public Player getWinner() {
    return winner;
  }

  public void setWinner(Player winner) {
    this.winner = winner;
  }

  public Move getLastMove() {
    return lastMove;
  }

  public void setLastMove(Move lastMove) {
    this.lastMove = lastMove;
  }

  public int getInitialNumber() {
    return initialNumber;
  }

  public Player getHasMove() {
    return hasMove;
  }

  public void setHasMove(Player hasMove) {
    this.hasMove = hasMove;
  }

  public int getResult() {
    return result;
  }

  public void setResult(int result) {
    this.result = result;
  }

  public GameMode getGameMode() {
    return gameMode;
  }

  public void setGameMode(GameMode gameMode) {
    this.gameMode = gameMode;
  }

}
