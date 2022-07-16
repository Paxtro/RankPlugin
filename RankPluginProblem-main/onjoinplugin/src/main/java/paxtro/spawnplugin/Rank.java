package paxtro.spawnplugin;

import org.bukkit.ChatColor;

public enum Rank {

    OWNER(ChatColor.DARK_RED + "[OWNER]", new String[]{"*"}),
    ADMIN(ChatColor.RED + "[ADMIN]", new String[]{"*"}),
    MODERATOR(ChatColor.GREEN + "[MOD]", new String[]{"ban"} ),
    MEMBER(ChatColor.YELLOW + "[MEMBER]", new String[]{}),
    Guest(ChatColor.GRAY + "[GUEST]", new String[]{});

    private String display;
    private String[] permissions;

    Rank(String display, String[] permissions) {
        this.display = display;
        this.permissions = permissions;
    }

    public String getDisplay() { return display; }
    public String[] getPermissions() { return permissions; }

}
