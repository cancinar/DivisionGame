package com.cinar.divisiongame.common.domain.enums;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum GameMode implements Serializable {

  AUTO("auto"), MANUEL("manuel");

  private String mode;
}
