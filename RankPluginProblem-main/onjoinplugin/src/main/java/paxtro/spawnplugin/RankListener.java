package paxtro.spawnplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

public class RankListener implements Listener {

    private SpawnPlugin main;

    public RankListener(SpawnPlugin main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        player.hasPlayedBefore();

        if (!player.hasPlayedBefore()) {
            main.getRankManger().setRank(player.getUniqueId(), Rank.Guest, true);
        }

        main.getNametagManager().setNametags(player);
        main.getNametagManager().newTag(player);

        PermissionAttachment attachment;
        UUID playerId = player.getUniqueId();
        HashMap<UUID, PermissionAttachment> perms = main.getRankManger().getPerms();
        if (perms.containsKey(playerId)) {
            attachment = perms.get(playerId);
        } else {
            attachment = player.addAttachment(main);
            perms.put(player.getUniqueId(), attachment);
        }
        for (String perm : main.getRankManger().getRank(playerId).getPermissions()) {
            attachment.setPermission(perm, true);
        }

    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        main.getNametagManager().removeTag(e.getPlayer());
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        e.setCancelled(true);

        Player player = e.getPlayer();

         Bukkit.broadcastMessage(main.getRankManger().getRank(player.getUniqueId()).getDisplay() + " " + player.getName() + ChatColor.WHITE + ": " + ChatColor.WHITE + e.getMessage());
    }
}
