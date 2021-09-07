package com.cinar.divisiongame.playertwo.game.core.listener;

import com.cinar.divisiongame.common.event.payload.GameEvent;
import com.cinar.divisiongame.playertwo.game.core.manager.PlayerManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class GameEventListener {

  private final PlayerManager playerManager;

  @RabbitListener(queues = "playeronegame.queue")
  public void gameChanged(GameEvent gameEvent) {
    log.info("Game event received!");
    playerManager.start(gameEvent.getGame());
  }
}
