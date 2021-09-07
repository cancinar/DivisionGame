package com.cinar.divisiongame.common.event.payload;

import com.cinar.divisiongame.common.domain.Game;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameEvent implements Serializable {

  private Game game;
}
