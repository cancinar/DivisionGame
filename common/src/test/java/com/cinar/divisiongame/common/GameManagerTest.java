package com.cinar.divisiongame.common;

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
public class GameManagerTest {

  private GameManager gameManager;

}
