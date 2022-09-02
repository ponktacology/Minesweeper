package me.ponktacology.minesweeper.minesweeper;

import lombok.Getter;
import lombok.Setter;
import me.ponktacology.minesweeper.minesweeper.command.MinesCommand;
import me.ponktacology.minesweeper.minesweeper.hook.MockEconomyHook;
import me.ponktacology.minesweeper.minesweeper.hook.VaultEconomyHook;
import me.ponktacology.minesweeper.minesweeper.util.menu.MenuListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Minesweeper extends JavaPlugin {

  @Getter private static Minesweeper minesweeper;
  @Getter @Setter private EconomyHook economyHook = new MockEconomyHook();

  @Override
  public void onEnable() {
    minesweeper = this;
    saveDefaultConfig();
    findEconomyHook();
    Configuration configuration = new Configuration(getConfig());
    Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
    getCommand("mines").setExecutor(new MinesCommand(configuration));
  }

  private void findEconomyHook() {
    if (getServer().getPluginManager().getPlugin("Vault") != null) {
      economyHook = new VaultEconomyHook();
    }
  }
}
