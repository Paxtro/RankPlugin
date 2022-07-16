package paxtro.spawnplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {

    // rank <player> <rank>

    private SpawnPlugin main;

    public RankCommand(SpawnPlugin main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

           if (player.isOp()) {
               if (args.length == 2) {
                    if (Bukkit.getOfflinePlayer(args[0]) != null) {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                        for (Rank rank : Rank.values()) {
                            if (rank.name().equalsIgnoreCase(args[1])) {
                                    main.getRankManger().setRank(target.getUniqueId(), rank, false);
                                    player.sendMessage(ChatColor.GREEN + "You have changed " + target.getName() + "'s rank! to " + rank.getDisplay() + ChatColor.GREEN + "!");
                                    if (target.isOnline()) {
                                        target.getPlayer().sendMessage(ChatColor.GREEN + "Congrats " + player.getName() + " on getting the rank " + rank.getDisplay() + ChatColor.GREEN + "!");
                                    }

                                return false;
                            }
                        }


                        player.sendMessage(ChatColor.RED  +  "Please put a valid rank: Guest, Member, MOD, Admin, and Owner");
                    } else {
                        player.sendMessage(ChatColor.RED  +  "This player has never joined the server before or does not exist!");
                    }
               } else {
                   player.sendMessage(ChatColor.RED + "Please format this command like this: /rank <player> <rank>!");
               }

           } else {
               player.sendMessage(ChatColor.RED + "I'm sorry but you don't have permission to do that!");
           }

        }

        return false;
    }
}
