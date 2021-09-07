package com.cinar.divisiongame.common.exception;

public class GameAlreadyStartedException extends RuntimeException {

  public GameAlreadyStartedException(String message) {
    super(message);
  }
}
