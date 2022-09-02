# Minesweeper gambling game for Spigot

* Configurable multiplier and map size
* API for own economy hooks

Usage:

```
/mines <wager_amount> - Opens minesweeper game with specified wager
```

```java
public class YourCustomEconomyHook implements EconomyHook {

    @Override
    public void giveBalance(Player player, int balance) {
        // Your give balance logic
    }

    @Override
    public void removeBalance(Player player, int balance) {
        // Your remove balance logic
    }

    @Override
    public int getBalance(Player player) {
        // Your get balance logic
    }
}
```

Then you need to set Minesweeper's economy hook in your plugin's onEnable method by adding

```java
Minesweeper.getMinesweeper().setEconomyHook(new YourCustomEconomyHook());
```
![](https://github.com/ponktacology/Minesweeper/blob/master/img/GIF%209-2-2022%204-22-25%20PM.gif)
