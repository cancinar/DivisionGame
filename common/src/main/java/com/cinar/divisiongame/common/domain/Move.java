package com.cinar.divisiongame.common.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Move implements Serializable {

  private final static List<Integer> acceptedAction = Arrays.asList(1, 0, -1);
  private Integer action;

  public Move(Integer action) {
    setAction(action);
  }

  public Integer getAction() {
    return action;
  }

  public void setAction(Integer action) {
    if (action == null || acceptedAction.stream().anyMatch(action::equals)) {
      this.action = action;
    } else {
      throw new RuntimeException("Action cannot accepted!");
    }
  }
}
