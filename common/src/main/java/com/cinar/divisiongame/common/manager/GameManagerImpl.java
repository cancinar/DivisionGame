package com.cinar.divisiongame.common.manager;


import com.cinar.divisiongame.common.domain.Game;
import com.cinar.divisiongame.common.domain.Move;
import com.cinar.divisiongame.common.domain.enums.GameMode;
import com.cinar.divisiongame.common.domain.enums.GameStatus;
import com.cinar.divisiongame.common.domain.enums.Player;
import com.cinar.divisiongame.common.exception.GameAlreadyStartedException;
import com.cinar.divisiongame.common.exception.NoGameFoundException;
import com.cinar.divisiongame.common.exception.NotYourTurnException;
import org.springframework.stereotype.Component;

@Component
class GameManagerImpl implements GameManager {

  private Game currentGame;
  protected static final String NO_GAME_FOUND = "No game is found!";

  public Game start(GameMode config, Player owner) {
    if (currentGame == null || currentGame.getGameStatus().equals(GameStatus.FINISHED)) {
      currentGame = new Game(config, owner);
      return currentGame;
    }

    throw new GameAlreadyStartedException("Game has already started!");
  }

  public Game move(Integer action, Player player) {
    if (currentGame != null && currentGame.getGameStatus().equals(GameStatus.STARTED)) {
      if (currentGame.getHasMove().equals(player)) {
        currentGame = currentGame.move(new Move(action), player);
        return currentGame;
      } else {
        throw new NotYourTurnException("Not your turn!");
      }
    } else {
      throw new NoGameFoundException(NO_GAME_FOUND);
    }
  }

  public Game changeMode(GameMode mode) {
    if (currentGame != null && currentGame.getGameStatus().equals(GameStatus.STARTED)) {
      currentGame.setGameMode(mode);
      return currentGame;
    } else {
      throw new NoGameFoundException(NO_GAME_FOUND);
    }
  }

  public void start(Game game) {
    currentGame = game;
  }

}
