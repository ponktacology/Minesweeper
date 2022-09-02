package me.ponktacology.minesweeper.minesweeper.game;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Game {

  private final Cell[] cells;
  private final int wager;
  private final GameSettings gameSettings;

  private boolean finished = false;
  private double multiplier;

  public Game(GameSettings gameSettings, int wager) {
    Preconditions.checkArgument(wager > 0, "wager must be > 0");
    this.gameSettings = gameSettings;
    this.cells = new Cell[gameSettings.getSize()];
    this.multiplier = gameSettings.getInitialMultiplier();
    this.wager = wager;

    createMap();
  }

  private void createMap() {
    putBombs();

    for (int slot = 0; slot < cells.length; slot++) {
      if (cells[slot] == null) {
        cells[slot] = new Cell(CellType.SAFE);
      }
    }
  }

  private void putBombs() {
    int bombsLeft = gameSettings.getBombAmount();

    while (bombsLeft-- > 0) {

      int slot;
      do {
        slot = ThreadLocalRandom.current().nextInt(0, cells.length);
      } while (cells[slot] != null);

      cells[slot] = new Cell(CellType.BOMB);
    }
  }

  public void checkCell(Cell clickedCell) {
    if (isFinished() || clickedCell.isChecked()) return;

    clickedCell.check();

    if (clickedCell.isBomb()) {
      multiplier = 0;
      finished = true;

      for (Cell cell : cells) {
        if (cell.isBomb()) cell.check();
      }

    } else multiplier += gameSettings.getMultiplierAddition();
  }

  public int getCashOut() {
    return (int) (wager * multiplier);
  }

  public double getMultiplier() {
    return BigDecimal.valueOf(multiplier).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
  }
}
