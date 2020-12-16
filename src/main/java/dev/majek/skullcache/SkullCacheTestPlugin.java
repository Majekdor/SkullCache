package dev.majek.skullcache;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;
import java.util.logging.Level;

public class SkullCacheTestPlugin extends JavaPlugin implements CommandExecutor {

    public static SkullCacheTestPlugin instance;
    public static SkullCacheTestPlugin getInstance() { return instance; }
    public SkullCacheTestPlugin() {
        instance = this;
    }

    public UUID[] uuids = new UUID[]{
            UUID.fromString("fe365af3-9d84-44e5-befa-63dd244a2697"), // Penroll
            UUID.fromString("dc1ad102-b4f9-4190-b662-2bc4abc56339"), // RanchDude
            UUID.fromString("c3fd6ba1-c79f-43b0-8be2-29b9bd6c5a08"), // Obdurator
            UUID.fromString("efe2a359-3752-4431-a707-e6e66fcd961d"), // funnyboy_roks
            UUID.fromString("ca2d5464-e09a-45c2-9ac9-54cba9b34801"), // Majekdor
            UUID.fromString("c96b1aaf-7588-4178-9dd8-b47fab8eea31"), // rchristensen1
            UUID.fromString("e27453c2-6130-44c8-899a-cdf7cc27f346"), // Lythoniel
            UUID.fromString("8d8acbaa-e717-4ecd-8537-c6f924e6de83"), // Kiaria
            UUID.fromString("ace05f76-b402-44b6-90b7-5d3a5bb270e8"), // Its_Echo
            UUID.fromString("b168f067-d1d0-49c5-9bf1-b7f338a0ae1f"), // Nayreast
            UUID.fromString("fd34e88d-4e4a-413a-9e2c-a52d18389ba9"), // BumbleDoore
            UUID.fromString("501d7cc8-2124-4edb-b9ef-76a4df06d449"), // xCassyx
            UUID.fromString("d62987d8-0ffd-4675-9c8f-e732af240b54"), // Kishiko73
            UUID.fromString("4a3e052d-cea5-4051-836e-068fc538815a"), // _Evets_
            UUID.fromString("81f7cfb0-ef96-494b-9891-9d09d0d6f087"), // ToksykPanda
            UUID.fromString("d8f6eb9e-d8c9-40d9-b812-b613733139b5"), // Koneko86
            UUID.fromString("4a50a7c9-94c5-489c-8539-fff5f2e21135"), // DragonMage1986
            UUID.fromString("d38e9088-8c98-45c4-a015-d5624e1dfbd8"), // xMars
            UUID.fromString("6c510612-4ceb-49e0-96c3-f570e6e96a66"), // The_Fae
            UUID.fromString("5edf0701-b5cb-40c8-b01d-9d906654eb9c"), // minecraftZP
            UUID.fromString("c9e538ad-82dc-44d5-9b9b-c4dc2f2f12ea"), // Slickmajek
            UUID.fromString("244b4be4-00b8-410e-abd8-5db61a38ea04"), // CptCrossbones
            UUID.fromString("4d9c2693-bcfe-48f6-b524-a60031b5f05c"), // Maverick
            UUID.fromString("b36990a8-30b2-49fd-bed6-c58e301172c1"), // _Slam_
            UUID.fromString("17e1e5ee-ac35-42ed-908c-a70f9fc9fd2f"), // Showstopper
            UUID.fromString("a5182c2c-4116-41ea-8cb0-5d05cb5f3eef"), // FearsomeMango
            UUID.fromString("68ea40d0-202b-4b6c-9e9f-d438112c2d91"), // vawter140
            UUID.fromString("4d52426c-0bff-4660-b633-13bbd8a66d70"), // NightstarHunter
            UUID.fromString("8228fe1c-c02e-4c25-b24f-a005f08f8595"), // SuperAnimeBoi
            UUID.fromString("707fe786-4fb5-4d6c-b9d9-0461e4713232"), // Kneesnap
            UUID.fromString("a27db2bb-4408-4c33-986e-2386a4303bb0")  // PandaClod
    };

    @Override
    public void onEnable() {
        Bukkit.getLogger().log(Level.SEVERE, "SKULLCACHE IS NOT INTENDED TO BE RUN AS A PLUGIN EXCEPT FOR" +
                "DEVELOPMENT REASONS. THIS PLUGIN WILL DO NOTHING.");
        this.getCommand("testskullcache").setExecutor(this);
        Bukkit.getScheduler().runTaskLater(this,  () -> SkullCache.cacheSkulls(uuids), 100L);
    }
    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("testskullcache")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This is not a console command."); return true;
            }
            long start = System.currentTimeMillis();
            Player player = (Player) sender;
            Inventory inventory  = Bukkit.createInventory(null, 54, "Skull Cache Test");
            //SkullCache.cacheSkull(UUID.fromString("557ce2bd-1f1b-4cd2-8a62-f34a1744d281"));
            ItemStack skull = SkullCache.getSkull(UUID.fromString("557ce2bd-1f1b-4cd2-8a62-f34a1744d281"));
            inventory.setItem(53, skull);
            for (int i = 0; i < uuids.length; i++) {
                inventory.setItem(i, SkullCache.getSkull(uuids[i]));
            }
            player.openInventory(inventory);
            player.sendMessage("Completed in " + (System.currentTimeMillis() - start) + "ms.");
        }
        return true;
    }
}
