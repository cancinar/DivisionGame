package com.cinar.divisiongame.playertwo.game.view.controller;


import com.cinar.divisiongame.common.domain.Game;
import com.cinar.divisiongame.common.domain.enums.GameMode;
import com.cinar.divisiongame.common.dto.GameDto;
import com.cinar.divisiongame.playertwo.game.core.manager.PlayerManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player-two")
@AllArgsConstructor
public class PlayerTwoController {

  private final PlayerManager playerManager;

  @PostMapping("/start")
  public ResponseEntity<GameDto> startGame(@RequestParam("mode") GameMode gameMode) {
    final Game game = playerManager.start(gameMode);
    return ResponseEntity.ok(GameDto.fromGame(game));
  }

  @PostMapping("/move")
  public ResponseEntity<GameDto> move(
      @RequestParam(name = "added", required = false) Integer added) {
    final Game game = playerManager.move(added);
    return ResponseEntity.ok(GameDto.fromGame(game));
  }

  @PutMapping("/game")
  public ResponseEntity<GameDto> move(
      @RequestParam(name = "mode") GameMode gameMode) {
    playerManager.changeMode(gameMode);
    return ResponseEntity.ok().build();
  }

}
