package me.ponktacology.minesweeper.minesweeper.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.ponktacology.minesweeper.minesweeper.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class Cell {

  private final CellType type;
  @Getter private boolean checked;

  public ItemStack getIcon() {
    if (!checked)
      return new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE)
          .setDisplayName(ChatColor.GRAY + ",")
          .build();

    return type.getIcon();
  }

  public boolean isBomb() {
    return type == CellType.BOMB;
  }

  public void check() {
    this.checked = true;
  }
}
