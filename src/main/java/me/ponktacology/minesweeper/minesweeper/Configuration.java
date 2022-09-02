package me.ponktacology.minesweeper.minesweeper;

import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.file.FileConfiguration;

@RequiredArgsConstructor
public class Configuration {

  private final FileConfiguration config;

  public int getSize() {
    return config.getInt("mine-field.size", 27);
  }

  public double getMultiplierAddition() {
    return config.getDouble("multiplier.addition_per_cell", 0.2);
  }

  public double getInitialMultiplier() {
    return config.getDouble("multiplier.initial_value", 0.5);
  }


  public int getBombAmount() {
    return config.getInt("mine-field.bomb-amount", 27);
  }
}
