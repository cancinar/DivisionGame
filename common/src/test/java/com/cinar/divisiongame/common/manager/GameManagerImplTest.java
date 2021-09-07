package com.cinar.divisiongame.common.manager;

import static com.cinar.divisiongame.common.generator.GameGenerator.randomFinishedGame;
import static com.cinar.divisiongame.common.generator.RandomGenerator.randomEnum;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cinar.divisiongame.common.domain.Game;
import com.cinar.divisiongame.common.domain.enums.GameMode;
import com.cinar.divisiongame.common.domain.enums.GameStatus;
import com.cinar.divisiongame.common.domain.enums.Player;
import com.cinar.divisiongame.common.exception.ActionRequiredException;
import com.cinar.divisiongame.common.exception.GameAlreadyStartedException;
import com.cinar.divisiongame.common.exception.NoGameFoundException;
import com.cinar.divisiongame.common.exception.NotYourTurnException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameManagerImplTest {

  private GameManager gameManager;

  @BeforeEach
  public void init() {
    gameManager = new GameManagerImpl();
  }

  @Test
  public void startManuelGame_whenNoGameStarted() {
    final Game game = gameManager.start(GameMode.MANUEL, randomEnum(Player.class));

    assertEquals(GameMode.MANUEL, game.getGameMode());
    assertEquals(GameStatus.STARTED, game.getGameStatus());
    assertEquals(GameStatus.STARTED, game.getGameStatus());
  }

  @Test
  public void startManuelGame_whenGameStarted_thenThrowException() {
    gameManager.start(GameMode.MANUEL, randomEnum(Player.class));
    assertThrows(GameAlreadyStartedException.class,
        () -> gameManager.start(GameMode.MANUEL, randomEnum(Player.class)));
  }

  @Test
  public void startManuelGame_whenFinishedGameExist() {
    gameManager.start(randomFinishedGame());
    final Game game = gameManager.start(GameMode.MANUEL, randomEnum(Player.class));

    assertEquals(GameMode.MANUEL, game.getGameMode());
    assertEquals(GameStatus.STARTED, game.getGameStatus());
    assertEquals(GameStatus.STARTED, game.getGameStatus());
  }

  @Test
  public void moveManuelGame_whenHasAction() {
    gameManager.start(GameMode.MANUEL, Player.PLAYER_ONE);
    final Game game = gameManager.move(-1, Player.PLAYER_TWO);

    assertEquals(GameMode.MANUEL, game.getGameMode());
    assertEquals(GameStatus.STARTED, game.getGameStatus());
    assertEquals(GameStatus.STARTED, game.getGameStatus());
    assertEquals(Player.PLAYER_ONE, game.getHasMove());
  }

  @Test
  public void moveAutoGame_whenHasNoAction() {
    gameManager.start(GameMode.AUTO, Player.PLAYER_ONE);
    final Game game = gameManager.move(null, Player.PLAYER_TWO);

    assertEquals(GameMode.AUTO, game.getGameMode());
    assertEquals(GameStatus.STARTED, game.getGameStatus());
    assertEquals(GameStatus.STARTED, game.getGameStatus());
    assertEquals(Player.PLAYER_ONE, game.getHasMove());
  }

  @Test
  public void moveManuelGame_whenNoAction_thenThrowException() {
    gameManager.start(GameMode.MANUEL, Player.PLAYER_ONE);
    assertThrows(ActionRequiredException.class,
        () -> gameManager.move(null, Player.PLAYER_TWO));
  }

  @Test
  public void moveManuelGame_whenNoTurn_thenThrowException() {
    gameManager.start(GameMode.MANUEL, Player.PLAYER_ONE);
    assertThrows(NotYourTurnException.class,
        () -> gameManager.move(null, Player.PLAYER_ONE));
  }

  @Test
  public void moveGame_whenNoGameExist_thenThrowException() {
    assertThrows(NoGameFoundException.class,
        () -> gameManager.move(null, Player.PLAYER_ONE));
  }

  @Test
  public void moveGame_whenGameFinished_thenThrowException() {
    gameManager.start(randomFinishedGame());
    assertThrows(NoGameFoundException.class,
        () -> gameManager.move(null, Player.PLAYER_ONE));
  }

  @Test
  public void changeGameMode_whenNoGameExist_thenThrowException() {
    assertThrows(NoGameFoundException.class,
        () -> gameManager.changeMode(GameMode.MANUEL));
  }


  @Test
  public void changeGameMode() {
    gameManager.start(GameMode.AUTO, Player.PLAYER_ONE);
    final Game game = gameManager.changeMode(GameMode.MANUEL);

    assertEquals(GameMode.MANUEL, game.getGameMode());
  }
}
