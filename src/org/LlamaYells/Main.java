package org.LlamaYells;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author leee leee
 */
public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new LlamaMouth(getServer()), this);
        getLogger().info("starting");
    }

}
