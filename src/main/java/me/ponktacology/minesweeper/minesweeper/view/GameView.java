package me.ponktacology.minesweeper.minesweeper.view;

import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import me.ponktacology.minesweeper.minesweeper.Minesweeper;
import me.ponktacology.minesweeper.minesweeper.game.Cell;
import me.ponktacology.minesweeper.minesweeper.game.Game;
import me.ponktacology.minesweeper.minesweeper.util.menu.Button;
import me.ponktacology.minesweeper.minesweeper.util.menu.Menu;
import me.ponktacology.minesweeper.minesweeper.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

@RequiredArgsConstructor
public class GameView extends Menu {

  private final Game game;

  @Override
  public String getTitle(Player player) {
    return "Minesweeper";
  }

  @Override
  public Map<Integer, Button> getButtons(Player player) {
    Map<Integer, Button> buttons = Maps.newHashMap();

    for (Cell cell : game.getCells()) {
      buttons.put(buttons.size(), new CellButton(game, cell));
    }

    for (int i = game.getCells().length; i < game.getCells().length + 8; i++) {
      buttons.put(
          buttons.size(), Button.placeholder(Material.GRAY_STAINED_GLASS_PANE, (byte) 0, ","));
    }

    buttons.put(game.getCells().length + 8, new CashOutButton(game));
    return buttons;
  }

  @RequiredArgsConstructor
  private static class CellButton extends Button {

    private final Game game;
    private final Cell cell;

    @Override
    public ItemStack getButtonItem(Player player) {
      return cell.getIcon();
    }

    @Override
    public boolean shouldUpdate(Player player, ClickType clickType) {
      boolean shouldUpdate = !cell.isChecked();

      if (clickType.isLeftClick()) {
        game.checkCell(cell);
      }

      return shouldUpdate;
    }
  }

  @RequiredArgsConstructor
  private static class CashOutButton extends Button {

    private final Game game;

    @Override
    public ItemStack getButtonItem(Player player) {
      return new ItemBuilder(Material.GOLD_BLOCK)
          .setDisplayName(
              ChatColor.YELLOW
                  + "CashOut: "
                  + ChatColor.WHITE
                  + game.getCashOut()
                  + ChatColor.GRAY
                  + " ("
                  + ChatColor.GOLD
                  + game.getMultiplier()
                  + ChatColor.GRAY
                  + ")")
          .build();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
      if (clickType.isLeftClick()) {
        Minesweeper.getMinesweeper().getEconomyHook().giveBalance(player, game.getCashOut());
        player.closeInventory();
      }
    }
  }
}
