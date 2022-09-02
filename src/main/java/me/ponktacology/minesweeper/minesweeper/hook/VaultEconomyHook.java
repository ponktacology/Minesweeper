package me.ponktacology.minesweeper.minesweeper.hook;

import me.ponktacology.minesweeper.minesweeper.EconomyHook;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultEconomyHook implements EconomyHook {

  private Economy economy;

  public VaultEconomyHook() {
    initialize();
  }

  public void initialize() {
    RegisteredServiceProvider<Economy> rsp =
        Bukkit.getServer().getServicesManager().getRegistration(Economy.class);

    if (rsp == null) {
      throw new IllegalStateException("vault rsp not found");
    }

    economy = rsp.getProvider();

    if (economy == null) throw new IllegalStateException("vault not found");
  }

  @Override
  public void giveBalance(Player player, int balance) {
    economy.depositPlayer(player, balance);
  }

  @Override
  public void removeBalance(Player player, int balance) {
    economy.withdrawPlayer(player, balance);
  }

  @Override
  public int getBalance(Player player) {
    return (int) economy.getBalance(player);
  }
}
