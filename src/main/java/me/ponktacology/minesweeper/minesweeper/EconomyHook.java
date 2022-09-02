package me.ponktacology.minesweeper.minesweeper;

import org.bukkit.entity.Player;

public interface EconomyHook {

  void giveBalance(Player player, int balance);

  void removeBalance(Player player, int balance);

  int getBalance(Player player);
}
