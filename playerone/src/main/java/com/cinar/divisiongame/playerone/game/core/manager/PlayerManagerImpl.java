package com.cinar.divisiongame.playerone.game.core.manager;

import com.cinar.divisiongame.common.domain.Game;
import com.cinar.divisiongame.common.domain.enums.GameMode;
import com.cinar.divisiongame.common.domain.enums.Player;
import com.cinar.divisiongame.common.event.payload.GameEvent;
import com.cinar.divisiongame.common.manager.GameManager;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PlayerManagerImpl implements PlayerManager {

  private final AmqpTemplate template;
  private final GameManager gameManager;

  public Game start(GameMode config) {
    final Game start = gameManager.start(config, Player.PLAYER_ONE);
    template.convertAndSend("playerOneExchange", "playeronegame", new GameEvent(start));
    return start;
  }

  public void start(Game game) {
    gameManager.start(game);
  }

  public Game move(Integer action) {
    final Game game = gameManager.move(action, Player.PLAYER_ONE);
    template.convertAndSend("playerTwoExchange", "playertwogame", new GameEvent(game));
    return game;
  }

  public void changeMode(GameMode mode) {
    final Game game = gameManager.changeMode(mode);
    template.convertAndSend("playerTwoExchange", "playertwogame", new GameEvent(game));
  }

}
