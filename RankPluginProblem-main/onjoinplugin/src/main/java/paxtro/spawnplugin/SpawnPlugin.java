package paxtro.spawnplugin;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import paxtro.spawnplugin.manager.NametagManager;
import paxtro.spawnplugin.manager.RankManager;

public final class SpawnPlugin extends JavaPlugin implements Listener {

    private RankManager rankManger;
    private NametagManager nametagManager;
    @Override
    public void onEnable() {
        getCommand("rank").setExecutor(new RankCommand(this));

        rankManger = new RankManager(this);
        nametagManager = new NametagManager(this);

        Bukkit.getPluginManager().registerEvents(new RankListener(this), this);

    }
    public RankManager getRankManger() {
        return rankManger; }
    public NametagManager getNametagManager() {
        return nametagManager;
    }
    @Override
    public void onDisable() {
        System.out.println("");
    }
}
