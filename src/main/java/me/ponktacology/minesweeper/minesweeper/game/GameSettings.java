package me.ponktacology.minesweeper.minesweeper.game;

import lombok.Getter;
import me.ponktacology.minesweeper.minesweeper.Configuration;

@Getter
public class GameSettings {

  private final int size;
  private final int bombAmount;
  private final double multiplierAddition;
  private final double initialMultiplier;

  public GameSettings(Configuration configuration) {
    this.size = configuration.getSize();
    this.bombAmount = configuration.getBombAmount();
    this.multiplierAddition = configuration.getMultiplierAddition();
    this.initialMultiplier = configuration.getInitialMultiplier();
  }
}
