package me.ponktacology.minesweeper.minesweeper.hook;

import me.ponktacology.minesweeper.minesweeper.EconomyHook;
import org.bukkit.entity.Player;

public class MockEconomyHook implements EconomyHook {

  @Override
  public void giveBalance(Player player, int balance) {
    System.out.println("Gave " + player.getName() + " " + balance);
  }

  @Override
  public void removeBalance(Player player, int balance) {
    System.out.println("Removed " + player.getName() + " " + balance);
  }

  @Override
  public int getBalance(Player player) {
    return 123;
  }
}
