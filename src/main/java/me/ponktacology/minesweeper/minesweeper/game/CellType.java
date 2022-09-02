package me.ponktacology.minesweeper.minesweeper.game;

import lombok.RequiredArgsConstructor;
import me.ponktacology.minesweeper.minesweeper.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public enum CellType {
  SAFE(
      new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE)
          .setDisplayName(ChatColor.GREEN + "Safe")
          .build()),
  BOMB(new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
          .setDisplayName(ChatColor.RED + "Bomb")
          .build());

  private final ItemStack icon;

  public ItemStack getIcon() {
    return icon;
  }
}
