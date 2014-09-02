import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;


import org.bukkit.plugin.RegisteredServiceProvider;

public class Data {

	public static Economy economy = null;
	
	public static boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
	
}
