package me.ponktacology.minesweeper.minesweeper.command;

import lombok.RequiredArgsConstructor;
import me.ponktacology.minesweeper.minesweeper.Configuration;
import me.ponktacology.minesweeper.minesweeper.EconomyHook;
import me.ponktacology.minesweeper.minesweeper.Minesweeper;
import me.ponktacology.minesweeper.minesweeper.game.Game;
import me.ponktacology.minesweeper.minesweeper.game.GameSettings;
import me.ponktacology.minesweeper.minesweeper.view.GameView;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class MinesCommand implements CommandExecutor {

  private final Configuration configuration;

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    Player player = (Player) sender;

    if (args.length != 1) {
      sender.sendMessage("Usage: /mines <wager>");
      return false;
    }

    int wager = Integer.parseInt(args[0]);

    if (wager <= 0) {
      sender.sendMessage("Wager must be a positive number.");
      return false;
    }

    EconomyHook economyHook = Minesweeper.getMinesweeper().getEconomyHook();
    if (economyHook.getBalance(player) < wager) {
      sender.sendMessage("Insufficient funds.");
      return false;
    }

    economyHook.removeBalance(player, wager);
    new GameView(new Game(new GameSettings(configuration), wager)).openMenu(player);
    return true;
  }
}
